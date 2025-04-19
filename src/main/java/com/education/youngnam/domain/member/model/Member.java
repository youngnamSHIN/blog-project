package com.education.youngnam.domain.member.model;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member { // 데이터베이스와 상호작용 하기위한 객체
    private int id;
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime uppdatedAt;

    public void registerMember(String email, String password, String name, String phoneNum, String address){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
    }

}
