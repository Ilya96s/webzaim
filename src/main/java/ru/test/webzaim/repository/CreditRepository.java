package ru.test.webzaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.webzaim.model.Credit;

/**
 * CreditRepository - хранилище кредитов
 *
 * @author Ilya Kaltygin
 */
@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}
