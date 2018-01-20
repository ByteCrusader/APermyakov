package ru.apermyakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.apermyakov.domain.account.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Account findByAccountId(String accountId);
}
