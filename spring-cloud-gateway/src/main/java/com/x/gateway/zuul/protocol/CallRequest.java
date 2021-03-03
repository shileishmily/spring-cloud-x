package com.x.gateway.zuul.protocol;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Leo
 */
@Data
public class CallRequest implements Serializable {

    private String interfaceName;
    private String method;
    private List<Object> param;
    private String address;
    private String version;
}
