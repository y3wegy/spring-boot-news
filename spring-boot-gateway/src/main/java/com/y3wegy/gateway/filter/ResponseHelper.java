package com.y3wegy.gateway.filter;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;

/**
 * @author e631876
 */
public class ResponseHelper {
    private static final Logger logger = LoggerFactory.getLogger(ResponseHelper.class);

    private static final String ERROR_MSG = "Decode repsonse json failed";
    private static final String ERROR_MSG2 = "Decode repsonse json failed again";

    private ResponseHelper() {
    }

    public static byte[] byteReponse(ResponseJson responseJson) {
        byte[] responseBytes = new byte[0];
        try {
            responseBytes = JackSonHelper.obj2JsonStr(responseJson).getBytes(StandardCharsets.UTF_8);
        } catch (ServiceException e) {
            logger.error(ERROR_MSG, e);
            responseJson.fail(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()), ERROR_MSG);
            try {
                responseBytes = JackSonHelper.obj2JsonStr(responseJson).getBytes(StandardCharsets.UTF_8);
            } catch (ServiceException e1) {
                logger.error(ERROR_MSG2, e1);
            }
        }
        return responseBytes;
    }
}
