package com.education.youngnam.domain.auth.model.dto;

import lombok.Data;

@Data//lombok 을 활용
public class LoginReq { // 사용자가 입력한 값
    private String email;
    private String password;
}
