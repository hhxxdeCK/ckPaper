package com.ck.paper.letter.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * thank_letter
 * @author 
 */
@Data
public class ThankLetter implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 创建人姓名
     */
    private String createName;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime creatTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否可用
     */
    private String isActive;

    private static final long serialVersionUID = 1L;
}