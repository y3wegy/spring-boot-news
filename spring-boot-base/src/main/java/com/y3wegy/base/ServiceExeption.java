package com.y3wegy.base;

/**
 * @author y3wegy
 */
public class ServiceExeption extends Exception {
    public ServiceExeption(String msg) {
        super(msg);
    }

    public ServiceExeption(String msg, Throwable e) {
        super(msg, e);
    }
}