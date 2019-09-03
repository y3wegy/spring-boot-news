package com.y3wegy.push.handler;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.y3wegy.base.exception.WebException;
import com.y3wegy.base.web.bean.web.constants.WebConstants;
import com.y3wegy.push.bean.principal.WebSocketUserAuthentication;

/**
 * <设置认证用户信息>
 * <功能详细描述>
 * @author wzh
 * @version 2018-09-18 23:55
 * @see [相关类/方法] (可选)
 **/
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {

    private static final Logger logger = LoggerFactory.getLogger(PrincipalHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        try {
            HttpSession httpSession = getSession(request);
            // 获取登录的信息，就是controller 跳转页面存的信息，可以根据业务修改
            String user = (String) httpSession.getAttribute(WebConstants.TOKEN_KEY);

            if (StringUtils.isEmpty(user)) {
                logger.warn("Not login yet, forbid use WebSocket!");
                return null;
            }
            logger.info(" MyDefaultHandshakeHandler login ={} ", user);
            return new WebSocketUserAuthentication(user);
        } catch (WebException e) {
            logger.error("Get Principal failed", e);
            return null;
        }
    }

    private HttpSession getSession(ServerHttpRequest request) throws WebException {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession(false);
        }
        throw new WebException("Only accept ServletServerHttpRequest");
    }

}
