package com.jibe.util;

public class SecurityUtil {

    public String hashPasscode(String passcode) {
        return String.valueOf(passcode.hashCode());
    }
}
