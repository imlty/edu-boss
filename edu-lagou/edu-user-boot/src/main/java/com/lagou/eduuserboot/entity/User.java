package com.lagou.eduuserboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-16 10:50
 * @Description:
 */
@Data  //get和set都全部生成了
@Table(name="user")
public class User implements Serializable {
    private static final long serialVersionUID = -89788707895046947L;
    /**
     * 用户id
     */
    @Id
    private Integer id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户头像地址
     */
    private String portrait;
    /**
     * 注册手机
     */
    private String phone;
    /**
     * 用户密码（可以为空，支持只用验证码注册、登录）
     */
    private String password;
    /**
     * 注册ip
     */
    private String regIp;
    /**
     * 是否有效用户
     */
    private Object accountNonExpired;
    /**
     * 账号是否未过期
     */
    private Object credentialsNonExpired;
    /**
     * 是否未锁定
     */
    private Object accountNonLocked;
    /**
     * 用户状态：ENABLE能登录，DISABLE不能登录
     */
    private String status;
    /**
     * 是否删除
     */
    private Object isDel;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 记录更新时间
     */
    private Date updateTime;

}
