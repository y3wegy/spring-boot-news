package com.y3wegy.base.exception;

/**
 * @author y3wegy
 */
public class ServiceException extends Exception {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }
}