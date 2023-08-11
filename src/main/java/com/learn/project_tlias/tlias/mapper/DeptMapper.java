package com.learn.project_tlias.tlias.mapper;

import com.learn.project_tlias.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface DeptMapper {
    //回显
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 真正上操作数据库
     *
     * @return
     */
    //查询所有数据库
    @Select("select * from dept")
    List<Dept> list();

    //删除数据库
    @Delete("delete from dept where id=#{id}")
    void deleteList(Integer id);

    //新增数据
    @Insert("insert into dept(name,create_time,update_time) value (#{name},#{createTime},#{updateTime})")
    void insertList(Dept dept);

    //修改数据
    @Update("update dept set name = #{name},create_time=#{createTime},update_time=#{updateTime} where id = #{id}")
    void update(Dept dept);

}
