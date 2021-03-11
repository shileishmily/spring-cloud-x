package com.x.gray.core;

import com.google.common.base.Optional;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrayMetadataRule extends ZoneAvoidanceRule {
    public static final String META_DATA_KEY_VERSION = "version";

    private static final Logger logger = LoggerFactory.getLogger(GrayMetadataRule.class);

    @Override
    public Server choose(Object key) {

        List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }

        String hystrixVer = CoreHeaderInterceptor.version.get();
        logger.info("======>GrayMetadataRule:  hystrixVer{}", hystrixVer);

        List<Server> noMetaServerList = new ArrayList<>();
        for (Server server : serverList) {
            Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();

            /**
             * version策略，判断客户端请求头中的version值和服务提供者列表元数据中取出的version值是否一致
             * 若一致，走灰度调用流程
             * 若不一致，检查请求头中version为空且服务提供者列表中存在元数据为空的服务器，则走非灰度调用流程（正常调用）
             * 否则，choose返回，消费者找不到合适的提供者
             */

            String metaVersion = metadata.get(META_DATA_KEY_VERSION);
            if (!StringUtils.isEmpty(metaVersion)) {
                if (metaVersion.equals(hystrixVer)) {
                    return server;
                }
            } else {
                noMetaServerList.add(server);
            }
        }

        if (StringUtils.isEmpty(hystrixVer) && !noMetaServerList.isEmpty()) {
            logger.info("====> 无请求header...");
            return originChoose(noMetaServerList, key);
        }

        return null;
    }

    private Server originChoose(List<Server> noMetaServerList, Object key) {
        Optional<Server> server = getPredicate().chooseRoundRobinAfterFiltering(noMetaServerList, key);
        if (server.isPresent()) {
            return server.get();
        } else {
            return null;
        }
    }
}
