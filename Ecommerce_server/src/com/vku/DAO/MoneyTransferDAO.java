package com.vku.DAO;

import com.vku.Model.History;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static void saveTransactionHistory(String ID, String User_ID, String details, String updateDate) throws SQLException {
    String sql = "INSERT INTO History (ID, User_ID, Details, UpdateDate) VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
        ps.setString(1, ID);
        ps.setString(2, User_ID);
        ps.setString(3, details);  
        ps.setString(4, updateDate);  

        ps.executeUpdate();
    }
}

    public static List<History> getAllHistory() {
        List<History> his = new ArrayList<>();
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.err.println("Không thể lấy kết nối đến cơ sở dữ liệu.");
            return his;
        }

        String sql = "SELECT * FROM History";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                String userID = resultSet.getString("User_ID");
                String details = resultSet.getString("details");
                String update = resultSet.getString("updateDate");

                History history = new History();
                history.setId(ID);
                history.setUserId(userID);  // Corrected field name
                history.setDetails(details);
                history.setUpdateDate(update);
                his.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return his;
    }
}
