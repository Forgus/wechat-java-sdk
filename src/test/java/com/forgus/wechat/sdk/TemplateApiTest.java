package com.forgus.wechat.sdk;

import com.forgus.wechat.BaseTest;
import com.forgus.wechat.sdk.constant.IndustryType;
import com.forgus.wechat.sdk.model.Keyword;
import com.forgus.wechat.sdk.model.Template;
import com.forgus.wechat.sdk.request.TemplateSendParams;
import com.forgus.wechat.sdk.result.GetIndustryResult;
import com.forgus.wechat.sdk.result.GetTemplateIdResult;
import com.forgus.wechat.sdk.result.ListTemplateResult;
import com.forgus.wechat.sdk.result.base.BaseResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Wenbin Chen
 */
public class TemplateApiTest extends BaseTest {

    private static final String INVITE_TEMPLATE = "YA0skfaUdnYeTm62v1B9DHbzLNjdoAiOkZtBB2O0_q8";

    @Before
    public void beforeInvoke() throws Exception {
        BaseWechatApi.refreshAppAccessToken();
    }

    @Test
    public void listTemplates() throws Exception {
        ListTemplateResult templateResult = TemplateApi.listTemplates();
        List<Template> templateList = templateResult.getTemplate_list();
        Assert.assertEquals(1, templateList.size());
        Assert.assertEquals(INVITE_TEMPLATE, templateList.get(0).getTemplate_id());
    }

    @Test
    public void setIndustry() throws Exception {
        BaseResult baseResult = TemplateApi.setIndustry(IndustryType.IT_INTERNET_E_COMMERCE, IndustryType.FINANCE_FUND_TRUST);
        Assert.assertEquals("0",baseResult.getErrcode());
    }

    @Test
    public void getIndustry() throws Exception {
        GetIndustryResult industry = TemplateApi.getIndustry();
        Assert.assertEquals("互联网|电子商务",industry.getPrimary_industry().getSecond_class());
        Assert.assertEquals("证券|基金|理财|信托",industry.getSecondary_industry().getSecond_class());
    }

    @Test
    public void getTemplate() throws Exception {
        GetTemplateIdResult templateResult = TemplateApi.getTemplateId("OPENTM413717410");
        System.out.println(templateResult);
    }

    @Test
    public void sendTemplateMessage() throws Exception {
        Map<String,Keyword> data = new HashMap<>();
        data.put("first", Keyword.create("跨境千人盛会即刻开始！干货满满，不容错过！\n"));
        data.put("keyword1", Keyword.create("为中国跨境卖家而生的PingPong"));
        data.put("keyword2", Keyword.create("PingPong 3.0鲨客集会"));
        data.put("keyword3", Keyword.create("18日13：30-17：30","#173177"));
        data.put("keyword4", Keyword.create("深圳罗湖·君悦酒店\n"));
        data.put("remark", Keyword.create("奥运冠军邓亚萍、《百家讲坛》主讲人魏新、全球十大电商平台高管都来了，不停电的涨姿势之旅，等你来观看。\n\n点击观看直播"));
        TemplateApi.sendTemplateMessage(TemplateSendParams.build(openId,INVITE_TEMPLATE,data).link("http://shangzhibo.tv/watch/1371836"));
    }

    @Test
    public void deleteTemplate() throws Exception {
        BaseResult baseResult = TemplateApi.deleteTemplate("2lHUVrgiqe0br4YkHTkjCTdkh9Tej7I51PkEd2Tnh8o");
        Assert.assertEquals(0,baseResult.getErrcode());
    }

}