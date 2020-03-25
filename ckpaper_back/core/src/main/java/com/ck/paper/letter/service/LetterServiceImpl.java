package com.ck.paper.letter.service;

import com.ck.paper.letter.dao.ThankLetterDao;
import com.ck.paper.letter.po.ThankLetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/13 15:37
 * @return
 */
@Service
public class LetterServiceImpl implements LetterService{

    @Autowired
    private ThankLetterDao thankLetterDao;
    @Override
    public List<ThankLetter> getLetters() {
        return thankLetterDao.getLetters();
    }

    @Override
    public void addLetter(ThankLetter thankLetter) {
        thankLetterDao.insertSelective(thankLetter);
    }
}
