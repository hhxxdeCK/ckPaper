package com.ck.paper.notice.controller;

import com.ck.paper.notice.service.PicturesService;
import com.ck.paper.util.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/16 0:44
 * @return
 */
@Slf4j
@RestController
@RequestMapping("/pictures")
public class PicturesController {
    @Autowired
    private PicturesService picturesService;


    @PostMapping
    public Map<String,Object> uploadQusImages(@RequestParam("usersFile") String usersFile){
        if(usersFile != null){
            log.info("接受到图片"+usersFile);
            return picturesService.uploadQusImages(usersFile);
        }else{
            log.info("questionImages is null");
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code", Constants.RESULT_NULL);
            return map;
        }
    }

}
