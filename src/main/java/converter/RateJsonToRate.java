package converter;

import model.Rate;
import json.RateJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import service.RateService;

@Component
public class RateJsonToRate implements Converter<RateJson, Rate> {

    private final RateService rateService;

    public RateJsonToRate(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public Rate convert(RateJson rateJson) {
        Rate rate = new Rate();
        rate.setCcy(rateJson.getCcy());
        rate.setBaseCcy(rateJson.getBaseCcy());
        rate.setBuy(Double.parseDouble(rateJson.getBuy()));
        rate.setSale(Double.parseDouble(rateJson.getSale()));
        return rateService.save(rate);
    }
}
