package com.learn.project_tlias.tlias.mapper;

import com.learn.project_tlias.tlias.pojo.Emp;
import com.learn.project_tlias.tlias.pojo.PageBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 真正上操作数据库
     *
     * @return
     */
//    //总记录数
//    @Select("select count(id) from emp")
//    Long getTotal();
//
//    //当前数据列表
//    @Select("select * from emp limit #{page},#{pageSize}")
//    List<Emp> getRows(Integer page, Integer pageSize);
    //使用pageHelper插件
//    @Select("select * from emp where name like concat('%',#{name},'%') and gender = #{gender} and entrydate between #{begin} and #{end}")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
//    @Delete("delete from emp where id = #{id}}")
    void deleteList(List<Integer> ids);

    void insert(Emp emp);

    void update(Emp emp);

    @Select("select * from emp where id= #{id}")
    Emp getInform(Integer id);
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp login(Emp emp);
    //根据部门id删除员工信息
    @Delete("delete from emp where dept_id = #{id}")
    void deleteEmp(Integer id);
}
