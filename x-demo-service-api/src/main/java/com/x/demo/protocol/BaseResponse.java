package com.x.demo.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Leo
 */
@Data
public class BaseResponse<T> implements Serializable {
    private String code;

    private String message;

    private T data;
}