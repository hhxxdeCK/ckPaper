package com.ck.paper.dynamic.service;

import com.ck.paper.dynamic.dao.DynamicDao;
import com.ck.paper.dynamic.po.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/14 15:18
 * @return
 */
@Service
public class DynamicServiceImpl implements DynamicService{
    @Autowired
    private DynamicDao dynamicDao;

    @Override
    public List<Dynamic> getDynamic() {
        return dynamicDao.getDynamic();
    }

    @Override
    public int insertDynamic(Dynamic dynamic) {
        return dynamicDao.insertSelective(dynamic);
    }
}
