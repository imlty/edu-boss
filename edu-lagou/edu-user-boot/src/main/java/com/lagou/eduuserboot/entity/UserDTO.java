package com.lagou.eduuserboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-16 11:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO<User> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int state;  // 操作状态
    private String message;  // 状态描述
    private User content;  // 响应内容
}
