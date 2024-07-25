package com.example.growith.supportservice.contact;

import com.example.growith.supportservice.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final SendMailService sendMailService;

    public void create(Contact contact) {
        contact.setDate(LocalDateTime.now());
        contactRepository.save(contact);
        try {
            sendMailService.sendMail(contact);
        } catch (Exception e) {
            System.out.println("Send mail failed: " + e.getMessage());
        }
    }

    public void delete(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
        } else {
            throw new DataNotFoundException("문의사항을 찾을 수 없습니다.");
        }
    }
}
