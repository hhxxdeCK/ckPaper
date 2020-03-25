package com.ck.paper.notice.service;

import com.ck.paper.notice.dao.NoticeDao;
import com.ck.paper.notice.po.Notice;
import com.ck.paper.util.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/16 0:45
 * @return
 */
@Service
@Slf4j
public class PicturesServiceImpl implements PicturesService{
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public Map<String,Object> uploadQusImages(String questionImages) {
        Map<String,Object> map = new HashMap<String,Object>();
        Notice notice = new Notice();
        notice.setPicture(questionImages);
        notice.setId("5");
        // 将上传成功图片全部添加到数据库中
        int i = noticeDao.updateByPrimaryKeySelective(notice);
        // 图片插入结果
        if(i==1){
            map.put("code",Constants.PICTURES_RESULT_OK);
        }else{
            map.put("code",Constants.PICTURES_RESULT_ERROR);
        }
        return null;
    }
}
