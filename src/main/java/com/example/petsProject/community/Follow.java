package com.example.petsProject.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Follow {
    private String user_id;
    private String to_user_name;
    private String from_user_name;

    public static Follow followMapping(String user_id , String to_user_name , String from_user_name){
        Follow follow = new Follow();
        follow.setUser_id(user_id);
        follow.setTo_user_name(to_user_name);
        follow.setFrom_user_name(from_user_name);
        return follow;
    }
}
