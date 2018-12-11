package repository;

import model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("select r.buy from Rate r where r.ccy= :currency")
    Double buyResult(@Param("currency") String currency);

    @Query("select r.sale from Rate r where r.ccy= :currency")
    Double saleResult(@Param("currency") String currency);
}
