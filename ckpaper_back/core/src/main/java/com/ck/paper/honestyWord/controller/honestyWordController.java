package com.ck.paper.honestyWord.controller;

import com.ck.paper.honestyWord.service.HonestyWordService;
import com.ck.paper.util.Result;
import com.ck.paper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @author ck
 * @description 名言警句模块
 * @date 2020/3/14 14:33
 * @return
 */
@RestController
@RequestMapping("/honestyWord")
public class honestyWordController {
        @Autowired
        private HonestyWordService honestyWordService;

        @GetMapping
        public Result<?> getHonestyWord(){
            return ResultUtil.success(honestyWordService.getHonestyWords());
        }
    }
