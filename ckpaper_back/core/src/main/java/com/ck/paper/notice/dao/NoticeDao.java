package com.ck.paper.notice.dao;

import com.ck.paper.notice.po.Notice;
import com.ck.paper.util.Result;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Component
public interface NoticeDao {
    String getPassword(String id);

    int deleteByPrimaryKey(String id);

    int deleteByIdAndPassword(String id,String deletePassword);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> getNotice(Notice notice);

    List<Notice> getNoticeByUserId(String userId);
}