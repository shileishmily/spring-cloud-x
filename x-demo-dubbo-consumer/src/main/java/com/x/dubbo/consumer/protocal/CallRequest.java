package com.x.dubbo.consumer.protocal;

import java.io.Serializable;
import java.util.List;

/**
 * @author Leo
 */
public class CallRequest implements Serializable {


    private String interfaceName;
    private String method;
    private List<Object> param;
    private String address;
    private String version;


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Object> getParam() {
        return param;
    }

    public void setParam(List<Object> param) {
        this.param = param;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CallRequest{" +
                "interfaceName='" + interfaceName + '\'' +
                ", method='" + method + '\'' +
                ", param=" + param +
                ", address='" + address + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
