package com.example.growith.introduction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/project")
@Controller
public class ProjectController {
    @GetMapping("/current")
    public String current() {
        return "projects_current";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "projects_gallery";
    }

    @GetMapping("/projects")
    public String projects() {
        return "projects_projects";
    }
}
