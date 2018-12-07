package repository;

import java.util.List;
import model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("select t from Trade t order by t.id desc")
    List<Trade> findAllDescAdmin();

    @Query("select t from Trade t inner join User u on t.user=u.id where u.id=?1")
    List<Trade> byManager(@Param("id") Long id);

}
