package com.forgus.wechat.service;

import com.forgus.wechat.sdk.BaseWechatApi;
import com.forgus.wechat.sdk.UserApi;
import com.forgus.wechat.vo.InviteNoticeVO;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wenbin Chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateMsgServiceTest {

    @Autowired
    private TemplateMsgService templateMsgService;

    @Test
    public void pushInviteTemplateMsg() throws Exception {
        BaseWechatApi.refreshAppAccessToken();
        Set<String> openIds = UserApi.getOpenIds();
        for(String openId : openIds) {
            InviteNoticeVO noticeVO = new InviteNoticeVO();
            noticeVO.setTitle("test");
            noticeVO.setSponsor("PingPong");
            noticeVO.setSubject("“SHARK集会”暨PingPong3.0新产品发布会");
            noticeVO.setTime("2018年9月18日");
            noticeVO.setAddress("深圳君悦酒店");
            noticeVO.setDescription("现场更将发布开走特斯拉的规则，赶紧点击报名~");
            templateMsgService.pushInviteTemplateMsg(openId,noticeVO);
        }
    }

}