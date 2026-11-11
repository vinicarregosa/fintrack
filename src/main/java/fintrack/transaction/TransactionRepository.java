package fintrack.transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    void save(Transaction transaction);

    Optional<Transaction> findById(Long id);

    List<Transaction> findAll();

    void deleteById(Long id);
}
