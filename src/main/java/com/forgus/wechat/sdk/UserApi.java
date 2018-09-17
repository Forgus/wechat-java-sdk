package com.forgus.wechat.sdk;

import com.alibaba.fastjson.JSON;
import com.forgus.wechat.sdk.result.UserInfo;
import com.forgus.wechat.sdk.result.WechatUser;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户管理类 API
 * <p>
 * Created by Nick on 21/11/2016.
 */
public class UserApi extends BaseWechatApi {

    private final static String GET_USER_INFO_TEMPLATE = "%s/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    private final static String GET_USER_LIST_TEMPLATE = "%s/cgi-bin/user/get?access_token=%s";
    private final static String GET_USER_LIST_NEXT_TEMPLATE = "%s/cgi-bin/user/get?access_token=%s&next_openid=%s";

    /**
     * 获取用户基本信息（包括UnionID机制）<br/>
     * 查看<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN">微信API文档</a>
     *
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return
     */
    public static UserInfo getUserInfo(String openId) throws Exception {
        String url = String.format(GET_USER_INFO_TEMPLATE, API_URL, accessToken, openId);
        return get(url, UserInfo.class);
    }


    /**
     * 获取公众号的关注者列表（openId）
     * @return
     */
    public static Set<String> getOpenIds() throws Exception {
        String url = String.format(GET_USER_LIST_TEMPLATE, API_URL, accessToken);
        logger.info("wechat user list request url:{}",url);
        Set<String> set = new HashSet<>();
        WechatUser wechatUser = get(url, WechatUser.class);
        logger.info("wechat user list response:{}", JSON.toJSONString(wechatUser));
        set.addAll(wechatUser.getData().getOpenid());
        while (!StringUtils.isEmpty(wechatUser.getNext_openid())) {
            url = String.format(GET_USER_LIST_NEXT_TEMPLATE,API_URL,accessToken,wechatUser.getNext_openid());
            logger.info("next wechat user list request url:{}",url);
            wechatUser = get(url, WechatUser.class);
            logger.info("next wechat user list response:{}",JSON.toJSONString(wechatUser));
            if(!StringUtils.isEmpty(wechatUser.getNext_openid())) {
                set.addAll(wechatUser.getData().getOpenid());
            }
        }
        return set;
    }

    public static Set<String> getOpenIds(String nextOpenId) throws Exception {
        String url = String.format(GET_USER_LIST_NEXT_TEMPLATE,API_URL,accessToken,nextOpenId);
        logger.info("next wechat user list request url:{}",url);
        WechatUser wechatUser = get(url, WechatUser.class);
        logger.info("next wechat user list response:{}",JSON.toJSONString(wechatUser));
        Set<String> set = new HashSet<>();
        if(wechatUser.getData() == null) {
            return set;
        }
        List<String> openids = wechatUser.getData().getOpenid();
        set.addAll(openids);
        return set;
    }

}
