package com.y3wegy.base.exception;

/**
 * @author e631876
 */
public class WebException extends Exception {
    public WebException(String msg) {
        super(msg);
    }

    public WebException(String msg, Throwable e) {
        super(msg, e);
    }
}
