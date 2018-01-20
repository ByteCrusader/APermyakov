package ru.apermyakov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.apermyakov.domain.account.Account;
import ru.apermyakov.repository.AccountsRepository;

import java.util.List;

@Service
public class AccountsDAOImpl implements DAO<Account> {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsDAOImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    @Transactional
    public Account create(Account account) {
        if (this.accountsRepository.findByAccountId(account.getAccountId()).getPassword().isEmpty()) {
            account.setPassword(new Randomizer().buildRandomString(8));
            account = this.accountsRepository.save(account);
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        return this.accountsRepository.findAll();
    }
}
