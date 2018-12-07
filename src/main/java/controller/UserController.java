package controller;

import model.Trade;
import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.TradeService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final TradeService tradeService;

    public UserController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    public String userPage(
        @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("newTrade", new Trade());
        model.addAttribute("allDeal", tradeService.allByManager(user.getId()));
        model.addAttribute("name", user.getName());
        return "user";

    }

    @PostMapping("/add")
    public String add(
        @AuthenticationPrincipal User user,
        @ModelAttribute("newTrade") Trade trade, Model model) {
        trade.setUser(user);
        Trade save = tradeService.create(trade);
        model.addAttribute("newTrade", save);
        return "redirect:/user";
    }


    @GetMapping(value = "/delete/{id}")
    public String closed(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        redirectAttributes
            .addFlashAttribute("message", String.format("deal by id %d closed success", id));
        return "redirect:/user";
    }

}
