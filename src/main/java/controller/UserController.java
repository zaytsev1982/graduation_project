package controller;

import model.Trade;
import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.NbuService;
import service.RateService;
import service.TradeService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final TradeService tradeService;
    private final RateService rateService;
    private final NbuService nbuService;

    public UserController(TradeService tradeService, RateService rateService,
        NbuService nbuService) {
        this.tradeService = tradeService;
        this.rateService = rateService;
        this.nbuService = nbuService;
    }

    @GetMapping
    public String userPage(
        @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("newTrade", new Trade());
        model.addAttribute("allDeal", tradeService.allByManager(user.getId()));
        model.addAttribute("adminAllDeal", tradeService.all());
        model.addAttribute("courses", nbuService.findAll());
        model.addAttribute("rates", rateService.allByDateTime());
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

}
