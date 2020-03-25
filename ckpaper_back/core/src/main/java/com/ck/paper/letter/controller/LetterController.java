package com.ck.paper.letter.controller;

import com.ck.paper.feign.UserFeign;
import com.ck.paper.letter.po.ThankLetter;
import com.ck.paper.letter.service.LetterService;
import com.ck.paper.util.Result;
import com.ck.paper.util.ResultUtil;
import com.ck.paper.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ck
 * @description 感谢信模块
 * @date 2020/3/13 15:15
 */
@RestController
@RequestMapping("/letter")
public class LetterController {
    @Autowired
    private LetterService letterService;

    @Autowired
    private UserFeign userFeign;

    @GetMapping
    public Result<?> getThankLetter(){
        return ResultUtil.success(letterService.getLetters());
    }

    @PostMapping
    public Result<?> addThankLetter(@RequestParam(value = "content") String content,@RequestParam(value = "userId") String userId){
        ThankLetter thankLetter = new ThankLetter();
        thankLetter.setCreateName(userFeign.getUserName(userId));
        thankLetter.setCreatTime(TimeUtil.getLocalDateAndTime());
        thankLetter.setIsActive("Y");
        thankLetter.setUpdateTime(TimeUtil.getLocalDateAndTime());
        thankLetter.setContent(content+"   发表人："+thankLetter.getCreateName());
        this.letterService.addLetter(thankLetter);
        return ResultUtil.success(200,"感谢信添加成功");
    }
}
