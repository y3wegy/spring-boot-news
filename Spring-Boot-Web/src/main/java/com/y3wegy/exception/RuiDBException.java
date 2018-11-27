package com.y3wegy.exception;

/**
 * @author rui
 */
public class RuiDBException extends Exception {
    public RuiDBException(String msg) {
        super(msg);
    }

    public RuiDBException(String msg, Throwable e) {
        super(msg, e);
    }
}
