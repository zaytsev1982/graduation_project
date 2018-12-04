package repository;

import java.util.List;
import model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {


    List<Operation> findAllByAuthor(Long id);

}
