package com.forgus.wechat.sdk;

import com.alibaba.fastjson.JSON;
import com.forgus.wechat.sdk.request.TemplateSendParams;
import com.forgus.wechat.sdk.result.GetIndustryResult;
import com.forgus.wechat.sdk.result.GetTemplateIdResult;
import com.forgus.wechat.sdk.result.ListTemplateResult;
import com.forgus.wechat.sdk.result.base.BaseResult;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息Api
 */
public class TemplateApi extends BaseWechatApi {

    private final static String SEND_TEMPLATE_MESSAGE_URL_TEMPLATE = API_URL + "/cgi-bin/message/template/send?access_token=%s";
    private final static String DELETE_TEMPLATE_URL_TEMPLATE = API_URL + "/cgi-bin/template/del_private_template?access_token=%s";
    private final static String GET_TEMPLATE_ID_URL_TEMPLATE = API_URL + "/cgi-bin/template/api_add_template?access_token=%s";
    private final static String SET_INDUSTRY_URL_TEMPLATE = API_URL + "/cgi-bin/template/api_set_industry?access_token=%s";
    private final static String GET_INDUSTRY_URL_TEMPLATE = API_URL + "/cgi-bin/template/get_industry?access_token=%s";
    private final static String LIST_TEMPLATE_URL_TEMPLATE = API_URL + "/cgi-bin/template/get_all_private_template?access_token=%s";


    /**
     * 设置所属行业
     *
     * @param primary 主行业id
     * @param secondary 副行业id
     */
    public static BaseResult setIndustry(String primary, String secondary) throws Exception {
        String setIndustryUrl = String.format(SET_INDUSTRY_URL_TEMPLATE, accessToken);
        Map<String, String> params = new HashMap<>(2);
        params.put("industry_id1", primary);
        params.put("industry_id2", secondary);
        return post(setIndustryUrl, JSON.toJSONString(params), BaseResult.class);
    }

    /**
     * 获取设置的行业信息
     */
    public static GetIndustryResult getIndustry() throws Exception {
        String getIndustryUrl = String.format(GET_INDUSTRY_URL_TEMPLATE, accessToken);
        return get(getIndustryUrl, GetIndustryResult.class);
    }


    /**
     * 获取模板ID
     *
     * @param templateNo 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    public static GetTemplateIdResult getTemplateId(String templateNo) throws Exception {
        String getTemplateUrl = String.format(GET_TEMPLATE_ID_URL_TEMPLATE, accessToken);
        Map<String, String> params = new HashMap<>();
        params.put("template_id_short", templateNo);
        return post(getTemplateUrl, JSON.toJSONString(params), GetTemplateIdResult.class);
    }

    /**
     * 获取模板列表
     */
    public static ListTemplateResult listTemplates() throws Exception {
        String listTemplateUrl = String.format(LIST_TEMPLATE_URL_TEMPLATE, accessToken);
        return get(listTemplateUrl, ListTemplateResult.class);
    }

    /**
     * 删除模板
     *
     * @param templateId 模板ID
     */
    public static BaseResult deleteTemplate(String templateId) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("template_id", templateId);
        return post(buildDeleteTemplateUrl(), JSON.toJSONString(params), BaseResult.class);
    }

    /**
     * 发送模板消息
     *
     * @param templateSendParams 请求参数
     */
    public static BaseResult sendTemplateMessage(TemplateSendParams templateSendParams) throws Exception {
        return post(buildSendTemplateMsgUrl(), JSON.toJSONString(templateSendParams), BaseResult.class);
    }

    private static String buildDeleteTemplateUrl() {
        return String.format(DELETE_TEMPLATE_URL_TEMPLATE, accessToken);
    }


    private static String buildSendTemplateMsgUrl() {
        return String.format(SEND_TEMPLATE_MESSAGE_URL_TEMPLATE, accessToken);
    }

}
