package com.ck.paper.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_user
 * @author ck
 */
@Data
public class SysUser implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否有效  1有效  2无效
     */
    private String deleteStatus;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0：男1：女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private Integer phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 院系名称
     */
    private String facultyName;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 是否可用
     */
    private String isActive;

    private static final long serialVersionUID = 1L;
}