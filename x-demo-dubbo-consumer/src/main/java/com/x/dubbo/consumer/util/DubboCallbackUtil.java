package com.x.dubbo.consumer.util;


import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Class.forName;

/**
 * @author Leo
 */
public class DubboCallbackUtil {

    private static Logger logger = LogManager.getLogger(DubboCallbackUtil.class);

    // 当前应用的信息
    private static ApplicationConfig application = new ApplicationConfig();
    // 注册中心信息缓存
    private static Map<String, RegistryConfig> registryConfigCache = new ConcurrentHashMap<>();

    // 各个业务方的ReferenceConfig缓存
    private static Map<String, ReferenceConfig> referenceCache = new ConcurrentHashMap<>();

    static {
        application.setName("x-demo-dubbo-consumer");
    }

    /**
     * 获取注册中心信息
     *
     * @param address zk注册地址
     * @param group   dubbo服务所在的组
     * @return
     */
    private static RegistryConfig getRegistryConfig(String address, String group, String version) {
        String key = address + "-" + group + "-" + version;
        RegistryConfig registryConfig = registryConfigCache.get(key);
        if (null == registryConfig) {
            registryConfig = new RegistryConfig();
            if (StringUtils.isNotEmpty(address)) {
                registryConfig.setAddress(address);
            }
            if (StringUtils.isNotEmpty(version)) {
                registryConfig.setVersion(version);
            }
            if (StringUtils.isNotEmpty(group)) {
                registryConfig.setGroup(group);
            }
            registryConfigCache.put(key, registryConfig);
        }
        return registryConfig;
    }

    private static ReferenceConfig getReferenceConfig(String interfaceName, String address,
                                                      String group, String version) {
        String referenceKey = interfaceName;

        ReferenceConfig referenceConfig = referenceCache.get(referenceKey);
        if (null == referenceConfig) {
            try {
                referenceConfig = new ReferenceConfig<>();
                referenceConfig.setApplication(application);
                referenceConfig.setRegistry(getRegistryConfig(address, group, version));
                Class interfaceClass = forName(interfaceName);
                referenceConfig.setInterface(interfaceClass);
                if (StringUtils.isNotEmpty(version)) {
                    referenceConfig.setVersion(version);
                }
                referenceConfig.setGeneric(true);
                //referenceConfig.setUrl("dubbo://10.1.50.167:20880/com.test.service.HelloService");
                referenceCache.put(referenceKey, referenceConfig);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return referenceConfig;
    }

    public static Object invoke(String interfaceName, String methodName, List<Object> paramList, String address, String version) {
        ReferenceConfig reference = getReferenceConfig(interfaceName, address, null, version);
        if (null != reference) {
            GenericService genericService = (GenericService) reference.get();
            if (genericService == null) {
                logger.debug("GenericService 不存在:{}", interfaceName);
                return null;
            }

            Object[] paramObject = null;
            if (!CollectionUtils.isEmpty(paramList)) {
                paramObject = new Object[paramList.size()];
                for (int i = 0; i < paramList.size(); i++) {
                    paramObject[i] = paramList.get(i);
                }
            }


            Object resultParam = genericService.$invoke(methodName, getMethodParamType(interfaceName, methodName), paramObject);
            return resultParam;
        }
        return null;
    }


    public static String[] getMethodParamType(String interfaceName, String methodName) {
        try {
            //创建类
            Class<?> class1 = Class.forName(interfaceName);
            //获取所有的公共的方法
            Method[] methods = class1.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] paramClassList = method.getParameterTypes();
                    String[] paramTypeList = new String[paramClassList.length];
                    int i = 0;
                    for (Class className : paramClassList) {
                        paramTypeList[i] = className.getTypeName();
                        i++;
                    }
                    return paramTypeList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}