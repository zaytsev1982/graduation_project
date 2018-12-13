package service;

import java.time.LocalDate;
import java.util.List;
import model.Nbu;

public interface NbuService {

    Nbu create(Nbu nbu);

    List<Nbu> findAll();

    List<Nbu> finaAllByDate(LocalDate date);
}
