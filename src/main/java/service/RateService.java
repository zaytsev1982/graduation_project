package service;

import java.util.List;
import model.Rate;

public interface RateService {

    Rate save(Rate rate);

    List<Rate> all();

}
