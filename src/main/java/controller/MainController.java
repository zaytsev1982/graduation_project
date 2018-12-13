package controller;

import javax.servlet.http.HttpSession;
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

    @GetMapping({"/", "/login"})
    public String startPage(Model model) {

        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
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
