package com.nithin.util;

public class ValidationUtil {

    // Username Validation
    public static boolean isValidUsername(String username) {

        return username != null && !username.trim().isEmpty();
    }

    // Email Validation
    public static boolean isValidEmail(String email) {

        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Password Validation
    public static boolean isValidPassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch))
                upper = true;

            else if (Character.isLowerCase(ch))
                lower = true;

            else if (Character.isDigit(ch))
                digit = true;

            else
                special = true;
        }

        return upper && lower && digit && special;
    }

    // Website Validation
    public static boolean isValidWebsite(String website) {

        return website != null && !website.trim().isEmpty();
    }
}