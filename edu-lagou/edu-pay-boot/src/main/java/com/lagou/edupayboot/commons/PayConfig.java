package com.lagou.edupayboot.commons;

/**
 * @BelongsProject: lagou-edu-web
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-09-23 17:49
 * @Description: 微信支付商户的配置类（运行不了的商户信息）
 */
public class PayConfig {
    //企业公众号ID
    public static String appid="wx4040fa5278c811eb";
    //财付通平台的商户帐号
    public static String partner="1219860701";
    //财付通平台的商户密钥
    public static String partnerKey="t2o2p11l0dgd1a5ojdar7a3qferfid11";
    //回调URL
    public static String notifyurl="http://edufront.lagou.com/front/pay/wxCallback";
}