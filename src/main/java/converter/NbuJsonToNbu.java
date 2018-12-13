package converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import json.NbuJson;
import model.Nbu;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import service.NbuService;

@Component
public class NbuJsonToNbu implements Converter<NbuJson, Nbu> {

    private final NbuService nbuService;

    public NbuJsonToNbu(NbuService nbuService) {
        this.nbuService = nbuService;
    }


    @Override
    public Nbu convert(NbuJson nbuJson) {
        Nbu nbu = new Nbu();
        nbu.setArticle(nbuJson.getCode());
        nbu.setCurrencyName(nbuJson.getText());
        nbu.setCourse(Double.parseDouble(nbuJson.getRate()));
        nbu.setCode(nbuJson.getCc());
        nbu.setExchangeDate(getDate(nbuJson.getExchangeDate()));
        return nbuService.create(nbu);
    }

    private LocalDate getDate(String exchangeDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(exchangeDate, formatter);
        return localDate;
    }
}
