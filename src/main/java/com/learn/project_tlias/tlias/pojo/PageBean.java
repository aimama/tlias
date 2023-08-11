package com.learn.project_tlias.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total;     //总记录数
    private List<Emp> rows;    //当前页数列表
}
