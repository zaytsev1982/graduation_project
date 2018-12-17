package controller;

import java.util.List;
import model.Trade;
import model.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import service.RateService;
import service.TradeService;

@Controller
@RequestMapping(value = "/api")
public class RestController {

    private final TradeService tradeService;
    private final RateService rateService;
    private final RestTemplate restTemplate;

    public RestController(TradeService tradeService, RateService rateService,
        RestTemplate restTemplate) {
        this.tradeService = tradeService;
        this.rateService = rateService;
        this.restTemplate = restTemplate;
    }


    @RequestMapping(value = "/user/alltrade/{id}", method = RequestMethod.GET)
    public List<Trade> getAllByManagerId(@RequestParam("id") Long id,
        @AuthenticationPrincipal User user) {
        return tradeService.allByManager(user.getId());
    }
}
