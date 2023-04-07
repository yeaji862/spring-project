package com.example.petsProject.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Reply {

    private int reply_num;
    private String user_id;

    private String user_name;

    private int post_num;

    private int comment_num;

    private String reply_content;

    private Date reply_regdate;

    private Date reply_update_date;

    private String user_img_file;
}
