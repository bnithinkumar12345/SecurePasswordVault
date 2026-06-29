package com.nithin.dao;

import com.nithin.config.DBConnection;
import com.nithin.model.Credential;
import com.nithin.util.AESUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialDAO {

    // Add Credential
    public boolean addCredential(Credential credential) {

        String sql = "INSERT INTO credentials(user_id, website, website_username, encrypted_password, notes) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, credential.getUserId());
            ps.setString(2, credential.getWebsite());
            ps.setString(3, credential.getWebsiteUsername());

            // Encrypt Password
            String encryptedPassword = AESUtil.encrypt(credential.getPassword());

            ps.setString(4, encryptedPassword);
            ps.setString(5, credential.getNotes());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // View All Credentials
    public List<Credential> getAllCredentials(int userId) {

        List<Credential> list = new ArrayList<>();

        String sql = "SELECT * FROM credentials WHERE user_id=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Credential credential = new Credential();

                credential.setId(rs.getInt("id"));
                credential.setUserId(rs.getInt("user_id"));
                credential.setWebsite(rs.getString("website"));
                credential.setWebsiteUsername(rs.getString("website_username"));

                // Decrypt Password
                credential.setPassword(
                        AESUtil.decrypt(rs.getString("encrypted_password"))
                );

                credential.setNotes(rs.getString("notes"));

                list.add(credential);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Credential
    public Credential searchCredential(int userId, String website) {

        String sql = "SELECT * FROM credentials WHERE user_id=? AND website=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, website);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Credential credential = new Credential();

                credential.setId(rs.getInt("id"));
                credential.setUserId(rs.getInt("user_id"));
                credential.setWebsite(rs.getString("website"));
                credential.setWebsiteUsername(rs.getString("website_username"));

                // Decrypt Password
                credential.setPassword(
                        AESUtil.decrypt(rs.getString("encrypted_password"))
                );

                credential.setNotes(rs.getString("notes"));

                return credential;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Credential
    public boolean updateCredential(int userId,
                                    String website,
                                    String newUsername,
                                    String newPassword,
                                    String newNotes) {

        String sql = "UPDATE credentials SET website_username=?, encrypted_password=?, notes=? WHERE user_id=? AND website=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, newUsername);

            // Encrypt Updated Password
            ps.setString(2, AESUtil.encrypt(newPassword));

            ps.setString(3, newNotes);
            ps.setInt(4, userId);
            ps.setString(5, website);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Credential
    public boolean deleteCredential(int userId, String website) {

        String sql = "DELETE FROM credentials WHERE user_id=? AND website=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, website);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}