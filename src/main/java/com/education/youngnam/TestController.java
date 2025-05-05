package com.education.youngnam;

import com.education.youngnam.common.utils.mail.GmailUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class TestController {
    public final GmailUtility gmailUtility;

    @GetMapping("/test")
    public String test()throws SQLException {
        gmailUtility.sendMail("youngnam151109@gmail.com","자바로 메일보내기", "안녕");
        return "index";
    }
}
