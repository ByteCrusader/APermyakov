package ru.apermyakov.service;

import java.security.SecureRandom;

public class Randomizer {

    public String buildRandomString(int length) {
        final String AB = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder(length);
        for (int index = 0; index < length; index++) {
            builder.append(AB.charAt(random.nextInt(AB.length())));
        }
        return builder.toString();
    }
}
