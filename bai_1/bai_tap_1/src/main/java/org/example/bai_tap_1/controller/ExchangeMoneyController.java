package org.example.bai_tap_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExchangeMoneyController {

    @GetMapping("/home")
    public String showForm() {
        return "home";
    }

    @PostMapping("/convert")
    public String convert(
            @RequestParam("rate") double rate,
            @RequestParam("usd") double usd,
            Model model) {
        double vnd = rate * usd;
        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
        return "result";
    }
}
