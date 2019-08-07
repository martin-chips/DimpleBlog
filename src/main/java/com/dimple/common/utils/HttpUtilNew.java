package com.dimple.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @className: HttpUtils
 * @description: 使用HttpClient封装的HttpUtil
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Slf4j
public final class HttpUtilNew {
    private HttpUtilNew() {
    }

    /**
     * 发送Get请求
     *
     * @param url    url
     * @param params 参数（Map类型）
     * @return 返回结果（String）
     */
    public static String doGet(String url, Map<String, String> params) {
        //创建HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    uriBuilder.addParameter(key, params.get(key));
                }
            }
            URI uri = uriBuilder.build();
            HttpGet httpGet = new HttpGet(uri);
            log.info("send get request to {}", uri.toString());
            //执行请求
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("close response error: {},{}", e.getMessage(), e);
            }
        }
        return resultString;
    }

    /**
     * 发送get请求
     *
     * @param url url
     * @return 返回结果String
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 发送Post请求
     *
     * @param url    URL
     * @param params 参数（Map）
     * @return 返回结果String
     */
    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            //创建参数列表
            if (params != null) {
                List<NameValuePair> paramsList = new LinkedList<>();
                for (String key : params.keySet()) {
                    paramsList.add(new BasicNameValuePair(key, params.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
            }
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return resultString;
    }


    /**
     * form表单Post提交
     *
     * @param url    URL
     * @param params 参数
     * @return 返回结果
     */
    public static String doFormPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = "";
        try {
            // 创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(url);
            // 配置请求参数实例
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                    .setSocketTimeout(60000)// 设置读取数据连接超时时间
                    .build();
            // 为httpPost实例设置配置
            httpPost.setConfig(requestConfig);
            // 设置请求头
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            // 封装post请求参数
            if (null != params) {
                List<NameValuePair> paramsList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramsList.add(new BasicNameValuePair(key, params.get(key)));
                }
                // 为httpPost设置封装好的请求参数
                httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
            }
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            // 关闭资源
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }


}
