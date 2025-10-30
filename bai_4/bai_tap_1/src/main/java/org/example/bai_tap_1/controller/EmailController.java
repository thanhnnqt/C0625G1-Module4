package org.example.bai_tap_1.controller;

import org.example.bai_tap_1.entity.Email;
import org.example.bai_tap_1.service.EmailService;
import org.example.bai_tap_1.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class EmailController {
    @Autowired
    private final IEmailService emailService;

    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("settings", emailService.findAll());
        model.addAttribute("language", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("pageSize", new int[]{5, 10, 15, 25, 50, 100});
        return "add";
    }

    @PostMapping
    public String update(@ModelAttribute("settings") Email updated, Model model) {
        emailService.update(updated);
        model.addAttribute("settings", emailService.findAll());
        return "list";
    }
}
