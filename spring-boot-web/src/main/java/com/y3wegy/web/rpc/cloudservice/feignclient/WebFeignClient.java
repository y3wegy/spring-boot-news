package com.y3wegy.web.rpc.cloudservice.feignclient;

import org.springframework.web.bind.annotation.RequestMapping;

import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * @author y3wegy
 */
public interface WebFeignClient {

    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     */
    @RequestMapping(value = "/menu")
    //@RequestLine("GET /menu")
    ResponseJson getMenuData();

}
