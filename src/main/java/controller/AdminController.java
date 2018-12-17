package controller;

import javax.validation.Valid;
import model.Rate;
import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.NbuService;
import service.RateService;
import service.TradeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TradeService tradeService;
    private final RateService rateService;
    private final NbuService nbuService;

    public AdminController(TradeService tradeService, RateService rateService,
        NbuService nbuService) {
        this.tradeService = tradeService;
        this.rateService = rateService;
        this.nbuService = nbuService;
    }

    @GetMapping
    public String adminPage(
        @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("allDeal", tradeService.all());
        model.addAttribute("rates", rateService.allByDateTime());
        model.addAttribute("courses", nbuService.findAll());
        model.addAttribute("name", user.getName());
        return "admin";

    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id) {
        return new ModelAndView("edit", "form", rateService.findOne(id));
    }

    @GetMapping("/createForm")
    public String createRateForm(Model model) {
        model.addAttribute("createRate", new Rate());
        return "create";
    }

    @PostMapping("/add")
    public String createRate(@ModelAttribute("createRate") @Valid Rate rate, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            return "redirect:/admin/createForm";
        }
        Rate save = rateService.save(rate);
        model.addAttribute("createRate", save);
        return "redirect:/admin";
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
