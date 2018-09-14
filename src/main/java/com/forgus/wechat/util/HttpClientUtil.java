package com.forgus.wechat.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
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
    public static final int TIME_OUT = Integer.getInteger("http.timeout", 60000);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36";


    public static HttpClient HTTP_CLIENT = bulidHttpClient();

    private static HttpClient bulidHttpClient() {

        SchemeRegistry schemeRegistry = SchemeRegistryFactory.createDefault();

        X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme sch = new Scheme("https", 443, socketFactory);
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(sch);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(800);
        cm.setDefaultMaxPerRoute(200);

        cm.setMaxPerRoute(new HttpRoute(new HttpHost("localhost")), 500);
        cm.setMaxPerRoute(new HttpRoute(new HttpHost("127.0.0.1")), 500);
        HttpParams defaultParams = new BasicHttpParams();

        defaultParams.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, TIME_OUT);
        defaultParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);//连接超时
        defaultParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);//读取超时

        defaultParams.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
        defaultParams.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, UTF8.name());
        defaultParams.setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);


        HttpClient client = new DefaultHttpClient(cm, defaultParams);
        client = new DecompressingHttpClient(client);
        return client;
    }


    public static String get(String url) throws IOException {
        return get(url, null, null);
    }

    public static String get(String url, Map<String, String> headers) throws IOException {
        return get(url, headers, null);
    }

    public static String get(String url, Map<String, String> headers, final Charset forceCharset) throws IOException {
        HttpGet get = new HttpGet(url);
        return execute(get, headers, forceCharset);
    }



    public static String post(String url, Map<String, String> params) throws IOException {
        return post(url,params,null,Consts.UTF_8);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        return post(url,params,headers,Consts.UTF_8);
    }

    public static String post(String url, String body, Map<String, String> headers) throws IOException {
        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            post.setEntity(new StringEntity(body, Consts.UTF_8));
        }
        return execute(post, headers, null);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers, Charset charset) throws IOException {
        HttpPost post = new HttpPost(url);
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> ps = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> kv : params.entrySet()) {
                ps.add(new BasicNameValuePair(kv.getKey(), kv.getValue()));
            }
            post.setEntity(new UrlEncodedFormEntity(ps, Consts.UTF_8));
        }
        return execute(post, headers, charset);
    }

    public static String post(String url, String body, Map<String, String> headers, Charset charset) throws IOException {
        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            post.setEntity(new StringEntity(body, Consts.UTF_8));
        }
        return execute(post, headers, charset);
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
            logger.info(handler.getName()+" "+request.getURI()+" ,cost:"+(System.currentTimeMillis() - begin)+"ms");
        }
    }

    /**
     * 字符编码检查
     *
     * @param html
     * @param use
     * @return
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
