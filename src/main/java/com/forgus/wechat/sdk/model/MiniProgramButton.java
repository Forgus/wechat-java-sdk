package com.forgus.wechat.sdk.model;

import com.forgus.wechat.sdk.constant.ButtonTypeEnum;
import lombok.Getter;

/**
 * @author Wenbin Chen
 */
@Getter
public class MiniProgramButton extends ViewButton {

    private String appid;
    private String pagepath;

    private MiniProgramButton() {
    }

    private MiniProgramButton(String name,String url,String appid,String pagepath) {
        this.name = name;
        this.url = url;
        this.appid = appid;
        this.pagepath = pagepath;
    }

    public static MiniProgramButton build(String name,String url,String appid,String pagepath) {
        return new MiniProgramButton(name,url,appid,pagepath);
    }


    @Override
    public String getType() {
        return ButtonTypeEnum.miniprogram.name();
    }
}
