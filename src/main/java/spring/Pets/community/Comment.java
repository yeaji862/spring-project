package spring.Pets.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Comment {
   private String user_id;

    private String user_name;

    private int post_num;

    private int comment_num;

    private String comment_content;

    private Date comment_regdate;

    private Date comment_update_date;

    private String user_img_file;

    private List<Reply> replyList;

}
