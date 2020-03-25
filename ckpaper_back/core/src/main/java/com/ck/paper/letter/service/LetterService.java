package com.ck.paper.letter.service;

import com.ck.paper.letter.po.ThankLetter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/13 15:23
 * @return
 */
@Service
public interface LetterService {
    List<ThankLetter> getLetters();

    void addLetter(ThankLetter thankLetter);
}
