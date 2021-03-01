package com.x.demo.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Leo
 */
@Data
public class BaseRequest<T> implements Serializable {
    private String uuid;

    private T data;
}