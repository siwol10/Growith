package com.example.growith.supportservice.contact;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/support")
@RequiredArgsConstructor
@Controller
@Validated
public class ContactController {
    private final ContactService contactService;
    private final ContactTypeService contactTypeService;

    @GetMapping("/contact")
    public String create(Model model) {
        List<ContactType> contactTypeList = contactTypeService.getAllContactTypes();
        model.addAttribute("contactTypeList", contactTypeList);
        model.addAttribute("contact", new Contact());
        return "support_contact";
    }

    @PostMapping("/contact")
    public String create(@Valid @ModelAttribute Contact contact) {
        contact.setDate(LocalDateTime.now());
        contactService.create(contact);
        return "email_sendcompleted";
    }

//    @PostMapping("/contact/type")
//    public ResponseEntity<String> updateContactType(@RequestParam("typeId") Integer typeId, @RequestParam("contactId") Integer contactId) {
//        Contact contact = contactService.getContact(contactId);
//        ContactType contactType = contactTypeService.findById(typeId);
//        contact.setType(contactType);
//        contactService.create(contact); // 저장
//        return ResponseEntity.ok("Contact type updated successfully!");
//    }
}
