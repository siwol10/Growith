package com.example.growith.supportservice.faq;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/support")
@RequiredArgsConstructor
@Controller
public class FaqController {
    private final FaqService faqService;

//    @GetMapping("/faq")
//    public String faq(Model model) {
//        model.addAttribute("faqs", faqService.readList());
//        return "support_faq";
//    }

//    @GetMapping("/faq")
//    public String faq(Model model,
//                      @RequestParam(value="page", defaultValue = "0") int page) {
//        Page<Faq> paging = faqService.getFaqs(page);
//        model.addAttribute("paging", paging);
//        return "support_faq";
//    }
    
    @GetMapping("/faq")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Faq> faqPage = this.faqService.getList(page); // 페이징된 FAQ 목록
        model.addAttribute("faqs", faqPage.getContent()); // FAQ 리스트
        model.addAttribute("currentPage", page); // 현재 페이지
        model.addAttribute("totalPages", faqPage.getTotalPages()); // 총 페이지 수
        return "support_faq";
    }
}
