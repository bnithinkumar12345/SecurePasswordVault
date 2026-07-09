package com.nithin.util;

public class ErrorHandler {

    public static void showDatabaseError(Exception e) {

        System.out.println("\n==================================");
        System.out.println("      DATABASE ERROR");
        System.out.println("==================================");
        System.out.println("Something went wrong while accessing the database.");
        System.out.println("Reason: " + e.getMessage());
        System.out.println("==================================");
    }

    public static void showGeneralError(Exception e) {

        System.out.println("\n==================================");
        System.out.println("          ERROR");
        System.out.println("==================================");
        System.out.println(e.getMessage());
        System.out.println("==================================");
    }
}