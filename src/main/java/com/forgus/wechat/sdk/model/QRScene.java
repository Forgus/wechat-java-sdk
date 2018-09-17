package com.forgus.wechat.sdk.model;

import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class QRScene {

    /**
     * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    private Integer scene_id;
    /**
     * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    private String scene_str;

    private QRScene() {

    }

    public static QRScene build(Integer sceneId) {
        QRScene qrScene = new QRScene();
        qrScene.setScene_id(sceneId);
        return qrScene;
    }

    public static QRScene build(String sceneStr) {
        QRScene qrScene = new QRScene();
        qrScene.setScene_str(sceneStr);
        return qrScene;
    }

}
