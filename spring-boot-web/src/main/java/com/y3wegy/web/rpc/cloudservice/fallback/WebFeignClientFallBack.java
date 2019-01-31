package com.y3wegy.web.rpc.cloudservice.fallback;

import com.y3wegy.web.rpc.cloudservice.feignclient.WebFeignClient;

import com.y3wegy.base.web.bean.web.ResponseJson;

/**
 * @author y3wegy
 */
//@Component
public class WebFeignClientFallBack implements WebFeignClient {

    @Override
    public ResponseJson getMenuData() {
        return new ResponseJson("getMenuData", false, "-1", null);
    }
}
