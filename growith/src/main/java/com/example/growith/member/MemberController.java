package com.example.growith.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    //로그인
    @GetMapping("/admin/login")
    public String login() {
        return "admin_login";
    }

    //회원가입
    @GetMapping("/admin/signup")
    public String signup() {
        return "admin_signup";
    }

    @PostMapping("/admin/signup")
    public String signup(Member member) {
        memberService.create(member);
        return "redirect:/admin/login";
    }


}
