package ru.apermyakov.mapping.service;

import ru.apermyakov.mapping.persistance.UserPersist;
import ru.apermyakov.mapping.user.User;

import java.util.Base64;

public class UserService {

    private final UserPersist persist = new UserPersist();

    public void create(String userName, String userLoginPass) {
        String res = new String(Base64
                                .getDecoder()
                                .decode(userLoginPass
                                        .replace("Basic ", "")));
        String[] authPare = res.split(":");
        this.persist.add(new User(userName, authPare[0], authPare[1]));
    }
}
