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
import com.example.growith.supportservice.notice.Notice;
import com.example.growith.supportservice.notice.NoticeService;

import lombok.RequiredArgsConstructor;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
@Controller
public class AdminNoticeController {
    private final NoticeService noticeService;
    //html 정리하여 악성 태그 혹은 스크립트 제거
    private final HtmlSanitizerService htmlSanitizerService;
    private final AdminNoticeService adminNoticeService;

//    @GetMapping("/manager")
//    public String readList(Model model) {
//        model.addAttribute("notices", noticeService.readList());
//        return "admin_notice_manager";
//    }

//    @GetMapping("/manager")
//    public String adminNotice(Model model) {
//        model.addAttribute("notices", adminNoticeService.getAllNotices());
//        return "admin_notice_manager";
//    }
//    @GetMapping("/manager")
//    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
//        Page<Notice> paging = this.noticeService.getList(page);
//        model.addAttribute("paging", paging);
//        return "support_notice";
//    }
    
    @GetMapping("/manager")
    public String getNotices(@RequestParam(name="page" ,defaultValue = "0") int page, Model model) {
        int pageSize = 5; // 페이지당 표시할 공지사항 수
        if (page < 0) {
            page = 0;
        }
        Page<Notice> noticesPage = noticeService.findAll(PageRequest.of(page, pageSize));
        model.addAttribute("notices", noticesPage.getContent());
        model.addAttribute("totalPages", noticesPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin_notice_manager";
    }


    @GetMapping("/create")
    public String noticeCreate(Model model) {
        model.addAttribute("notice", new Notice());
        return "admin_notice_create";
    }

    @PostMapping("/create")
    public String noticeCreate(@ModelAttribute Notice notice) {
        adminNoticeService.createNotice(notice);
        return "redirect:/admin/notice/manager";
    }

    @GetMapping("/delete/noticeID={id}")
    public String noticeDelete(@PathVariable("id") Integer id) {
        adminNoticeService.deleteNotice(id);
        return "redirect:/admin/notice/manager";
    }

    @GetMapping("/update/noticeID={id}")
    public String noticeUpdate(@PathVariable("id") Integer id, Model model) {
        Notice notice = adminNoticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "admin_notice_create";
    }

    @PostMapping("/update")
    public String noticeUpdate(@ModelAttribute Notice notice) {
        String sanitizedContent = htmlSanitizerService.sanitizeHtml(notice.getContent());
        notice.setContent(sanitizedContent);
        adminNoticeService.updateNotice(notice);
        return "redirect:/admin/notice/manager";
    }
}
