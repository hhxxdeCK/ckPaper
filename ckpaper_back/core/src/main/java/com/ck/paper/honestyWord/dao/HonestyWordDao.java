package com.ck.paper.honestyWord.dao;

import com.ck.paper.honestyWord.po.HonestyWord;
import org.springframework.stereotype.Component;

@Component
public interface HonestyWordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HonestyWord record);

    int insertSelective(HonestyWord record);

    HonestyWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HonestyWord record);

    int updateByPrimaryKey(HonestyWord record);

    HonestyWord getHonestyWords();
}