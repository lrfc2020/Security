package com.local.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.security.common.enums.RespCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登录成功处理页面
 */
@Component
public class    MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Map<String,Object>  map = new HashMap<>();
        map.put("Authorities",authentication.getAuthorities());
        map.put("Credentials",authentication.getCredentials());
        map.put("Details",authentication.getDetails());
        map.put("Principal",authentication.getPrincipal());
        map.put("isAuthenticated",authentication.isAuthenticated());

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseData.getInstance().generateRespData(RespCodeEnum.SUCCESS,map)));
    }
}
