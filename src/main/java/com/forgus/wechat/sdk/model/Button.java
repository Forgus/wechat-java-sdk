package com.forgus.wechat.sdk.model;

import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public abstract class Button {

    protected String name;

    public abstract String getType();

}
