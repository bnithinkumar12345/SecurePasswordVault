package com.nithin.menu;

import com.nithin.model.Credential;
import com.nithin.service.AuthService;
import com.nithin.service.VaultService;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
    private AuthService authService = new AuthService();
    private VaultService vaultService = new VaultService();

    // Temporary user id
    private int currentUserId = 1;

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

                    if (authService.register(username, email, password))
                        System.out.println("✅ Registration Successful!");
                    else
                        System.out.println("❌ Registration Failed!");

                    break;

                case 2:

                    System.out.print("Enter Username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter Master Password: ");
                    String loginPassword = scanner.nextLine();

                    if (authService.login(loginUsername, loginPassword)) {

                        System.out.println("✅ Login Successful!");
                        passwordVaultMenu();

                    } else {

                        System.out.println("❌ Invalid Username or Password!");
                    }

                    break;

                case 3:

                    System.out.println("Thank You!");
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void passwordVaultMenu() {

        while (true) {

            System.out.println("\n========== PASSWORD VAULT ==========");
            System.out.println("1. Add Credential");
            System.out.println("2. View Credentials");
            System.out.println("3. Search Credential");
            System.out.println("4. Update Credential");
            System.out.println("5. Delete Credential");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Website: ");
                    String website = scanner.nextLine();

                    System.out.print("Website Username: ");
                    String websiteUsername = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    if (vaultService.addCredential(currentUserId, website, websiteUsername, password, notes))
                        System.out.println("✅ Credential Added Successfully!");
                    else
                        System.out.println("❌ Failed to Add Credential!");

                    break;

                case 2:

                    List<Credential> credentials = vaultService.getAllCredentials(currentUserId);

                    if (credentials.isEmpty()) {
                        System.out.println("No Credentials Found!");
                    } else {

                        System.out.println("\n========== YOUR CREDENTIALS ==========");

                        for (Credential c : credentials) {

                            System.out.println("--------------------------------");
                            System.out.println("Website : " + c.getWebsite());
                            System.out.println("Username: " + c.getWebsiteUsername());
                            System.out.println("Password: " + c.getPassword());
                            System.out.println("Notes   : " + c.getNotes());
                        }
                    }

                    break;

                case 3:

                    System.out.print("Enter Website Name: ");
                    String searchWebsite = scanner.nextLine();

                    Credential credential = vaultService.searchCredential(currentUserId, searchWebsite);

                    if (credential != null) {

                        System.out.println("\n====== Credential Found ======");
                        System.out.println("Website : " + credential.getWebsite());
                        System.out.println("Username: " + credential.getWebsiteUsername());
                        System.out.println("Password: " + credential.getPassword());
                        System.out.println("Notes   : " + credential.getNotes());

                    } else {

                        System.out.println("❌ Credential Not Found!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Website to Update: ");
                    String updateWebsite = scanner.nextLine();

                    System.out.print("New Username: ");
                    String newUsername = scanner.nextLine();

                    System.out.print("New Password: ");
                    String newPassword = scanner.nextLine();

                    System.out.print("New Notes: ");
                    String newNotes = scanner.nextLine();

                    if (vaultService.updateCredential(currentUserId, updateWebsite, newUsername, newPassword, newNotes))
                        System.out.println("✅ Credential Updated Successfully!");
                    else
                        System.out.println("❌ Credential Update Failed!");

                    break;

                case 5:

                    System.out.print("Enter Website to Delete: ");
                    String deleteWebsite = scanner.nextLine();

                    if (vaultService.deleteCredential(currentUserId, deleteWebsite))
                        System.out.println("✅ Credential Deleted Successfully!");
                    else
                        System.out.println("❌ Credential Delete Failed!");

                    break;

                case 6:

                    return;

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}