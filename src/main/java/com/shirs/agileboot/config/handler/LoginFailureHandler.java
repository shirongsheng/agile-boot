package com.shirs.agileboot.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shirs.agileboot.common.constant.AgileConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 401);
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            map.put("message", AgileConstant.USERNMAE_OR_PASSWORD_ERROR);
        } else if (exception instanceof DisabledException) {
            map.put("message", "账户被禁用");
        } else {
            map.put("message", "登录失败!");
        }
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
