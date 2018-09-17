package com.forgus.wechat.sdk;


import com.alibaba.fastjson.JSON;
import com.forgus.wechat.sdk.request.CreateQrCodeParams;
import com.forgus.wechat.sdk.result.QrCodeTicket;

/**
 * Created by Nick on 21/11/2016.
 */
public class QrCodeApi extends BaseWechatApi {

    private final static String CREATE_QR_CODE_URL_TEMPLATE = API_URL + "/cgi-bin/qrcode/create?access_token=%s";


    /**
     * 创建二维码
     *
     * @param createQrCodeParams
     * @return
     * @throws Exception
     */
    public static QrCodeTicket createQrCode(CreateQrCodeParams createQrCodeParams) throws Exception {
        return post(getQrCodeApiUrl(), JSON.toJSONString(createQrCodeParams), QrCodeTicket.class);
    }


    private static String getQrCodeApiUrl() {
        return String.format(CREATE_QR_CODE_URL_TEMPLATE, accessToken);
    }


}
