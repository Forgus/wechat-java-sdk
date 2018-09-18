package com.forgus.wechat.sdk;

import com.forgus.wechat.sdk.result.UserInfo;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wenbin Chen
 */
public class UserApiTest extends ApiTest {

    @Test
    public void getUserInfo() throws Exception {
        UserInfo userInfo = UserApi.getUserInfo(openId);
        Assert.assertEquals("Perfectionist",userInfo.getNickname());
    }

    @Test
    public void getOpenIds() throws Exception {
        Set<String> openIds = UserApi.getOpenIds();
        Assert.assertEquals(1,openIds.size());
    }

}