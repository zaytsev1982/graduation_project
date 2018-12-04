package controller;

import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.OperationService;
import service.user.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final OperationService operationService;

    public UserController(UserService userService, OperationService operationService) {
        this.userService = userService;
        this.operationService = operationService;
    }

    @GetMapping
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("name", user.getName());

        return "user";

    }


    @GetMapping(value = "/delete/{id}")
    public String closed(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        redirectAttributes
            .addFlashAttribute("message", String.format("deal by id %d closed success", id));
        return "redirect:/user";
    }

}
