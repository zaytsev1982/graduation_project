package service;

import java.time.LocalDateTime;
import java.util.List;
import model.Rate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RateRepository;

@Service
@Transactional
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate save(Rate rate) {
        if (rate.getId() == null) {
            rate.setCreateDate(LocalDateTime.now());
            return rateRepository.save(rate);
        } else {
            Rate update = findOne(rate.getId());
            update.setId(rate.getId());
            update.setCcy(rate.getCcy());
            update.setBaseCcy(rate.getBaseCcy());
            update.setBuy(rate.getBuy());
            update.setSale(rate.getSale());
            update.setCreateDate(LocalDateTime.now());
            return rateRepository.save(update);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Rate> all() {
        return rateRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rate> allByDateTime() {
        return rateRepository.byDate();
    }

    @Override
    public Double saleResult(String currency) {
        return rateRepository.saleResult(currency);
    }

    @Override
    public Double buyResult(String currency) {
        return rateRepository.buyResult(currency);
    }


    @Override
    public Rate findOne(Long id) {
        return rateRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("rate by id: " + id + " fot found"));
    }


}
