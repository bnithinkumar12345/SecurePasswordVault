package com.nithin.service;

import com.nithin.dao.CredentialDAO;
import com.nithin.model.Credential;

import java.util.List;

public class VaultService {

    private CredentialDAO credentialDAO = new CredentialDAO();

    // Add Credential
    public boolean addCredential(int userId,
                                 String website,
                                 String websiteUsername,
                                 String password,
                                 String notes) {

        Credential credential = new Credential(
                userId,
                website,
                websiteUsername,
                password,
                notes
        );

        return credentialDAO.addCredential(credential);
    }

    // View All Credentials
    public List<Credential> getAllCredentials(int userId) {

        return credentialDAO.getAllCredentials(userId);
    }

    // Search Credential
    public Credential searchCredential(int userId, String website) {

        return credentialDAO.searchCredential(userId, website);
    }

    // Update Credential
    public boolean updateCredential(int userId,
                                    String website,
                                    String newUsername,
                                    String newPassword,
                                    String newNotes) {

        return credentialDAO.updateCredential(
                userId,
                website,
                newUsername,
                newPassword,
                newNotes
        );
    }

    // Delete Credential
    public boolean deleteCredential(int userId,
                                    String website) {

        return credentialDAO.deleteCredential(userId, website);
    }
}