package com.forgus.wechat.sdk.result;

import com.forgus.wechat.sdk.result.base.BaseResult;

/**
 * Created by Nick on 21/11/2016.
 */
public class QrCode extends BaseResult {

    private static final long serialVersionUID = 2493689552150381696L;

    private String ticket;
    private Integer expire_seconds;
    private String url;


    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Integer expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
