package service;

import java.time.LocalDate;
import java.util.List;
import model.Nbu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.NbuRepository;

@Service
@Transactional
public class NbuServiceImpl implements NbuService {

    private final NbuRepository nbuRepository;

    public NbuServiceImpl(NbuRepository nbuRepository) {
        this.nbuRepository = nbuRepository;
    }

    @Override
    public Nbu create(Nbu nbu) {
        return nbuRepository.save(nbu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nbu> findAll() {
        return nbuRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nbu> finaAllByDate(LocalDate date) {
        return nbuRepository.findAllByExchangeDate(date);
    }
}
