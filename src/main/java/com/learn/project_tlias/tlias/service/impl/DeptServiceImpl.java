package com.learn.project_tlias.tlias.service.impl;

import com.learn.project_tlias.tlias.mapper.DeptMapper;
import com.learn.project_tlias.tlias.mapper.EmpMapper;
import com.learn.project_tlias.tlias.pojo.Dept;
import com.learn.project_tlias.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    //因为Service层不能直接使用数据库，只能调用Mapper层
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //删除数据
    @Transactional
    @Override
    public void deleteList(Integer id) {

        //删除部门
        deptMapper.deleteList(id);
        //删除该部门下的所有员工
        empMapper.deleteEmp(id);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insertList(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    //回显
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }


}
