package com.gen.api.global.config.security;

import com.gen.api.global.contant.ErrorContant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.gen.api.global.common.ErrorResult;
import com.gen.api.global.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-05-20
 * Time: 오후 4:57
 */
@Slf4j
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        ErrorResult error = new ErrorResult();
        if (request.getAttribute("token") != null) {
            error = (ErrorResult) request.getAttribute("token");
        } else {
            error.setError("Unauthorized");
            error.setCode(ErrorContant.UNAUTHORIZED);
            error.setMessage(ErrorContant.getMessage(error.getCode()));

        }
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.dtoToJson(error));


    }
}
