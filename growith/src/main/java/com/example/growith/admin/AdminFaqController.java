package com.example.growith.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.growith.HtmlSanitizerService;
import com.example.growith.supportservice.faq.Faq;
import com.example.growith.supportservice.faq.FaqService;

import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin/faq")
@RequiredArgsConstructor
@Controller
public class AdminFaqController {
    private final AdminFaqService adminFaqService;
    private final FaqService faqService;
    //html 정리하여 악성 태그 혹은 스크립트 제거
    private final HtmlSanitizerService htmlSanitizerService;
    private final AdminNoticeService adminNoticeService;
    
    
//    @GetMapping("/manager")
//    public String getFaqList(Model model) {
//        List<Faq> faqList = adminFaqService.getAllFaqs();
//        model.addAttribute("faqs", faqList);
//        return "admin_faq_manager";
//    }
    
    @GetMapping("/manager")
    public String getFaqs(@RequestParam(name="page" ,defaultValue = "0") int page, Model model) {
        int pageSize = 5; // 페이지당 표시할 공지사항 수
        if (page < 0) {
            page = 0;
        }
        Page<Faq> faqsPage = faqService.findAll(PageRequest.of(page, pageSize));
        model.addAttribute("faqs", faqsPage.getContent());
        model.addAttribute("totalPages", faqsPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin_faq_manager";
    }
    
    @GetMapping("/create")
    public String faqCreate(Model model) {
        model.addAttribute("faq", new Faq()); // 모델의 이름을 'faq'로 수정
        return "admin_faq_create";
    }

    @PostMapping("/create")
    public String faqCreate(@ModelAttribute Faq faq) {
        adminFaqService.createFaq(faq);
        return "redirect:/admin/faq/manager";
    }

    @GetMapping("/delete/faqID={id}")
    public String faqDelete(@PathVariable("id") Integer id) {
        adminFaqService.deleteFaq(id);
        return "redirect:/admin/faq/manager";
    }

    @GetMapping("/update/faqID={id}")
    public String faqUpdate(@PathVariable("id") Integer id, Model model) {
    	Faq faq = adminFaqService.getFaq(id);
        model.addAttribute("faq", faq);
        return "admin_faq_create";
    }

    @PostMapping("/update")
    public String faqUpdate(@ModelAttribute Faq faq) {
        adminFaqService.updateFaq(faq);
        return "redirect:/admin/faq/manager";
    }
}

