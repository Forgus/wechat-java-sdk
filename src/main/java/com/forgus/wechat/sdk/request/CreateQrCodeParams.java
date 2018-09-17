package com.forgus.wechat.sdk.request;

import com.forgus.wechat.sdk.constant.QrCodeTypeEnum;
import com.forgus.wechat.sdk.model.QRInfo;
import com.forgus.wechat.sdk.model.QRScene;
import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class CreateQrCodeParams {

    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private Integer expire_seconds;
    /**
     * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    private String action_name;
    private QRInfo action_info;

    private CreateQrCodeParams() {

    }

    public static CreateQrCodeParams buildTemp(Integer sceneId, Integer expireSeconds) {
        CreateQrCodeParams params = new CreateQrCodeParams();
        params.setAction_name(QrCodeTypeEnum.QR_SCENE.name());
        params.setExpire_seconds(expireSeconds);
        params.setAction_info(QRInfo.build().setScene(QRScene.build(sceneId)));
        return params;
    }

    public static CreateQrCodeParams buildTemp(String sceneStr, Integer expireSeconds) {
        CreateQrCodeParams params = new CreateQrCodeParams();
        params.setAction_name(QrCodeTypeEnum.QR_STR_SCENE.name());
        params.setExpire_seconds(expireSeconds);
        params.setAction_info(QRInfo.build().setScene(QRScene.build(sceneStr)));
        return params;
    }

    public static CreateQrCodeParams buildPerpetual(Integer sceneId) {
        CreateQrCodeParams params = new CreateQrCodeParams();
        params.setAction_name(QrCodeTypeEnum.QR_LIMIT_SCENE.name());
        params.setAction_info(QRInfo.build().setScene(QRScene.build(sceneId)));
        return params;
    }

    public static CreateQrCodeParams buildPerpetual(String sceneStr) {
        CreateQrCodeParams params = new CreateQrCodeParams();
        params.setAction_name(QrCodeTypeEnum.QR_LIMIT_STR_SCENE.name());
        params.setAction_info(QRInfo.build().setScene(QRScene.build(sceneStr)));
        return params;
    }

}
