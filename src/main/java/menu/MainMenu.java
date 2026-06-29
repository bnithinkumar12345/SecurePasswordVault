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
                        System.out.println("\n✅ Registration Successful!");
                    } else {
                        System.out.println("\n❌ Registration Failed!");
                    }

                    break;

                case 2:

                    System.out.print("Enter Username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter Master Password: ");
                    String loginPassword = scanner.nextLine();

                    boolean login = authService.login(loginUsername, loginPassword);

                    if (login) {
                        System.out.println("\n✅ Login Successful!");
                    } else {
                        System.out.println("\n❌ Invalid Username or Password!");
                    }

                    break;

                case 3:

                    System.out.println("Thank You for using Secure Password Vault!");
                    System.exit(0);
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}