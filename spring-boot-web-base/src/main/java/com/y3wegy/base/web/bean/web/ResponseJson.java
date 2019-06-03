package com.y3wegy.base.web.bean.web;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author y3wegy
 */
public class ResponseJson extends HashMap<String, Object> implements Serializable {

    private static final String KEY_OP = "operation";
    private static final String KEY_SUCC = "isSuccess";
    private static final String KEY_CODE = "code";
    private static final String KEY_DATA = "data";

    private static final String DEF_OP = "default";
    private static final boolean DEF_SUCCESS = true;
    private static final boolean DEF_FAIL = false;
    private static final String SUCC_CODE = "1";
    private static final String FAIL_CODE = "-1";

    public ResponseJson() {
        this.put(KEY_OP, DEF_OP);
        this.put(KEY_SUCC, DEF_SUCCESS);
        this.put(KEY_CODE, SUCC_CODE);
    }

    public ResponseJson(Object data) {
        this();
        this.put(KEY_DATA, data);
    }

    public ResponseJson(String operation, boolean success, String code, Object data) {
        this.put(KEY_OP, operation);
        this.put(KEY_SUCC, success);
        this.put(KEY_CODE, code);
        this.put(KEY_DATA, data);
    }

    public ResponseJson success(Object data) {
        this.put(KEY_OP, DEF_OP);
        this.put(KEY_SUCC, DEF_SUCCESS);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson success(String successCode, Object data) {
        this.put(KEY_SUCC, DEF_SUCCESS);
        this.put(KEY_CODE, successCode);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson fail(Object data) {
        this.put(KEY_SUCC, DEF_FAIL);
        this.put(KEY_CODE, FAIL_CODE);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson fail(String failCode, Object data) {
        this.put(KEY_SUCC, DEF_FAIL);
        this.put(KEY_CODE, failCode);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson operate(String operation) {
        this.put(KEY_OP, operation);
        return this;
    }

    public ResponseJson code(String code) {
        this.put(KEY_CODE, code);
        return this;
    }

    public ResponseJson data(Object data) {
        this.put(KEY_DATA, data);
        return this;
    }

    public String getOperation() {
        return String.valueOf(this.get(KEY_OP));
    }

    public boolean isSuccess() {
        return Boolean.parseBoolean(String.valueOf(this.get(KEY_SUCC)));
    }

    public String getCode() {
        return String.valueOf(this.get(KEY_CODE));
    }

    public Object getData() {
        return this.get(KEY_DATA);
    }

}
