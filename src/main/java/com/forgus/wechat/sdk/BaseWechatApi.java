package com.forgus.wechat.sdk;

import com.alibaba.fastjson.JSON;
import com.forgus.wechat.util.HttpClientUtil;
import org.apache.http.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Nick on 21/11/2016.
 */
public class BaseWechatApi {

    protected static Logger logger = LoggerFactory.getLogger(BaseWechatApi.class);

    protected static String accessToken;

    protected static String jsApiTicket;

    public static <T> T get(String url, Class<T> clazz) throws Exception {
        String result = HttpClientUtil.get(url, null, Consts.UTF_8);
        return JSON.parseObject(result, clazz);
    }

    public static <T> T post(String url, String postContext, Class<T> clazz) throws Exception {
        String result = HttpClientUtil.post(url, postContext, null, Consts.UTF_8);
        return JSON.parseObject(result, clazz);
    }
}
