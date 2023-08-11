package com.learn.project_tlias.tlias.controller;

import com.learn.project_tlias.tlias.pojo.Emp;
import com.learn.project_tlias.tlias.pojo.PageBean;
import com.learn.project_tlias.tlias.pojo.Result;
import com.learn.project_tlias.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result pageList(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String name,
                           Short gender,
                           @DateTimeFormat(pattern = "yyy-MM-dd") LocalDate begin,
                           @DateTimeFormat(pattern = "yyy-MM-dd") LocalDate end) {
        log.info("接收到请求：{},{},{},{},{},{}" + name, gender, begin, end, page, pageSize);

        PageBean pageBean = empService.pageList(page, pageSize, name, gender, end, begin);

        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result deletes(@PathVariable List<Integer> ids) {
        log.info("请求删除的id为:" + ids.toString());

        empService.delete(ids);

        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("添加数据");

        empService.insert(emp);

        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改数据");

        empService.update(emp);

        log.info("修改完成");
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInform(@PathVariable Integer id) {
        log.info("查询回显");

        Emp emp = empService.getInfrom(id);
        return Result.success(emp);
    }

}
