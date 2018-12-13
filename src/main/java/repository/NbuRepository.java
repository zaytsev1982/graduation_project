package repository;

import java.time.LocalDate;
import java.util.List;
import model.Nbu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbuRepository extends JpaRepository<Nbu, Long> {

    List<Nbu> findAllByExchangeDate(LocalDate date);

}
