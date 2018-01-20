package ru.apermyakov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.apermyakov.domain.account.Account;
import ru.apermyakov.domain.jsonobjects.JsonAccount;
import ru.apermyakov.domain.shortened.Shortened;
import ru.apermyakov.service.AccountsDAOImpl;
import ru.apermyakov.service.ShortenedsDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Front controller.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 20.01.2018.
 */
@Controller
public class ShortenController {

    private final AccountsDAOImpl accountDao;

    private final ShortenedsDAOImpl shortenedDao;

    @Autowired
    public ShortenController(AccountsDAOImpl dao, ShortenedsDAOImpl shortenedDao) {
        this.accountDao = dao;
        this.shortenedDao = shortenedDao;
    }

    @GetMapping("/redirect/{LongPath}")
    public String redirectAccount(@PathVariable("LongPath") String longUrl) {
        return String.format("redirect:%s", this.shortenedDao.findByLongUrl(longUrl).getLongUrl());
    }

    @GetMapping("/statistic/{AccountId}")
    public ResponseEntity<Map<String, Integer>> showStatistics(@PathVariable("AccountId") String accountId) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        List<Shortened> shorteneds = this.shortenedDao.findAll();
        for (Shortened shortened : shorteneds) {
            result.put(shortened.getLongUrl(), shortened.getId());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/register")
    public String showRegister(){
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Shortened addShortened(@RequestBody Shortened shortened) {
        return this.shortenedDao.create(shortened);
    }

    @GetMapping("/account")
    public String showAccount(){
        return "account";
    }

    @PostMapping("/account")
    @ResponseBody
    public JsonAccount addAccount(@RequestBody Account account) {
        JsonAccount jsonAccount = new JsonAccount();
        account = this.accountDao.create(account);
        if (!account.getPassword().isEmpty()) {
            jsonAccount.setSuccess(true);
            jsonAccount.setDescription("Correct registration!");
            jsonAccount.setPassword(account.getPassword());
        } else {
            jsonAccount.setSuccess(false);
            jsonAccount.setDescription("This account already exist!");
        }
        return jsonAccount;
    }
}
