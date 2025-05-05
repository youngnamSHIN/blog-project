package com.education.youngnam.common.utils.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class GmailUtility implements MailUtility {

    private final Session session;

    @Value("${gmail.username}")
    private String username;


    @Override
    public void sendMail(String to, String subject, String content) {

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));

            // 수신자 설정
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 메일 제목 설정
            message.setSubject(subject);
            // 메일 내용 설정
            message.setText(content);
            // 실제 전송 수행
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}