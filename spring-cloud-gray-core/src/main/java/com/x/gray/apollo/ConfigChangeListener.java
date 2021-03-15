package com.x.gray.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.netflix.appinfo.EurekaInstanceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 使用@ApolloConfig自动注入Config对象
 * 使用@ApolloConfigChangeListener自动注入ConfigChangeListener对象 当监听到属性值发生变化后使用Config
 * API修改属性值
 *
 * @author Leo
 */
@Slf4j
@EnableApolloConfig
public class ConfigChangeListener {

    @ApolloConfig
    private Config config;

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;

    @Value("${eureka.client.serviceUrl.defaultZone:}")
    private String eurekaUrl;

    @Value("${eureka.client.service-url.defaultZone:}")
    private String eureka_url;

    private String eurekaConnUrl;

    @ApolloConfigChangeListener("application")
    private void versionChange(ConfigChangeEvent changeEvent) {
        changeEvent.changedKeys().forEach(key -> {
            ConfigChange change = changeEvent.getChange(key);
            log.debug("Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());

            // 灰度配置
            if (GrayBean.version.equals(change.getPropertyName())) {
                String appName = eurekaInstanceConfig.getAppname();
                String instanceId = eurekaInstanceConfig.getInstanceId();
                String value = StringUtils.isEmpty(change.getNewValue()) ? "" : change.getNewValue();

                /**
                 * http://localhost:8761/eureka/,http://localhost:8762/eureka/,http://localhost:8763/eureka/
                 * 配合eureka集群时，需要根据逗号分隔符解析Eureka地址
                 */
                String[] eurekaUrls = eurekaConnUrl.split(",");
                for (String url : eurekaUrls) {
                    try {
                        CloseableHttpClient httpclient = HttpClients.createDefault();

                        //调用eureka变更元数据接口：/eureka/apps/{appID}/{instanceID}/metadata?key=value
                        HttpPut httpPut = new HttpPut(url + "apps/" + appName + "/" + instanceId + "/metadata?version=" + value);

                        log.debug("======>请求url:{}", httpPut.getURI());

                        ResponseHandler<String> responseHandler = response -> {
                            int status = response.getStatusLine().getStatusCode();
                            if (status >= 200 && status < 300) {
                                HttpEntity entity = response.getEntity();
                                return entity != null ? EntityUtils.toString(entity) : null;
                            } else {
                                throw new ClientProtocolException("Unexpected response status: " + status);
                            }
                        };
                        String responseBody = httpclient.execute(httpPut, responseHandler);
                        log.debug("======返回结果:{}", responseBody);
                    } catch (IOException e) {
                        log.error("sync eureka metadata-map error", e);
                    }
                }
            }
        });
    }


    @PostConstruct
    public void initConfig() {
        this.eurekaConnUrl = StringUtils.isEmpty(eurekaUrl) ? eureka_url : eurekaUrl;
        log.info("eureka url: {}", eurekaConnUrl);
    }
}