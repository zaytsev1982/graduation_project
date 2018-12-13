package controller;

import converter.NbuJsonToNbu;
import converter.RateJsonToRate;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import json.NbuJson;
import json.RateJson;
import model.Nbu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.NbuService;
import service.RateService;

@RestController
public class ClientController {

    private static final String URL_CURRENT_RATE = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    private static final String URL_NATIONAL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    private final RestTemplate restTemplate;
    private final RateJsonToRate converter;
    private final NbuJsonToNbu toNbu;
    private final RateService service;
    private final NbuService nbuService;

    public ClientController(RestTemplate restTemplate, RateJsonToRate converter,
        NbuJsonToNbu toNbu, RateService service, NbuService nbuService) {
        this.restTemplate = restTemplate;
        this.converter = converter;
        this.toNbu = toNbu;
        this.service = service;
        this.nbuService = nbuService;
    }

    @PostConstruct
    @GetMapping("/api/current-rate")
    public void rate() {
        RateJson[] rateJsons = restTemplate.getForObject(URL_CURRENT_RATE, RateJson[].class);

        for (RateJson r : rateJsons) {
            converter.convert(r);
        }

    }

    @PostConstruct
    @GetMapping("/api/bank")
    public List<Nbu> nbuCourse() {
        NbuJson[] nbuJsons = restTemplate.getForObject(URL_NATIONAL, NbuJson[].class);

        for (NbuJson nbu : nbuJsons) {
            toNbu.convert(nbu);
        }
        return nbuService.finaAllByDate(LocalDate.now());
    }
}
