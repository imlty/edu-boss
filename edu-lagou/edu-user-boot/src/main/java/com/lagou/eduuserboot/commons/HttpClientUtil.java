package com.lagou.eduuserboot.commons;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Map;

/**
 * @BelongsProject: lagou-edu-web
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-09-22 11:30
 * @Description: httpclient的封装工具类
 */
public class HttpClientUtil {

    public static String doGet(String url) {
        return doGet(url,null);
    }

    /**
     * get请求，支持request请求方式，不支持restfull方式
     *
     * @param url   请求地址
     * @param param 参数
     * @return 响应的字符串
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response =null;
        try {
            // 创建url
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                // 在url后面拼接请求参数
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http get请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 从响应对象中获取状态码（成功或失败的状态）
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("响应的状态 = " + statusCode);
            // 200表示响应成功
            if (statusCode == 200) {
                // 响应的内容字符串
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 释放资源
            try {
                if (response != null) {
                    response.close();
                }

                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
