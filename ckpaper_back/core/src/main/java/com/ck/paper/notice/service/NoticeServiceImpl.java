package com.ck.paper.notice.service;

import com.ck.paper.notice.dao.NoticeDao;
import com.ck.paper.notice.po.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/15 15:30
 * @return
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public int insertNotice(Notice notice) {
        Date d = new Date();
        notice.setIsActive("Y");
        notice.setCreateTime(d);
        notice.setUpdateTime(d);
        return noticeDao.insert(notice);
    }

    @Override
    public List<Notice> getNotice(Notice notice) {
        return noticeDao.getNotice(notice);
    }

    @Override
    public List<Notice> getNoticeByUserId(Notice notice) {
        return noticeDao.getNoticeByUserId(notice.getUserId());
    }

    @Override
    public int deleteNoticeById(String id,String deletePassword) {
        return noticeDao.deleteByIdAndPassword(id,deletePassword);
    }

    @Override
    public String getPassword(String id) {
        return noticeDao.getPassword(id);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return noticeDao.deleteByPrimaryKey(id);
    }


}
