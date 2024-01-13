package com.salary.global.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStringBuilder {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private RandomStringBuilder(){

    }

    public static String generateRandomString(int length){
        SecureRandom secureRandom = new SecureRandom();
        List<Character> charList = new ArrayList<>();

        for (char c : CHARACTERS.toCharArray()) {
            charList.add(c);
        }
        Collections.shuffle(charList, secureRandom);

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(charList.size());
            char randomChar = charList.get(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
