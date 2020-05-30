package com.ck.paper.dynamic.controller;

import com.ck.paper.dynamic.po.Dynamic;
import com.ck.paper.dynamic.service.DynamicService;
import com.ck.paper.feign.UserFeign;
import com.ck.paper.notice.service.NoticeService;
import com.ck.paper.util.Result;
import com.ck.paper.util.ResultUtil;
import com.ck.paper.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/14 15:18
 * @return
 */

@RestController
@RequestMapping("/dynamic")
public class DynamicController {
        @Autowired
        private DynamicService dynamicService;

        /*@Autowired
        private UserService userService;*/

        @Autowired
        private NoticeService noticeService;

        @Autowired
        private UserFeign userFeign;

        @GetMapping
        public Result<?> getDynamic(){
            return ResultUtil.success(dynamicService.getDynamic());
        }

        /**
         *
         * @param userId 前台获取到的启事用户id，用于查找用户昵称
         * @param person 前台获取到的当前登录的用户昵称
         * @param forR 用户判断是哪种启事
         * @param id 获取id，标记已完成，启事状态设为N
         * @return
         */
        @PostMapping
        public Result<?> insertDynamic(@RequestParam(value = "id") String id, @RequestParam(value = "forR") String forR, @RequestParam(value = "userId") String userId,@RequestParam(value = "person") String person){
                String user = userFeign.getUserName(userId);
                String content;
                // 如果为R说明是招领启事，F为失物启事
                if("R".equals(forR)){
                        content = user + "的招领启事已完成：领取人为" + person + "。时间为：" + TimeUtil.getLocalDateAndTime();
                } else {
                        content = user + "的失物启事已完成: 拾获人为" + person + "。时间为：" + TimeUtil.getLocalDateAndTime();
                }
                Dynamic dynamic = new Dynamic();
                dynamic.setContent(content);
                dynamic.setCreateTime(TimeUtil.getLocalDateAndTime());
                dynamic.setCreateUser(userId);
                dynamic.setUpdateTime(TimeUtil.getLocalDateAndTime());
                dynamic.setUpdateUser(userId);
                dynamic.setIsActive("Y");
                dynamicService.insertDynamic(dynamic);
                noticeService.deleteByPrimaryKey(id);
                return ResultUtil.success();
        }
}
