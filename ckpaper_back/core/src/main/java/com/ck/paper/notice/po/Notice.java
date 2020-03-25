package com.ck.paper.notice.po;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * notice
 * @author 
 */
@Data
public class Notice implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别
     */
    private String category;

    /**
     * 丢失起始时间
     */
    private Date startTime;

    /**
     * 丢失地点
     */
    private String address;

    /**
     * 悬赏金额
     */
    private Integer amount;

    /**
     * 启事有效期
     */
    private Integer expirationDate;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 删除密码
     */
    private String deletePassword;

    /**
     * 物品图片
     */
    private String picture;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否可用
     */
    private String isActive;

    /**
     * 丢失终止时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 招领启事或者失物启事
     */
    private String forR;

    /**
     * 联系人
     */
    private String contactPerson;

    private String qqNumber;

    private String email;

    private String phone;

    private static final long serialVersionUID = 1L;
}