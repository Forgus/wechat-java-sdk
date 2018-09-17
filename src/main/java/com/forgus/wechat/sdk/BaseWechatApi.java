package com.forgus.wechat.sdk;

import com.alibaba.fastjson.JSON;
import com.forgus.wechat.sdk.result.AppAccessToken;
import com.forgus.wechat.sdk.result.AppJsApiTicket;
import com.forgus.wechat.util.HttpClientUtil;
import org.apache.http.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Nick on 21/11/2016.
 */
public class BaseWechatApi {

    protected static Logger logger = LoggerFactory.getLogger(BaseWechatApi.class);

    protected final static String API_URL = "https://api.weixin.qq.com";
    private final static String GET_ACCESS_TOKEN_URL_TEMPLATE = "%s/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    private final static String GET_JSAPI_TICKET_URL_TEMPLATE = "%s/cgi-bin/ticket/getticket?type=jsapi&access_token=%s";
    protected static String jsApiTicket;
    protected static String accessToken;
    protected final static String APP_ID = "wx0d4d7ac5b62ca48a";
    protected final static String APP_SECRET = "ba94f3584963cdc744040023415fc3fb";


    public static void refreshJsApiTicket() throws Exception {
        String url = String.format(GET_JSAPI_TICKET_URL_TEMPLATE, API_URL, accessToken);
        AppJsApiTicket appJsApiTicket = get(url, AppJsApiTicket.class);
        jsApiTicket = appJsApiTicket.getTicket();
    }

    public static AppAccessToken getAppAccessToken() throws Exception {
        String url = String.format(GET_ACCESS_TOKEN_URL_TEMPLATE, API_URL, APP_ID, APP_SECRET);
        return get(url, AppAccessToken.class);
    }

    public static void refreshAppAccessToken() throws Exception {
        AppAccessToken accessTokenResult = getAppAccessToken();
        accessToken = accessTokenResult.getAccess_token();
    }

    public static <T> T get(String url, Class<T> clazz) throws Exception {
        String result = HttpClientUtil.get(url, null, Consts.UTF_8);
        return JSON.parseObject(result, clazz);
    }

    public static <T> T post(String url, String postContext, Class<T> clazz) throws Exception {
        String result = HttpClientUtil.post(url, postContext, null, Consts.UTF_8);
        return JSON.parseObject(result, clazz);
    }
}
