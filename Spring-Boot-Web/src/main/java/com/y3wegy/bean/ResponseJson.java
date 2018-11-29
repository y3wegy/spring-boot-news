package com.y3wegy.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author y3wegy
 */
public class ResponseJson extends HashMap<String, Object> implements Serializable {

    private static final String KEY_OP = "OPERATION";
    private static final String KEY_SUCC = "SUCCESS";
    private static final String KEY_CODE = "CODE";
    private static final String KEY_DATA = "DATA";

    private static final String DEF_OP = "Default";
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private static final String SUCC_CODE = "1";
    private static final String FAIL_CODE = "-1";

    public ResponseJson() {
        this.put(KEY_OP, DEF_OP);
        this.put(KEY_SUCC, SUCCESS);
        this.put(KEY_CODE, SUCC_CODE);
    }

    public ResponseJson(String operation) {
        this.put(KEY_OP, operation);
        this.put(KEY_SUCC, SUCCESS);
        this.put(KEY_CODE, SUCC_CODE);
    }

    public ResponseJson(String operation, String success, String code, Object data) {
        this.put(KEY_OP, operation);
        this.put(KEY_SUCC, success);
        this.put(KEY_CODE, code);
        this.put(KEY_DATA, data);
    }

    public ResponseJson success(Object data) {
        this.put(KEY_SUCC, SUCCESS);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson success(String successCode, Object data) {
        this.put(KEY_SUCC, FAIL);
        this.put(KEY_CODE, successCode);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson fail(Object data) {
        this.put(KEY_SUCC, FAIL);
        this.put(KEY_CODE, FAIL_CODE);
        this.put(KEY_DATA, data);
        return this;
    }

    public ResponseJson fail(String failCode, Object data) {
        this.put(KEY_SUCC, FAIL);
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

}
