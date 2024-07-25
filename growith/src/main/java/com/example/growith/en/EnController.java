package com.example.growith.en;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.growith.supportservice.contact.Contact;
import com.example.growith.supportservice.contact.ContactService;
import com.example.growith.supportservice.contact.ContactType;
import com.example.growith.supportservice.contact.ContactTypeService;
import com.example.growith.supportservice.faq.Faq;
import com.example.growith.supportservice.faq.FaqService;
import com.example.growith.supportservice.notice.Notice;
import com.example.growith.supportservice.notice.NoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/en")
@Controller
public class EnController {
	
	
	private final NoticeService noticeService;
	private final FaqService faqService;
	private final ContactService contactService;
	private final ContactTypeService contactTypeService;
	
    @GetMapping("/index")
    public String index() {
        return "/en/index";
    }

    @GetMapping("/about/overview")
    public String overview() {
        return "/en/about_overview";
    }

    @GetMapping("/about/team")
    public String team() {
        return "/en/about_team";
    }

    @GetMapping("/about/history")
    public String history() {
        return "/en/about_history";
    }

    @GetMapping("/projects/current")
    public String current() {
        return "/en/projects_current";
    }

    @GetMapping("/projects/projects")
    public String projects() {
        return "/en/projects_projects";
    }
    @GetMapping("/projects/gallery")
    public String gallery() {
        return "/en/projects_gallery";
    }


    @GetMapping("/support/notice")
    public String getNotices(@RequestParam(name="page" ,defaultValue = "0") int page, Model model) {
        int pageSize = 5; // 페이지당 표시할 공지사항 수
        if (page < 0) {
            page = 0;
        }
        Page<Notice> noticesPage = noticeService.findAll(PageRequest.of(page, pageSize));
        model.addAttribute("notices", noticesPage.getContent());
        model.addAttribute("totalPages", noticesPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/en/en_support_notice";
    }
    
    @GetMapping("/support/faq")
    public String getFaqs(@RequestParam(name="page" ,defaultValue = "0") int page, Model model) {
        int pageSize = 5; // 페이지당 표시할 공지사항 수
        if (page < 0) {
            page = 0;
        }
        Page<Faq> faqsPage = faqService.findAll(PageRequest.of(page, pageSize));
        model.addAttribute("faqs", faqsPage.getContent());
        model.addAttribute("totalPages", faqsPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/en/en_support_faq";
    }

    @GetMapping("/support/contact")
    public String create(Model model) {
        List<ContactType> contactTypeList = contactTypeService.getAllContactTypes();
        model.addAttribute("contactTypeList", contactTypeList);
        model.addAttribute("contact", new Contact());
        return "/en/en_support_contact";
    }

    @PostMapping("/support/contact")
    public String create(@Valid @ModelAttribute Contact contact) {
        contact.setDate(LocalDateTime.now());
        contactService.create(contact);
        return "/en/en_email_sendcompleted";
    }

    @GetMapping("/investor/info/details")
    public String details() {
        return "/en/investor_info_details";
    }

    @GetMapping("/investor/info/financial")
    public String financial() {
        return "/en/investor_info_financial";
    }

    @GetMapping("/investor/info/press")
    public String press() {
        return "/en/investor_info_press";
    }



}
