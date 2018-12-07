package controller;

import java.util.Locale;
import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.RateService;

@Controller
public class MainController {

    private final RateService rateService;

    public MainController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping({"/"})
    public String startPage(Model model, Locale locale) {
        model.addAttribute("rate", rateService.all());
        return "index";
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
