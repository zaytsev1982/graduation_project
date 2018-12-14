package service;

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
            return rateRepository.save(rate);
        } else {
            Rate update = findOne(rate.getId());
            update.setId(rate.getId());
            update.setCcy(rate.getCcy());
            update.setBaseCcy(rate.getBaseCcy());
            update.setBuy(rate.getBuy());
            update.setSale(rate.getSale());

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
    public Double buyResult(String ccy) {
        return rateRepository.buyResult(ccy);
    }

    @Override
    public Double saleResult(String ccy) {
        return rateRepository.saleResult(ccy);
    }

    @Override
    public Rate findOne(Long id) {
        return rateRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("rate by id: " + id + " fot found"));
    }


}
