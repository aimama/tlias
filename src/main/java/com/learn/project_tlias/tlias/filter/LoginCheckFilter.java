package com.learn.project_tlias.tlias.filter;

import com.alibaba.fastjson.JSONObject;
import com.learn.project_tlias.tlias.Utils.JwtUtils;
import com.learn.project_tlias.tlias.pojo.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    //servletRequest获取请求参数
    //servletResponse响应参数
    //filterChain放行操作
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取url
        StringBuffer requestURL = request.getRequestURL();
        log.info("获取到的url地址：" + requestURL);

        //字串匹配，判断是否为登录请求
        if (requestURL.indexOf("login") != -1) {
            //字符中包含login则说明是登录操作，跳过
            log.info("登录操作，放行");
            filterChain.doFilter(request, response);
            return;
        }

        //非登录操作，获取token信息
        String jwt = request.getHeader("token");
        log.info("已拦截，正在验证JWT信息");
        //判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            //字符为null或为空，则返回
            log.info("请求头为空，返回未登录信息");
            Result error = Result.error("Not_Login");
            //手动转换成JSON
            String jsonString = JSONObject.toJSONString(error); //使用阿里巴巴的转JSON依赖转换
            response.getWriter().write(jsonString);         //手动将JSON写入信息流，利用response传给服务端
            return;
        }

        //令牌校验
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) { //凡是异常就说明解析失败，否则成功
            e.printStackTrace();
            log.info("令牌解析失败，返回未登录错误信息");

            Result error = Result.error("Not_Login");
            //手动转换成JSON
            String jsonString = JSONObject.toJSONString(error); //使用阿里巴巴的转JSON依赖转换
            response.getWriter().write(jsonString);         //手动将JSON写入信息流，利用response传给服务
        }

        //令牌正常,放行
        log.info("令牌合法");
        filterChain.doFilter(request,response);
    }
}
