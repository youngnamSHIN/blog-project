package com.education.youngnam.domain.auth.controller;

import com.education.youngnam.domain.auth.model.dto.SignupReq;
import com.education.youngnam.domain.auth.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;


@Controller
public class AuthController {

   private final AuthService authService; // 5. 생성자 주입을 통해 AuthService를 DI한다.

   public AuthController(AuthService authService){
        this.authService = authService;
   }

    @GetMapping("/") //메인페이지 요청시 index 화면 응답
    public String showIndexPage(){
        return  "index";
    }

    @GetMapping("/signup") // 회원가입 버튼을 눌렀을때 핸들러 맵핑을 토대로 이url을 찾음
    public String showSignupPage(){
        return "/auth/signup"; //
        //classpath:templates/thymeleaf/auth/signup 이경로 하위로 이동하게 됨
        //"/auth/signup" 이게 모델엔 뷰 객체에 뷰의 경로가 된다.
        // /singup 요청시 /auth/signup"을 응답해준다. 여기까지는 따로 post가 아니라  get 요청이기에 따로 할것은 없고 화면만 보여주면 된다.
    }

    @PostMapping("/signup") // 사용자가 post 방식으로 요청 하면 createMember메서드 실행
    public String createMember(SignupReq req) throws SQLException {// 1. req는 자료형이 SignupReq로써, 클라이언트가 회원가입을 위해 전송한 데이터들을 담고 있는 DTO다.
        // 2. 해당 req 데이터를 기반으로 실제 회원가입을 수행해줘야 한다.
        // 3. 그런데 책임분리를 위해 비즈니스 로직은 서비스 레이어에서 수행하도록 한다.
        // 4. 그래서 만들어둔 것이 AuthService인데, 스프링이니까 의존성을 주입 받아서 개발자가 객체 생성 및 관리 없이 편하게 사용하도록 해야 한다.
        int result = authService.register(req);// 6. DI된 AuthService의 register() 메서드를 호출하여, 해당 메서드에서 회원가입이 수행될 수 있도록 한다.
        // register() 메서드는 파라미터로 SignupReq를 하나 받고 있음.
        // 따라서 해당 메서드를 호출하는 곳에서는 당연히 요구하는 데이터 타입에 맞는 데이터를 넘겨야 메서드가 정상적으로 호출이 된다.
        return "index";
    }
}
