package service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import model.Operation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OperationRepository;

@Service
@Transactional
@Slf4j
public class OperationServiceImpl implements OperationService {


    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation create(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    @Transactional(readOnly = true)
    public Operation findOne(Long id) {
        return operationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("Cannot found operation by id:%d", id)));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> allByUser(Long id) {
        List<Operation> allByAuthor = operationRepository.findAllByAuthor(id);
        for (Operation o : allByAuthor) {
            log.info("operation by id:{} successfully found", o.getId());
        }
        return allByAuthor;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> all() {
        List<Operation> list = operationRepository.findAll();
        for (Operation o : list) {
            log.info("operation by id:{} successfully found", o.getId());
        }

        return list;
    }

    @Override
    public void closed(Long id) {
        Operation operation = operationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("Cannot found operation by id:%d", id)));
        operation.setStatus(false);
        log.info("operation by id:{} successfully closed", operation.getId());

    }
}
