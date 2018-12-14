package repository;

import java.util.List;
import model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("select r.buy from Rate r where r.ccy= :currency")
    Double buyResult(@Param("currency") String currency);

    @Query("select r.sale from Rate r where r.ccy= :currency")
    Double saleResult(@Param("currency") String currency);

    @Query(value = "select r.* from RATE r where r.CREATE_DATE = (select max(CREATE_DATE) from RATE group by CCY having CCY=r.CCY)", nativeQuery = true)
    List<Rate> byDate();
}
