package com.ck.paper.honestyWord.service;

import com.ck.paper.honestyWord.po.HonestyWord;
import org.springframework.stereotype.Service;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/13 15:23
 * @return
 */
@Service
public interface HonestyWordService {
    HonestyWord getHonestyWords();
}
