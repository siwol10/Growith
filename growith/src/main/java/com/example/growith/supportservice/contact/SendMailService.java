package com.example.growith.supportservice.contact;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Service
@Slf4j
public class SendMailService {
    private final JavaMailSender mailSender;

    private static final String EMAIL_TITLE_PREFIX = "Growith 문의사항: ";

    public void sendMail(Contact contact) throws MessagingException {
        String email = contact.getEmail();
//
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setFrom("hs381717@naver.com", email);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding not supported: " + e.getMessage());
        }

        helper.setReplyTo(email);
        helper.setTo("hs381717@gmail.com");
        helper.setSubject(EMAIL_TITLE_PREFIX + "[" + contact.getType().getContent() + "] " + contact.getSubject());
        helper.setText(contact.getContent() + "\n\n" + "발신자: " + contact.getName());

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("hs381717@naver.com");
//        message.setSubject(EMAIL_TITLE_PREFIX + "["+ contact.getType().getContent() + "] " + contact.getSubject());
//        message.setText(contact.getContent()+"\n\n"+"발신자: "+ contact.getName()+"\n"
//                +"연락처: "+ contact.getPhone());
//        message.setFrom(contact.getEmail());

        try {
            mailSender.send(message);
            log.info("Email sent successfully to {}", "hs381717@gmail.com");
        } catch (MailSendException e) {
            log.error("Failed to send Email: {}", e.getMessage());
        }
    }
}
