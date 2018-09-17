package com.forgus.wechat.sdk;

import com.forgus.wechat.BaseTest;
import org.junit.Before;

/**
 * @author Wenbin Chen
 */
public class ApiTest extends BaseTest {

    protected final String openId = "oGfLf0v7oZ92ypEA9EDhnb-q1vaw";

    @Before
    public void beforeInvoke() throws Exception {
        BaseWechatApi.refreshAppAccessToken();
    }


}
