package com.forgus.wechat.sdk.result.base;

import java.io.Serializable;

/**
 * Created by Nick on 21/11/2016.
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = -812022748827576049L;

    private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean isSuccess() {
        return errcode == null || "0".equals(errcode);
    }
}
