package com.forgus.wechat.vo;

import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class InviteNoticeVO {

    private Integer msgId;
    private String title;
    private String sponsor;
    private String subject;
    private String time;
    private String address;
    private String description;
    private String link;

}
