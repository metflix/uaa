package com.metflix.uaa;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UaaController {
    @GetMapping("/")
    String home(@AuthenticationPrincipal(expression = "user") User user, Model model) {
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
