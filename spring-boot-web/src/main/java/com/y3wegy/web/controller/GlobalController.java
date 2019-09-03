package com.y3wegy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.base.web.tools.RestCallExecutor;

/**
 * /**
 *
 * @author y3wegy
 * @create 6/14/2018
 * @description
 **/
@RestController
public class GlobalController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    @Value("${eureka.service.url}")
    private String serviceURL;

    @Value("${eureka.service.serviceId}")
    private String serviceName;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("version")
    public String version() throws ServiceException {
        ResponseJson responseJson = RestCallExecutor.get(restTemplate, serviceURL + "/version");
        String jsonStr = JackSonHelper.obj2JsonStr(responseJson);
        logger.info(jsonStr);
        return jsonStr;
    }

    @RequestMapping("serviceInfo")
    public String serviceInfo() throws JsonProcessingException {
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);
        Map<String, Object> info = new HashMap<>(2);
        info.put("ServiceId", serviceInstance.getServiceId());
        info.put("host", serviceInstance.getHost());
        info.put("port", serviceInstance.getPort());
        return JackSonHelper.getObjectMapper().writeValueAsString(new ResponseJson().data(info));
    }

}
