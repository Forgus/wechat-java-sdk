package com.forgus.wechat.sdk;

import com.alibaba.fastjson.JSON;
import com.forgus.wechat.sdk.request.CreateQrCodeParams;
import com.forgus.wechat.sdk.result.QrCodeTicket;
import org.junit.Test;

/**
 * @author Wenbin Chen
 */
public class QrCodeApiTest extends ApiTest {
    @Test
    public void createQrCode() throws Exception {
        QrCodeTicket codeTicket = QrCodeApi.createQrCode(CreateQrCodeParams.buildPerpetual(1));
        System.out.println(JSON.toJSONString(codeTicket));
    }

}