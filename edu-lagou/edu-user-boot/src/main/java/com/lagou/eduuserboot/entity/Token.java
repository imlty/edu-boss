package com.lagou.eduuserboot.entity;

/**
 * @BelongsProject: lagou-edu-web
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-09-22 13:53
 * @Description: 令牌实体类
 */
public class Token {
    private String access_token	;//接口调用凭证
    private String expires_in;//	access_token接口调用凭证超时时间，单位（秒）
    private String refresh_token;//	用户刷新access_token
    private String openid;//	授权用户唯一标识
    private String scope;//	用户授权的作用域，使用逗号（,）分隔
    private String unionid;//	当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Token() {
    }

    public Token(String access_token, String expires_in, String refresh_token, String openid, String scope, String unionid) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.openid = openid;
        this.scope = scope;
        this.unionid = unionid;
    }
}
