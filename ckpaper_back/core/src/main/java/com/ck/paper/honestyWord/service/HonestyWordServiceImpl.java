package com.ck.paper.honestyWord.service;

import com.ck.paper.honestyWord.dao.HonestyWordDao;
import com.ck.paper.honestyWord.po.HonestyWord;
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
public class HonestyWordServiceImpl implements HonestyWordService {

    @Autowired
    private HonestyWordDao honestyWordDao;

    @Override
    public HonestyWord getHonestyWords() {
        return honestyWordDao.getHonestyWords();
    }
}
