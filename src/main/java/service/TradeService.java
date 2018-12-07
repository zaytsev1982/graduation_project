package service;

import java.util.List;
import model.Trade;

public interface TradeService {

    Trade create(Trade trade);

    Trade findOne(Long id);

    List<Trade> all();

    List<Trade> allByManager(Long id);

    void delete(Long id);

}
