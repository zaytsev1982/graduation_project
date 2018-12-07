package controller;

import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.TradeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TradeService tradeService;

    public AdminController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    public String userPage(
        @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("allDeal", tradeService.all());
        model.addAttribute("name", user.getName());
        return "admin";

    }

    @GetMapping(value = "/delete/{id}")
    public String closed(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        tradeService.delete(id);
        redirectAttributes
            .addFlashAttribute("message", String.format("deal by id %d successfully closed ", id));
        return "redirect:/admin";
    }




}
