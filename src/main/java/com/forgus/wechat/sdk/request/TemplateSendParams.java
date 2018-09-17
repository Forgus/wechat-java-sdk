package com.forgus.wechat.sdk.request;

import com.forgus.wechat.sdk.model.Keyword;
import com.forgus.wechat.sdk.model.MiniProgram;
import java.util.Map;
import lombok.Data;

/**
 * 发送模板消息请求参数
 * 注：url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
 * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
 * @author Wenbin Chen
 */
@Data
public class TemplateSendParams {

    //接收者openid
    private String touser;
    //模板ID
    private String template_id;
    //模板跳转链接，可选
    private String url;
    //跳小程序所需数据,可选
    private MiniProgram miniprogram;
    //模板数据
    private Map<String,Keyword> data;

    private TemplateSendParams() {

    }

    private TemplateSendParams(String touser, String template_id, Map<String, Keyword> data) {
        this.touser = touser;
        this.template_id = template_id;
        this.data = data;
    }

    public static TemplateSendParams build(String toUser,String templateId,Map<String,Keyword> data) {
        return new TemplateSendParams(toUser,templateId,data);
    }

    public TemplateSendParams link(String url) {
        this.setUrl(url);
        return this;
    }

    public TemplateSendParams link(MiniProgram miniProgram) {
        this.setMiniprogram(miniProgram);
        return this;
    }
}
