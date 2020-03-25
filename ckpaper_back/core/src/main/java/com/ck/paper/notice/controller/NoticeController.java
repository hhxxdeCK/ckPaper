package com.ck.paper.notice.controller;

import com.ck.paper.notice.po.Notice;
import com.ck.paper.notice.service.NoticeService;
import com.ck.paper.util.Result;
import com.ck.paper.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ck.paper.emum.ResultEnum.DELETEBYPASSWORDSUCESS;
import static com.ck.paper.emum.ResultEnum.INSERTNOTICESUCCESS;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/15 15:28
 * @return
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/userId")
    public Result<?> getNoticeByUserId(@RequestParam(value = "userId") String userId){
        Notice notice = new Notice();
        notice.setUserId(userId);
        List<Notice> noticeList = noticeService.getNoticeByUserId(notice);
        System.out.println(noticeList);
        return ResultUtil.success(noticeList);
    }

    @PutMapping("/id")
    public Result<?> deleteNoticeById(@RequestParam(value = "id") String id,@RequestParam(value = "deletePassword") String deletePassword){
        int i = noticeService.deleteNoticeById(id,deletePassword);
        if(i==0) {
            return ResultUtil.error(789,"删除密码错误");
        } else {
            return ResultUtil.success(DELETEBYPASSWORDSUCESS.getCode(),DELETEBYPASSWORDSUCESS.getMessage());
        }
    }
    @PutMapping
    public Result<?> insertNotice(@RequestBody Notice notice) {
        log.info(notice.toString());
        noticeService.insertNotice(notice);
        return ResultUtil.success(INSERTNOTICESUCCESS);
    }

    @PostMapping("/pictures")
    public Result<?> insertPictures(MultipartFile[] myfiles){
        log.info("接受到图片数据"+myfiles.toString());
        return null;
    }

    @GetMapping
    public Result<?> getNotice(@RequestParam(value = "category")String category,
                               @RequestParam(value = "startTime") String startTime,
                               @RequestParam(value = "endTime") String endTime,
                               @RequestParam(value = "address") String address,
                               @RequestParam(value = "keyWord") String keyWord,
                               @RequestParam(value = "forR") String forR) throws ParseException {
        Notice noticeSearch = new Notice();
        boolean bool = (startTime!=null && !startTime.equals("")) || (endTime!=null && !endTime.equals(""));
        if(bool) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            noticeSearch.setStartTime(sdf.parse(startTime));
            noticeSearch.setEndTime(sdf.parse(endTime));
        }
        noticeSearch.setForR(forR);
        noticeSearch.setCategory(category);
        noticeSearch.setAddress(address);
        noticeSearch.setDescription(keyWord);
        List<Notice> notice = noticeService.getNotice(noticeSearch);
        log.info(notice.toString());
        return ResultUtil.success(notice);
    }
}
