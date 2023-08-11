package com.learn.project_tlias.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.project_tlias.tlias.mapper.EmpMapper;
import com.learn.project_tlias.tlias.pojo.Emp;
import com.learn.project_tlias.tlias.pojo.PageBean;
import com.learn.project_tlias.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServicelmpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean pageList(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        //计算起始数据
//        page = (page - 1) * pageSize;
//
//        PageBean pageBean = new PageBean();
//        pageBean.setTotal(empMapper.getTotal());
//
//        pageBean.setRows(empMapper.getRows(page, pageSize));

        //使用pageHelper插件

        //1传入参数
        PageHelper.startPage(page, pageSize);

        //2传入SQL语句的，并由pageHelper自动增加limit语句
        List<Emp> list = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;

        //3封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());


        return pageBean;
    }

    //删除员工
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteList(ids);
    }

    //增加员工
    @Override
    public void insert(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    //修改员工信息
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

    }

    @Override
    public Emp getInfrom(Integer id) {
        return empMapper.getInform(id);
    }
    //登录请求
    @Override
    public Emp login(Emp emp) {
       return  empMapper.login(emp);
    }
}
