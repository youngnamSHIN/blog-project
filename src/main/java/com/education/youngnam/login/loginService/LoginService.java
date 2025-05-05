package com.education.youngnam.login.loginService;

import com.education.youngnam.domain.member.model.entity.Member;
import com.education.youngnam.domain.member.repository.impl.MemberRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class LoginService {
    private MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository)throws SQLException {
        this.memberRepository = memberRepository;
    }

//    public String loginEmail(Member member) throws SQLException {
//
//        Member foundMember =  memberRepository.LoginMember(member);
//
//        if (foundMember == null){
//            System.out.println("회원 정보가 일치하지 않습니다.");
//            return "index";
//        }else {
//            System.out.println("로그인 성공입니다.");
//            Member.currentMember = foundMember; // 로그인이 성공한다면 currentMember에 현재  member객체를 주자 그리고 login 화면으로 넘어가자
//            return "login";
//        }
//    }
}
