package com.nithin.dao;

import com.nithin.config.DBConnection;
import com.nithin.model.LoginHistory;
import com.nithin.util.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginHistoryDAO {

    // Save Login History
    public void saveLoginHistory(int userId, String status) {

        String sql = "INSERT INTO login_history(user_id, status) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, status);

            ps.executeUpdate();

        } catch (SQLException e) {

            ErrorHandler.showDatabaseError(e);
        }
    }

    // Get Login History
    public List<LoginHistory> getLoginHistory(int userId) {

        List<LoginHistory> historyList = new ArrayList<>();

        String sql = "SELECT * FROM login_history WHERE user_id=? ORDER BY login_time DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    LoginHistory history = new LoginHistory();

                    history.setId(rs.getInt("id"));
                    history.setUserId(rs.getInt("user_id"));
                    history.setLoginTime(rs.getTimestamp("login_time"));
                    history.setStatus(rs.getString("status"));

                    historyList.add(history);
                }
            }

        } catch (SQLException e) {

            ErrorHandler.showDatabaseError(e);
        }

        return historyList;
    }

    // Get Total Logins
    public int getTotalLogins(int userId) {

        String sql = "SELECT COUNT(*) FROM login_history WHERE user_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {

            ErrorHandler.showDatabaseError(e);
        }

        return 0;
    }

    // Get Last Login
    public Timestamp getLastLogin(int userId) {

        String sql = "SELECT MAX(login_time) FROM login_history WHERE user_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getTimestamp(1);
                }
            }

        } catch (SQLException e) {

            ErrorHandler.showDatabaseError(e);
        }

        return null;
    }
}