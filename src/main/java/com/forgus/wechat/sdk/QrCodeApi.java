package com.forgus.wechat.sdk;


import com.forgus.wechat.sdk.constant.QRTypeEnum;
import com.forgus.wechat.sdk.result.QrCode;

/**
 * Created by Nick on 21/11/2016.
 */
public class QrCodeApi extends BaseWechatApi {

    private final static String CREATE_QR_CODE_URL_TEMPLATE = "%s/cgi-bin/qrcode/create?access_token=%s";
    private final static String QR_CODE_INTEGER_PARAM_TEMPLATE = "{\"action_name\": \"%s\", \"expire_seconds\":%s, \"action_info\": {\"scene\": {\"scene_id\": %s}}}";
    private final static String QR_CODE_STRING_PARAM_TEMPLATE = "{\"action_name\": \"%s\", \"expire_seconds\":%s, \"action_info\": {\"scene\": {\"scene_str\": %s}}}";


    public static String getAccessToken() {
        return accessToken;
    }

    public static String getJsApiTicket() {
        return jsApiTicket;
    }

    /**
     * 创建带字符串参数的临时二维码ticket
     *
     * @param sceneStr 场景值ID（字符串形式的ID），长度限制为1到64
     * @param expire_seconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    public static QrCode createTempQrCode(String sceneStr, int expire_seconds) throws Exception {
        String postBody = String.format(QR_CODE_STRING_PARAM_TEMPLATE, QRTypeEnum.QR_STR_SCENE.name(), expire_seconds, sceneStr);
        return post(getQrCodeApiUrl(), postBody, QrCode.class);
    }

    /**
     * 创建带整型参数的临时二维码ticket
     *
     * @param sceneId 场景值ID，32位非0整型
     * @param expire_seconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    public static QrCode createTempQrCode(int sceneId, int expire_seconds) throws Exception {
        String postBody = String.format(QR_CODE_INTEGER_PARAM_TEMPLATE, QRTypeEnum.QR_SCENE.name(), expire_seconds, sceneId);
        return post(getQrCodeApiUrl(), postBody, QrCode.class);
    }

    /**
     * 创建带整型参数的永久二维码ticket
     *
     * @param sceneId 场景值ID，最大值为100000（目前参数只支持1--100000）
     */
    public static QrCode createPerpetualpQrCode(int sceneId) throws Exception {
        String postBody = String.format(QR_CODE_INTEGER_PARAM_TEMPLATE, QRTypeEnum.QR_LIMIT_SCENE.name(), 0, sceneId);
        return post(getQrCodeApiUrl(), postBody, QrCode.class);
    }

    /**
     * 创建带字符串参数的永久二维码ticket
     *
     * @param sceneStr 场景值ID（字符串形式的ID），长度限制为1到64
     */
    public static QrCode createPerpetualpQrCode(String sceneStr) throws Exception {
        String postBody = String.format(QR_CODE_STRING_PARAM_TEMPLATE, QRTypeEnum.QR_LIMIT_STR_SCENE.name(), 0, sceneStr);
        return post(getQrCodeApiUrl(), postBody, QrCode.class);
    }


    private static String getQrCodeApiUrl() {
        return String.format(CREATE_QR_CODE_URL_TEMPLATE, API_URL, accessToken);
    }


}
