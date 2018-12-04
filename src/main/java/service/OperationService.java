package service;

import java.util.List;
import model.Operation;

public interface OperationService {

    Operation create(Operation operation);

    Operation findOne(Long id);

    List<Operation> allByUser(Long id);

    List<Operation> all();

    void closed(Long id);

}
