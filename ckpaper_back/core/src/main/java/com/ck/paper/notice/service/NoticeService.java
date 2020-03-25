package com.ck.paper.notice.service;

import com.ck.paper.notice.po.Notice;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/15 15:29
 * @return
 */
@Service
public interface NoticeService {
    int insertNotice(Notice notice);

    List<Notice> getNotice(Notice notice);

    List<Notice> getNoticeByUserId(Notice notice);

    int deleteNoticeById(String id,String deletePassword);

    String getPassword(String id);

    int deleteByPrimaryKey(String id);
}
