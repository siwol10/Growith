package com.example.growith.introduction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/about")
@Controller
public class AboutController {
    @GetMapping("/overview")
    public String overview() {
        return "about_overview";
    }

    @GetMapping("/team")
    public String team() {
        return "about_team";
    }

    @GetMapping("/history")
    public String history() {
        return "about_history";
    }
}
