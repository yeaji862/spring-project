package com.example.petsProject.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

    private String user_id;
    private String user_pass;
    private String user_name;
    private String user_phone;
    private String user_img_file;
    private String animal_type;
    private String animal_birth;
    private String animal_gender;
    private int user_follower;
    private int user_following;
    private LoginType login_type;


}
