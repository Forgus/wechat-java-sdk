package com.forgus.wechat.task;

import com.forgus.wechat.sdk.QrCodeApi;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Nick on 21/11/2016.
 */
@Component
public class AppAuthSchedule {
    private static Logger logger = LoggerFactory.getLogger(AppAuthSchedule.class);


    // 启动时, 立即获取 access token
    // 此后每20分钟刷新一次
    @Scheduled(initialDelay = 0,
            fixedDelay = 20 * DateUtils.MILLIS_PER_MINUTE)
    public void refreshAccessToken() throws Exception {
        logger.info("开始刷新 access_token");
        QrCodeApi.refreshAppAccessToken();
        logger.info("刷新 access_token 结束");

        //刷新完access_token立即去刷新ticket
        logger.info("开始刷新 jsapi_ticket");
        QrCodeApi.refreshJsApiTicket();
        logger.info("刷新 jsapi_ticket 结束");

    }

}
