package com.education.youngnam.login.loginController;

import com.education.youngnam.domain.member.model.entity.Member;
import com.education.youngnam.login.loginService.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
@Controller
public class LoginController {
    LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

//    @PostMapping("/exercise")
//    public String login(Member member)throws SQLException {
//        String result = loginService.loginEmail(member);
//        if (result.equals("index")){
//            return "index";
//        }else {
//            return "redirect:/login"; // 잘못한건지 선생님께 여쭤봐야함 인터넷 찾아봐서 진행 함
//        }
//    }

    @GetMapping("/exercise/login")
    public String showLoginSuccessPage() {
        return "login";
    }

//    @GetMapping("/exercise/memberinfor") // 회원 정보를(마이페이지를 요청하면)
//    public String showMemberInfor(Model model)throws SQLException{
//        model.addAttribute("member",Member.currentMember); // 모델에 담아주기
//        System.out.println(model); //현재 로그인한 정보들이 담겨있음
//        return  "memberinfor";
//    };

}
