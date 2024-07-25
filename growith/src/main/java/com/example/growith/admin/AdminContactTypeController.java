package com.example.growith.admin;

import com.example.growith.supportservice.contact.ContactType;
import com.example.growith.supportservice.contact.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//현재 미사용
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/contactType")
@RequiredArgsConstructor
@Controller
public class AdminContactTypeController {
    private final AdminContactTypeService adminContactTypeService;

    @GetMapping("/manager")
    public String readList(Model model) {
        model.addAttribute("contactTypes", adminContactTypeService.getAllContactTypes());
        return "admin_contactType";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contactType", new ContactType());
        return "admin_contactType_create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ContactType contactType) {
        adminContactTypeService.create(contactType);
        return "redirect:/admin/contactType/manager";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        adminContactTypeService.deleteById(id);
        return "redirect:/admin/contactType/manager";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Integer id) {
        ContactType contactType = adminContactTypeService.getById(id);
        model.addAttribute("contactType", contactType);
        return "admin_contactType_create";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ContactType contactType) {
        adminContactTypeService.update(contactType);
        return "redirect:/admin/contactType/manager";
    }
}
