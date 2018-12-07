package controller;

import converter.RateJsonToRate;
import java.util.List;
import javax.annotation.PostConstruct;
import json.RateJson;
import model.Rate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.RateService;

@RestController
public class ClientController {

    private static final String URL_CURRENT_RATE = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    private final RestTemplate restTemplate;
    private final RateJsonToRate converter;
    private final RateService service;

    public ClientController(RestTemplate restTemplate, RateJsonToRate converter,
        RateService service) {
        this.restTemplate = restTemplate;
        this.converter = converter;
        this.service = service;
    }

    @PostConstruct
    @GetMapping("/api/current-rate")
    public List<Rate> rate() {
        RateJson[] rateJsons = restTemplate.getForObject(URL_CURRENT_RATE, RateJson[].class);

        for (RateJson r : rateJsons) {
            converter.convert(r);
        }
        return service.all();
    }

}
