package controller;

import model.Rate;
import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.RateService;
import service.TradeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TradeService tradeService;
    private final RateService rateService;

    public AdminController(TradeService tradeService, RateService rateService) {
        this.tradeService = tradeService;
        this.rateService = rateService;
    }

    @GetMapping
    public String adminPage(
        @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("allDeal", tradeService.all());
        model.addAttribute("rates", rateService.allByDateTime());
        model.addAttribute("name", user.getName());
        return "admin";

    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id) {
        return new ModelAndView("edit", "form", rateService.findOne(id));
    }


    @PostMapping("/edit")
    public String edit(Rate rate) {
        rateService.save(rate);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete/{id}")
    public String closed(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        tradeService.delete(id);
        redirectAttributes
            .addFlashAttribute("message", String.format("deal by id %d successfully closed ", id));
        return "redirect:/admin";
    }




}
