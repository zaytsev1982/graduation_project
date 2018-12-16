package repository;

import java.util.List;
import model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends JpaRepository<Rate, Long>, JpaSpecificationExecutor<Rate> {

    @Query(value = "select r.BUY from RATE as r where r.CREATE_DATE = max(r.CREATE_DATE) and r.CCY = :names", nativeQuery = true)
    Double buyResult(@Param("names") String currency);

    @Query(value = "select SALE from RATE as r where r.CREATE_DATE = max(r.CREATE_DATE) and r.CCY = :names", nativeQuery = true)
    Double saleResult(@Param("names") String currency);

    @Query(value = "select r.* from RATE r where r.CREATE_DATE = (select max(CREATE_DATE) from RATE group by CCY having CCY=r.CCY)", nativeQuery = true)
    List<Rate> byDate();
}
