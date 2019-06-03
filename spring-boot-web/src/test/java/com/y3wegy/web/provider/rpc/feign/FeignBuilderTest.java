package com.y3wegy.web.provider.rpc.feign;

import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import feign.RequestLine;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.jupiter.api.Test;

public class FeignBuilderTest {

    private JacksonDecoder decoder = new JacksonDecoder(JackSonHelper.getObjectMapper());
    private JacksonEncoder encoder = new JacksonEncoder(JackSonHelper.getObjectMapper());

    @Test
    void testCall() {
        serviceAAA serviceAAA = HystrixFeign.builder()
                .decoder(decoder)
                .target(serviceAAA.class, "http://localhost:10101", new fallBack());

        // Fetch and print a list of the contributors to this library.
        ResponseJson responseJson = serviceAAA.getMenu();
        System.out.println(responseJson.getData());
    }
}

interface serviceAAA {
    @RequestLine("GET /api/web/menu")
    ResponseJson getMenu();
}

class fallBack implements serviceAAA {

    @Override
    public ResponseJson getMenu() {
        return new ResponseJson().fail(null);
    }
}
