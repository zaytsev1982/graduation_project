package service;

import java.util.List;
import model.Rate;

public interface RateService {

    Rate save(Rate rate);

    List<Rate> all();

    List<Rate> allByDateTime();

    Double saleResult(String currency);

    Double buyResult(String currency);

    Rate findOne(Long id);

}
