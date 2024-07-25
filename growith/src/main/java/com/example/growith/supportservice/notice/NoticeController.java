package com.example.growith.supportservice.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.growith.supportservice.faq.Faq;

@RequestMapping("/support")
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
//	@GetMapping("/notice")
//	public String notice(Model model) {
//		model.addAttribute("notices", noticeService.readList());
//		return "support_notice";
//	}
	
	@GetMapping("/notice")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Notice> noticePage = this.noticeService.getList(page); 
        model.addAttribute("notices", noticePage.getContent()); 
        model.addAttribute("currentPage", page); // 현재 페이지
        model.addAttribute("totalPages", noticePage.getTotalPages()); 
        return "support_notice";
    }
	
//	@GetMapping("/notice/readdetail/{id}")
//	public String readdetail(Model model, @PathVariable("id") Integer id) {
//		model.addAttribute("notices", noticeService.readdetail(id));
//		return "support_notice_readdetail";
//	}

//	@GetMapping("/contact")
//	public String contact() {
//		return "support_contact";
//	}
//
//	@PostMapping("/contact")
//    public String submitContactForm(@RequestParam("name") String name,
//                                    @RequestParam("email") String email,
//                                    @RequestParam("content") String content,
//                                    @RequestParam("phone") String phone,
//                                    Model model) {
//		return "support_contact";
//	}
//
//	@GetMapping("/faq")
//	public String faq() {
//		return "support_faq";
//	}
	
	
	
}
