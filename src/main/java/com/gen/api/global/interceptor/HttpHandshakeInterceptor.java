package com.gen.api.global.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-17
 * Time: 오후 12:46
 */
@Slf4j
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        log.debug("___________beforeHandshake");
        log.debug(request.getURI().toString());
        log.debug(request.getLocalAddress().toString());
        log.debug(request.getRemoteAddress().toString());
        log.debug(request.getHeaders().toString());
        log.debug("___________beforeHandshake");

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            attributes.put("sessionId", session.getId());
        }

        return true;
    }

    /**
     * After handshake.
     *
     * @param request   the request
     * @param response  the response
     * @param wsHandler the ws handler
     * @param exception the exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // nothing to do.


        try {
            log.debug("___________afterHandshake");
            log.debug(request.getURI().toString());
            log.debug(response.getHeaders().toString());
            log.debug(response.getBody().toString());
            log.debug("___________afterHandshake");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
