package com.ck.paper.letter.dao;

import com.ck.paper.letter.po.ThankLetter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ThankLetterDao {
    List<ThankLetter> getLetters();

    int deleteByPrimaryKey(String id);

    int insert(ThankLetter record);

    int insertSelective(ThankLetter record);

    ThankLetter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThankLetter record);

    int updateByPrimaryKey(ThankLetter record);

    void getHonestyWords();
}