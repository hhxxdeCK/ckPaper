package com.ck.paper.feign;

import com.ck.paper.feign.config.FeignHeadConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/20 23:44
 * @return
 */

@FeignClient(value = "AUTH",configuration = FeignHeadConfiguration.class)
public interface UserFeign {
        @RequestMapping(value = "/user/id", method = RequestMethod.GET)
        public String getUserName(@RequestParam(value = "id") String id);
}
