package com.example.hdtyglxt.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    @Column(name = "student_id")
    private String studentId;

    private Integer role;

    public static final String USER_ID = "userId";

    public static final String ACCOUNT = "account";

    public static final String NICKNAME = "nickname";

    public static final String PASSWORD = "password";

    public static final String STUDENT_ID = "studentId";

    public static final String ROLE = "role";

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public User(Integer userId ,String nickname,String studentId){
        this.userId = userId;
        this.nickname = nickname;
        this.studentId = studentId;
    }

    public User(Integer userId ,String password){
        this.userId = userId;
        this.password = password;
    }

}