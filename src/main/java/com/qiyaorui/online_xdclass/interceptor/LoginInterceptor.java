package com.qiyaorui.online_xdclass.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiyaorui.online_xdclass.utils.JWTUtils;
import com.qiyaorui.online_xdclass.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入controller之前的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String accessToken = request.getHeader("token");
            if (accessToken == null){
                accessToken = request.getParameter("token");
            }
            if(StringUtils.isNotBlank(accessToken)){
                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims == null){
                    //告诉用户登录过期，重新登录
                    JsonData jsonData = JsonData.buildError("登录过期，重新登录");
                    sendJsonMassage(response,jsonData);
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id",id);
                request.setAttribute("name",name);
                return true;
            }
        }catch (Exception e){}
        sendJsonMassage(response,JsonData.buildError("请先登录"));
        return false;
    }

    /**
     * 相应json数据给前端
     * @param response
     * @param obj
     */
    private static void sendJsonMassage(HttpServletResponse response, Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.flush();
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
