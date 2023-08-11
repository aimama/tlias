package com.learn.project_tlias.tlias.controller;

import com.learn.project_tlias.tlias.pojo.Dept;
import com.learn.project_tlias.tlias.pojo.Result;
import com.learn.project_tlias.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    //    private Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    //设置Controller对前端数据进行接受，以及将后端服务器发送过来的数据封装为Result对象，返回给浏览器
    @GetMapping
    public Result list() {
        log.info("查询全部的部门数据");

        //调用service查询部门的方法
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result deleteList(@PathVariable Integer id) {
        deptService.deleteList(id);

        log.info("删除记录成功");
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Dept dept){
        deptService.insert(dept);

        log.info("新增成功");

        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);

        log.info("修改成功");
        return Result.success();
    }



}
