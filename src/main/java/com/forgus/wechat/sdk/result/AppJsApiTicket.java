package com.forgus.wechat.sdk.result;


import com.forgus.wechat.sdk.result.base.BaseResult;

/**
 * Created by penghaiping on 2018/1/12.
 */
public class AppJsApiTicket extends BaseResult {

    private static final long serialVersionUID = 907284848284284284L;

    private String ticket;
    private String expires_in;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
