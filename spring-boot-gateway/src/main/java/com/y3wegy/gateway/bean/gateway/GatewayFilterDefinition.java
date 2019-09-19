package com.y3wegy.gateway.bean.gateway;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author e631876
 */
@Data
public class GatewayFilterDefinition {
    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
