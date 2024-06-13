package com.vku.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTransferDAO {

    // Rút tiền
    public void withdraw(int accountId, double amount) throws SQLException {
        String sql = "UPDATE MoneyTransfer SET Amount = Amount - ? WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        }
    }

    // Nạp tiền
    public void deposit(int accountId, double amount) throws SQLException {
        String sql = "UPDATE MoneyTransfer SET Amount = Amount + ? WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        }
    }

    // Chuyển tiền
    public void transfer(int fromAccountId, int toAccountId, double amount) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtWithdraw = null;
        PreparedStatement stmtDeposit = null;
        String sqlWithdraw = "UPDATE MoneyTransfer SET Amount = Amount - ? WHERE id = ?";
        String sqlDeposit = "UPDATE MoneyTransfer SET Amount = Amount + ? WHERE id = ?";
        
        try {
            conn = Database.getConnection();
            conn.setAutoCommit(false);  // Bắt đầu giao dịch

            // Rút tiền từ tài khoản người gửi
            stmtWithdraw = conn.prepareStatement(sqlWithdraw);
            stmtWithdraw.setDouble(1, amount);
            stmtWithdraw.setInt(2, fromAccountId);
            stmtWithdraw.executeUpdate();

            // Nạp tiền vào tài khoản người nhận
            stmtDeposit = conn.prepareStatement(sqlDeposit);
            stmtDeposit.setDouble(1, amount);
            stmtDeposit.setInt(2, toAccountId);
            stmtDeposit.executeUpdate();

            conn.commit();  // Xác nhận giao dịch

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // Rollback nếu có lỗi
            }
            throw e;
        } 
    }

    // Truy vấn số dư
    public double getBalance(int userId) throws SQLException {
        String sql = "SELECT Amount FROM MoneyTransfer WHERE userId = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("Amount");
                } else {
                    throw new SQLException("No account found for user ID: " + userId);
                }
            }
        }
    }
}
