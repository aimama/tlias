package com.learn.project_tlias.tlias.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.learn.project_tlias.tlias.Utils.AliOSSUtils;
import com.learn.project_tlias.tlias.Utils.JwtUtils;
import com.learn.project_tlias.tlias.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //登录请求，自动放行
        StringBuffer url = req.getRequestURL();
        if (url.indexOf("Login") == -1) {
            log.info("登录请求，放行");
            return true;
        }

        //非登录请求，获取token
        String token = req.getHeader("token");
        //空，未登录
        if (!StringUtils.hasLength(token)) {
            log.info("空token，返回未登录信息");

            Result error = Result.error("Not_Login");
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);

            return false;
        }

        //jwt信息不匹配
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("jwt信息不匹配，返回未登录信息");

            Result error = Result.error("Not_Login");
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);

            return false;
        }

        //放行
        log.info("合法放行");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
