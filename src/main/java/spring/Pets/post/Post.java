package spring.Pets.post;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Post {
    private int post_num;
    private String user_id;
    private String user_name;
    private String post_content;
    private Date post_regdate;
    private Date post_update_date;
    private String post_img_file;
    private int post_like;
    private int post_comment;
    private int post_report;
    private String user_img_file;
}
