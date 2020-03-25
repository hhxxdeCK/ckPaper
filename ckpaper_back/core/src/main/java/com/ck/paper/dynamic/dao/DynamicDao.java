package com.ck.paper.dynamic.dao;

import com.ck.paper.dynamic.po.Dynamic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DynamicDao {
    List<Dynamic> getDynamic();

    int deleteByPrimaryKey(String id);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);

}