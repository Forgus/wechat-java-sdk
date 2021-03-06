package com.forgus.wechat.sdk.result;

import com.forgus.wechat.sdk.result.base.BaseResult;
import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class WechatUser extends BaseResult {

    private int total;
    private int count;
    private OpenIdResult data;
    private String next_openid;
}
