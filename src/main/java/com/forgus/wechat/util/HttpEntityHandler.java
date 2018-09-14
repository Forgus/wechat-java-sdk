/**
 * @Copyright@wantdo.com 2015
 */

package com.forgus.wechat.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

/**
 * @author: xianlai.zhang
 * @version: 1.0.0
 * @email: <a href="@mail:a208zhangxianlai@163.com">联系作者</a>
 * @date: 2015/7/28 - 下午2:30
 */
public abstract class HttpEntityHandler<T> implements ResponseHandler<T> {



    public T handleResponse(HttpResponse response) throws IOException {
        //int code = response.getStatusLine().getStatusCode();
        /*if (code != HttpStatus.SC_OK) {
            throw new HttpStatusException(code, response.toString());
        }*/
        return handle(response.getEntity());
    }

    public abstract T handle(HttpEntity entity) throws IOException;

    public abstract String getName();

}
