package com.learn.project_tlias.tlias.service;

import com.learn.project_tlias.tlias.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询全部部门数据
     */
    List<Dept> list();

    /**
     * 删除数据
     */
    void deleteList(Integer id);

    /**
     * 新增数据
     * @param dept
     */
    void insert(Dept dept);

    /**
     * 修改数据
     * @param dept
     */
    void update(Dept dept);
    //根据ID回显
    Dept getById(Integer id);
}
