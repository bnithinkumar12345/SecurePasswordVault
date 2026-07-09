package com.nithin.menu;

import com.nithin.model.Credential;
import com.nithin.model.User;
import com.nithin.service.AuthService;
import com.nithin.service.VaultService;
import com.nithin.util.PasswordGenerator;
import com.nithin.util.PasswordStrengthChecker;
import com.nithin.dao.LoginHistoryDAO;
import com.nithin.model.LoginHistory;
import com.nithin.dao.CredentialDAO;
import com.nithin.util.ValidationUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
    private AuthService authService = new AuthService();
    private VaultService vaultService = new VaultService();
    private LoginHistoryDAO loginHistoryDAO = new LoginHistoryDAO();
    private CredentialDAO credentialDAO = new CredentialDAO();

    public void start() {

        while (true) {

            System.out.println("\n==================================");
            System.out.println("      SECURE PASSWORD VAULT");
            System.out.println("==================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
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

                    if (authService.register(username, email, password)) {

                        System.out.println("✅ Registration Successful!");

                    } else {

                        System.out.println("❌ Registration Failed!");
                    }

                    break;

                case 2:

                    System.out.print("Enter Username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter Master Password: ");
                    String loginPassword = scanner.nextLine();

                    User loggedInUser = authService.login(loginUsername, loginPassword);

                    if (loggedInUser != null) {

                        System.out.println("\n✅ Login Successful!");
                        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");

                        passwordVaultMenu(loggedInUser);

                    } else {

                        System.out.println("❌ Invalid Username or Password!");
                    }

                    break;

                case 3:

                    System.out.println("\n========== FORGOT PASSWORD ==========");

                    System.out.print("Enter Username: ");
                    String forgotUsername = scanner.nextLine();

                    System.out.print("Enter Registered Email: ");
                    String forgotEmail = scanner.nextLine();

                    System.out.print("Enter New Password: ");
                    String newPassword = scanner.nextLine();

                    if (authService.forgotPassword(
                            forgotUsername,
                            forgotEmail,
                            newPassword)) {

                        System.out.println("\n✅ Password Reset Successfully!");

                    } else {

                        System.out.println("\n❌ Invalid Username or Email!");
                    }

                    break;

                case 4:

                    System.out.println("Thank You!");
                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void passwordVaultMenu(User loggedInUser) {

        while (true) {

            System.out.println("1. Add Credential");
            System.out.println("2. View Credentials");
            System.out.println("3. Search Credential");
            System.out.println("4. Update Credential");
            System.out.println("5. Delete Credential");
            System.out.println("6. Generate Password");
            System.out.println("7. Check Password Strength");
            System.out.println("8. View Login History");
            System.out.println("9. Change Master Password");
            System.out.println("10. Statistics");
            System.out.println("11. Export Credentials");
            System.out.println("12. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n========== ADD CREDENTIAL ==========");

                    System.out.print("Website: ");
                    String website = scanner.nextLine();

                    if (!ValidationUtil.isValidWebsite(website)) {

                        System.out.println("❌ Website cannot be empty!");
                        break;
                    }

                    System.out.print("Website Username: ");
                    String websiteUsername = scanner.nextLine();

                    if (!ValidationUtil.isValidUsername(websiteUsername)) {

                        System.out.println("❌ Username cannot be empty!");
                        break;
                    }

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    if (!ValidationUtil.isValidPassword(password)) {

                        System.out.println("❌ Password is too weak!");
                        System.out.println("Password must contain:");
                        System.out.println("- At least 8 characters");
                        System.out.println("- One Uppercase Letter");
                        System.out.println("- One Lowercase Letter");
                        System.out.println("- One Number");
                        System.out.println("- One Special Character");
                        break;
                    }

                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    if (vaultService.addCredential(
                            loggedInUser.getId(),
                            website,
                            websiteUsername,
                            password,
                            notes)) {

                        System.out.println("✅ Credential Added Successfully!");

                    } else {

                        System.out.println("❌ Failed to Add Credential!");
                    }

                    break;

                case 2:

                    List<Credential> credentials =
                            vaultService.getAllCredentials(loggedInUser.getId());

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

                        System.out.println("--------------------------------");
                    }

                    break;

                case 3:

                    System.out.print("Enter Website Name: ");
                    String searchWebsite = scanner.nextLine();

                    Credential credential =
                            vaultService.searchCredential(
                                    loggedInUser.getId(),
                                    searchWebsite);

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

                    if (vaultService.updateCredential(
                            loggedInUser.getId(),
                            updateWebsite,
                            newUsername,
                            newPassword,
                            newNotes)) {

                        System.out.println("✅ Credential Updated Successfully!");

                    } else {

                        System.out.println("❌ Credential Update Failed!");
                    }

                    break;

                case 5:

                    System.out.print("Enter Website to Delete: ");
                    String deleteWebsite = scanner.nextLine();

                    if (vaultService.deleteCredential(
                            loggedInUser.getId(),
                            deleteWebsite)) {

                        System.out.println("✅ Credential Deleted Successfully!");

                    } else {

                        System.out.println("❌ Credential Delete Failed!");
                    }

                    break;

                case 6:

                    System.out.print("Enter Password Length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();

                    String generatedPassword = PasswordGenerator.generatePassword(length);

                    System.out.println("\nGenerated Password:");
                    System.out.println(generatedPassword);

                    break;

                case 7:

                    System.out.print("Enter Password: ");
                    String checkPassword = scanner.nextLine();

                    String strength =
                            PasswordStrengthChecker.checkStrength(checkPassword);

                    System.out.println();
                    System.out.println("Password Strength : " + strength);

                    break;

                case 8:

                    List<LoginHistory> historyList =
                            loginHistoryDAO.getLoginHistory(loggedInUser.getId());

                    if(historyList.isEmpty()){

                        System.out.println("\nNo Login History Found!");

                    }else{

                        System.out.println("\n========== LOGIN HISTORY ==========");

                        for(LoginHistory history : historyList){

                            System.out.println("--------------------------------------");
                            System.out.println("Login Time : " + history.getLoginTime());
                            System.out.println("Status     : " + history.getStatus());

                        }

                        System.out.println("--------------------------------------");

                    }

                    break;

                case 9:

                    System.out.println("\n========== CHANGE MASTER PASSWORD ==========");

                    System.out.print("Enter Old Password: ");
                    String oldPassword = scanner.nextLine();

                    System.out.print("Enter New Password: ");
                    String newMasterPassword = scanner.nextLine();

                    boolean changed = authService.changeMasterPassword(
                            loggedInUser.getId(),
                            oldPassword,
                            newMasterPassword
                    );

                    if (changed) {

                        System.out.println("\n✅ Master Password Changed Successfully!");

                    } else {

                        System.out.println("\n❌ Old Password is Incorrect!");
                    }

                    break;

                case 10:

                    int totalCredentials =
                            credentialDAO.getTotalCredentials(loggedInUser.getId());

                    int totalLogins =
                            loginHistoryDAO.getTotalLogins(loggedInUser.getId());

                    Timestamp lastLogin =
                            loginHistoryDAO.getLastLogin(loggedInUser.getId());

                    System.out.println("\n=================================");
                    System.out.println("          USER STATISTICS");
                    System.out.println("=================================");
                    System.out.println("Username           : " + loggedInUser.getUsername());
                    System.out.println("Total Credentials  : " + totalCredentials);
                    System.out.println("Total Logins       : " + totalLogins);
                    System.out.println("Last Login         : " + lastLogin);
                    System.out.println("=================================");

                    break;

                case 11:

                    if (vaultService.exportCredentials(loggedInUser.getId())) {

                        System.out.println("\n✅ Credentials exported successfully!");
                        System.out.println("File created: credentials.csv");

                    } else {

                        System.out.println("\n❌ Export Failed!");
                    }

                    break;

                case 12:

                    System.out.println("Logged Out Successfully!");
                    return;
            }
        }
    }
}