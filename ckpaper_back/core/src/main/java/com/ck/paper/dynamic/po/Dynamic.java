package com.ck.paper.dynamic.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * dynamic
 * @author 
 */
@Data
public class Dynamic implements Serializable {
    /**
     * 主键
     */
    private String id;

    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 动态内容
     */
    private String content;

    private String isActive;

    private static final long serialVersionUID = 1L;
}