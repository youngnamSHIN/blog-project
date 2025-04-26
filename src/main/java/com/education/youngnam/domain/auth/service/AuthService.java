package com.education.youngnam.domain.auth.service;

import com.education.youngnam.domain.auth.model.dto.SignupReq;
import com.education.youngnam.domain.member.model.Member;
import com.education.youngnam.domain.member.repository.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.SQLException;


@Service  // 서비스 레이어, 컨트롤러에서 전달받은 데이터를 처리해주는 곳
public class AuthService {// 컨트롤러로부터 전달받은 데이터를 가지고 비즈니스 로직을처리
    private final MemberRepository memberRepository;
    public AuthService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public int register(SignupReq signupReq) throws SQLException { //원래 여기에 회원 저장, 중복체크 등의 로직이 들어가야하고
        if(this.isDuplicatedEmail(signupReq.getEmail())) { // 이메일 중복 검사
            System.out.println("이메일이 중복됩니다.");
            return 0;
        }
        Member toSaveMember = new Member();
        toSaveMember.registerMember(signupReq.getEmail(), this.encryptPassword(signupReq.getPassword()),signupReq.getName(), signupReq.getPhone(), signupReq.getAddress()); // 여기메서드에 전달 해주는 역할
        return memberRepository.save(toSaveMember);
    }

    public boolean isDuplicatedEmail(String email) throws SQLException {
        Member member = memberRepository.findByEmail(email);
        return member != null;
    }

    private String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt()); // 비밀번호 보안
    }


}
