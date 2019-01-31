package com.y3wegy.web.rpc.cloudservice.feignclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * @author y3wegy
 */
public interface WebFeignClient {

    /**
     * -------------------------------------------------------------
     * @author     @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    ResponseJson getMenuData();

}
