package com.forgus.wechat.sdk.model;

import com.forgus.wechat.sdk.constant.ButtonTypeEnum;
import lombok.Getter;

/**
 * @author Wenbin Chen
 */
@Getter
public class ViewButton extends Button {

    protected String url;

    protected ViewButton() {

    }

    private ViewButton(String name,String url) {
        this.name = name;
        this.url = url;
    }

    public static ViewButton build(String name,String url) {
        return new ViewButton(name,url);
    }

    @Override
    public String getType() {
        return ButtonTypeEnum.view.name();
    }
}
