package com.forgus.wechat.sdk;


import com.forgus.wechat.sdk.result.UserAccessToken;

/**
 * Created by Nick on 21/11/2016.
 */
public class AuthorizeApi extends BaseWechatApi {
    private final static String GET_ACCESS_TOKEN_URL_TEMPLATE = "%s/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    private static final String REFRESH_ACCESS_TOKEN_URL_TEMPLATE = "%s/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    public static UserAccessToken getUserAccessToken(String code) throws Exception {
        String url = String.format(GET_ACCESS_TOKEN_URL_TEMPLATE, API_URL, APP_ID, APP_SECRET, code);
        return get(url, UserAccessToken.class);
    }

    public static UserAccessToken refreshUserAccessToken(String refreshToken) throws Exception {
        String url = String.format(REFRESH_ACCESS_TOKEN_URL_TEMPLATE,API_URL,APP_ID,refreshToken);
        return get(url,UserAccessToken.class);
    }
}
