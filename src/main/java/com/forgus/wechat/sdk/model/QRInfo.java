package com.forgus.wechat.sdk.model;

import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class QRInfo {

    private QRScene scene;

    private QRInfo() {

    }

    public static QRInfo build() {
        return new QRInfo();
    }

    public QRInfo setScene(QRScene scene) {
        this.scene = scene;
        return this;
    }
}
