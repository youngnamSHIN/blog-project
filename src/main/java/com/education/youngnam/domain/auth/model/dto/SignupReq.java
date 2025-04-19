package com.education.youngnam.domain.auth.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SignupReq { // 회원 가입 요청을 보낼떄 사용하는 요청 데이터 객체, DTO 데이터를 전송할떄 사용하는 객체
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
}
