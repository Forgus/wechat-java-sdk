package com.forgus.wechat.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: xianlai.zhang
 * @version: 1.0.0
 * @email: <a href="@mail:a208zhangxianlai@163.com">联系作者</a>
 * @date: 2015/7/7 - 上午12:12
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final Charset GB18030 = Charset.forName("GB18030");


    public static HttpClient HTTP_CLIENT = HttpClientBuilder.create().build();


    public static String get(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        return execute(get, null, UTF8);
    }


    public static String post(String url, String body) throws IOException {
        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            post.setEntity(new StringEntity(body, Consts.UTF_8));
        }
        return execute(post, null, UTF8);
    }

    private static String execute(final HttpRequestBase request, Map<String, String> headers, final Charset forceCharset) throws IOException {
        return http(HTTP_CLIENT, request, headers, new HttpEntityHandler<String>() {
            @Override
            public String handle(HttpEntity entity) throws IOException {
                if (entity == null) {
                    return null;
                }
                byte[] content = EntityUtils.toByteArray(entity);
                if (forceCharset != null) {
                    return new String(content, forceCharset);
                }
                String html;
                Charset charset = null;
                ContentType contentType = ContentType.get(entity);
                if (contentType != null) {
                    charset = contentType.getCharset();
                }
                if (charset == null) {
                    charset = GB18030;
                }
                html = new String(content, charset);
                charset = checkMetaCharset(html, charset);
                if (charset != null) {
                    html = new String(content, charset);
                }
                return html;
            }

            public String getName() {
                return request.getMethod();
            }
        });
    }


    public static <T> T http(HttpClient client, HttpRequestBase request, Map<String, String> headers, HttpEntityHandler<T> handler)
        throws IOException {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> kv : headers.entrySet()) {
                request.addHeader(kv.getKey(), kv.getValue());
            }
        }
        long begin = System.currentTimeMillis();
        try {
            return client.execute(request, handler, null);
        } catch (ConnectTimeoutException e) {
            logger.error(" catch ConnectTimeoutException ,closeExpiredConnections &  closeIdleConnections for 30 s. ");
            client.getConnectionManager().closeExpiredConnections();
            client.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
            throw e;
        } finally {
            logger.info(handler.getName() + " " + request.getURI() + " ,cost:" + (System.currentTimeMillis() - begin) + "ms");
        }
    }

    /**
     * 字符编码检查
     */
    private static Charset checkMetaCharset(String html, Charset use) {
        String magic = "charset=";
        int index = html.indexOf(magic);
        if (index > 0 && index < 1000) {
            index += magic.length();
            int end = html.indexOf('"', index);
            if (end > index) {
                try {
                    String charSetString = html.substring(index, end).toLowerCase();
                    if (charSetString.length() > 10) {
                        return null;
                    }
                    //GBK GB2312 --> GB18030
                    if (charSetString.startsWith("gb")) {
                        return GB18030.equals(use) ? null : GB18030;
                    }
                    Charset curr = Charset.forName(charSetString);
                    if (!curr.equals(use)) {
                        return curr;
                    }
                } catch (Exception e) {
                    logger.error("Get MetaCharset error", e);
                }
            }
        }
        return null;
    }

}
