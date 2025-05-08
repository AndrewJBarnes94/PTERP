package com.brocodesoftware.pterp_client;

import java.util.ArrayList;
import java.util.List;

public class PasswordHasher {

    private final String password;

    public PasswordHasher(String password) {
        this.password = password;
    }

    public String hash() {
        List<Integer> asciiValues = getAsciiValues();
        StringBuilder builder = new StringBuilder();
        for (int ascii : asciiValues) {
            builder.append(ascii);
        }
        return builder.toString();
    }

    private List<Integer> getAsciiValues() {
        List<Integer> asciiValues = new ArrayList<>();
        for (char c : password.toCharArray()) {
            asciiValues.add((int) c);
        }
        return asciiValues;
    }
}
