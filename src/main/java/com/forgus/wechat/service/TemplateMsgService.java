package com.forgus.wechat.service;

import com.forgus.wechat.sdk.TemplateApi;
import com.forgus.wechat.sdk.model.Keyword;
import com.forgus.wechat.sdk.request.TemplateSendParams;
import com.forgus.wechat.sdk.result.base.BaseResult;
import com.forgus.wechat.vo.InviteNoticeVO;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

/**
 * @author Wenbin Chen
 */
@Service
@Slf4j
public class TemplateMsgService {

    private static final String INVITE_TEMPLATE_ID = "T1zv1YOP4vOF30ZfSbGCdQFPl_4Fb3nTfesHsGbe82s";

    public BaseResult pushInviteTemplateMsg(String openId,InviteNoticeVO inviteNoticeVO) throws Exception {
        Map<String,Keyword> data = new HashedMap();
        data.put("first", Keyword.create(inviteNoticeVO.getTitle()));
        data.put("keyword1", Keyword.create(inviteNoticeVO.getSponsor()));
        data.put("keyword2", Keyword.create(inviteNoticeVO.getSubject()));
        data.put("keyword3", Keyword.create(inviteNoticeVO.getTime()));
        data.put("keyword4", Keyword.create(inviteNoticeVO.getAddress()));
        data.put("remark", Keyword.create(inviteNoticeVO.getDescription()));
        return TemplateApi.sendTemplateMessage(TemplateSendParams.build(openId,INVITE_TEMPLATE_ID,data));
    }
}
