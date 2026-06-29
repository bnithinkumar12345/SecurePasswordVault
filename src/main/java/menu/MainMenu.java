package com.nithin.menu;

import com.nithin.service.AuthService;

import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
    private AuthService authService = new AuthService();

    public void start() {

        while (true) {

            System.out.println("\n==================================");
            System.out.println("      SECURE PASSWORD VAULT");
            System.out.println("==================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Master Password: ");
                    String password = scanner.nextLine();

                    boolean success = authService.register(username, email, password);

                    if (success) {
                        System.out.println("Registration Successful!");
                    } else {
                        System.out.println("Registration Failed!");
                    }

                    break;

                case 2:
                    System.out.println("Login Feature Coming Soon...");
                    break;

                case 3:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}