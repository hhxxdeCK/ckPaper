package com.ck.paper.honestyWord.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * honesty_word
 * @author 
 */
@Data
public class HonestyWord implements Serializable {
    private Integer id;

    private String content;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String isActive;
    private static final long serialVersionUID = 1L;
}