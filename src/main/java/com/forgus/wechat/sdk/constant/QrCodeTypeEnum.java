package com.forgus.wechat.sdk.constant;

/**
 * 二维码类型
 * @author Wenbin Chen
 */
public enum QrCodeTypeEnum {
    /**
    整型临时二维码
     */
    QR_SCENE,
    /**
     * 字符串临时二维码
     */
    QR_STR_SCENE,
    /**
     * 整型永久二维码
     */
    QR_LIMIT_SCENE,
    /**
     * 字符串永久二维码
     */
    QR_LIMIT_STR_SCENE
}
