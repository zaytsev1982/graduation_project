package service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import model.Trade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TradeRepository;

@Service
@Transactional
@Slf4j
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final RateService rateService;


    public TradeServiceImpl(TradeRepository tradeRepository, RateService rateService) {
        this.tradeRepository = tradeRepository;
        this.rateService = rateService;
    }

    @Override
    public Trade create(Trade trade) {

        trade.setAmount(trade.getType().contains("BUY") ? trade.getQuantity() * rateService
            .buyResult(trade.getName())
            : trade.getQuantity() * rateService.saleResult(trade.getName()));
        trade.setActive(true);
        trade.setDateTime(LocalDateTime.now());
        Trade save = tradeRepository.save(trade);

        return save;

    }

    @Override
    public Trade findOne(Long id) {
        return tradeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("deal on id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trade> all() {
        return tradeRepository.findAllDescAdmin();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trade> allByManager(Long id) {
        return tradeRepository.byManager(id);
    }

    @Override
    public void delete(Long id) {
        Trade trade = tradeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("deal on id " + id + " not found"));
        trade.setActive(false);
        tradeRepository.save(trade);
    }


}
