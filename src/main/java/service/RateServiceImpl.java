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
        return rateRepository.save(rate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rate> all() {
        return rateRepository.findAll();
    }
}
