package controller;

import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/"})
    public String startPage(Model model) {

        return "welcome";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/403")
    public String accept(
        @AuthenticationPrincipal
            User user, Model model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("user", "you do not have access rights");
        return "403";
    }



}
