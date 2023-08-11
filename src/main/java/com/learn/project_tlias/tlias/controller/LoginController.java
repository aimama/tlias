package com.learn.project_tlias.tlias.controller;

import com.learn.project_tlias.tlias.Utils.JwtUtils;
import com.learn.project_tlias.tlias.pojo.Emp;
import com.learn.project_tlias.tlias.pojo.Result;
import com.learn.project_tlias.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("请求登录");

        Emp emp1 = empService.login(emp);
        //判断是否查询到用户信息
        if(emp1 != null){
            //生成JWT
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",emp1.getId());
            claims.put("name",emp1.getName());
            claims.put("username",emp1.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            return  Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }
}
