package com.shirs.agileboot.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response,
                                      Integer status,
                                      String string) {
        try {
            response.setStatus(status);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
