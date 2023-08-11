package com.learn.project_tlias.tlias.service;

import com.learn.project_tlias.tlias.pojo.Emp;
import com.learn.project_tlias.tlias.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public interface EmpService {
    //根据page，pageSize来返回分页数据
    PageBean pageList(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
    //删除员工数据
    void delete(List<Integer> id);
    //增加员工
    void insert(Emp emp);
    //修改员工
    void update(Emp emp);
    //查询回显
    Emp getInfrom(Integer id);

    Emp login(Emp emp);
}
