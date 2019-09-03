package com.y3wegy.push.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.y3wegy.base.web.bean.web.constants.PushConstants;
import com.y3wegy.base.web.bean.web.constants.WebConstants;

@Controller
@RequestMapping(PushConstants.ENDPOINT)
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    // 跳转stomp websocket 页面
    @GetMapping("/spring/stompSocket.do")
    public String toStompWebSocket(HttpSession session, HttpServletRequest request, Model model) {
        // 这里封装一个登录的用户组参数，模拟进入通讯后的简单初始化
        model.addAttribute("groupId", "user_groupId");
        model.addAttribute("session_id", session.getId());
        logger.info("跳转：{}", session.getId());
        session.setAttribute(WebConstants.TOKEN_KEY, session.getId());
        return "/test/springWebSocketStomp";
    }
}
