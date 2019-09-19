package com.y3wegy.gateway;

/**
 * @author e631876
 */
public class GateWayException extends Exception {
    public GateWayException(String msg) {
        super(msg);
    }

    public GateWayException(String msg, Throwable e) {
        super(msg, e);
    }
}
