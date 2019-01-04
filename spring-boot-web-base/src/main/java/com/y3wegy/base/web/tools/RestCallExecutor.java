package com.y3wegy.base.web.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author y3wegy
 */
public class RestCallExecutor {

    private static final Logger logger = LoggerFactory.getLogger(RestCallExecutor.class);

    private RestCallExecutor() {
    }


    public static <T> List<T> postForList(RestTemplate restTemplate, String serviceURL, Object postBody) throws ServiceExeption {
        return postForObject(restTemplate, serviceURL, postBody, new TypeReference<List<T>>() {});
    }

    /**
     * -------------------------------------------------------------
     *
     * @param restTemplate
     * @param serviceURL
     * @param postBody
     * @param resultType
     * @param <T>
     * @return T
     * @throws ServiceExeption
     * @author @date        @comment
     * Chen, Rui   1/4/2019     init version
     * -------------------------------------------------------------
     */
    public static <T> T postForObject(RestTemplate restTemplate, String serviceURL, Object postBody, TypeReference<T> resultType) throws ServiceExeption {
        ResponseJson responseJson = post(restTemplate, serviceURL, postBody);
        return JackSonHelper.jsonStr2Obj((String) responseJson.getData(), resultType);
    }

    /**
     * -------------------------------------------------------------
     *
     * @param restTemplate
     * @param serviceURL
     * @param postBody
     * @return ResponseJson
     * @author @date        @comment
     * Chen, Rui   1/4/2019     init version
     * -------------------------------------------------------------
     * simple postForObject json and return ResponseJson
     */
    public static ResponseJson post(RestTemplate restTemplate, String serviceURL, Object postBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> request = new HttpEntity<>(postBody, headers);

        return restTemplate.postForObject(serviceURL, request, ResponseJson.class);
    }

    /**
     * -------------------------------------------------------------
     *
     * @param restTemplate
     * @param serviceURL
     * @return List
     * @author @date        @comment
     * Chen, Rui   1/4/2019     init version
     * -------------------------------------------------------------
     */
    public static <T> List<T> getForList(RestTemplate restTemplate, String serviceURL) throws ServiceExeption {
        return getForObject(restTemplate, serviceURL, new TypeReference<List<T>>() {
        });
    }

    /**
     * -------------------------------------------------------------
     *
     * @param restTemplate
     * @param serviceURL
     * @param resultType
     * @return T
     * @author @date        @comment
     * Chen, Rui   1/4/2019     init version
     * -------------------------------------------------------------
     */
    public static <T> T getForObject(RestTemplate restTemplate, String serviceURL, TypeReference<T> resultType) throws ServiceExeption {
        ResponseJson responseJson = get(restTemplate, serviceURL);
        return JackSonHelper.jsonStr2Obj((String) responseJson.getData(), resultType);
    }

    /**
     * -------------------------------------------------------------
     *
     * @param restTemplate
     * @param serviceURL
     * @return ResponseJson
     * @author @date        @comment
     * Chen, Rui   1/4/2019     init version
     * -------------------------------------------------------------
     */
    public static ResponseJson get(RestTemplate restTemplate, String serviceURL) {
        return restTemplate.getForObject(serviceURL, ResponseJson.class);
    }
}
