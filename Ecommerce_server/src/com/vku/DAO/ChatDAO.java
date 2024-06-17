package com.vku.DAO;

import com.vku.Model.Chat;
import com.vku.DAO.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {

    // Thêm một tin nhắn mới
    public void addChat(Chat chat) throws SQLException {
        String query = "INSERT INTO Chat (user_id, receiver_id, message, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, chat.getUserId());
            preparedStatement.setInt(2, chat.getReceiverId());
            preparedStatement.setString(3, chat.getMessage());
            preparedStatement.setTimestamp(4, chat.getTimestamp());
            preparedStatement.executeUpdate();
        }
    }

    // Lấy tất cả các tin nhắn
    public List<Chat> getAllChats() throws SQLException {
        List<Chat> chats = new ArrayList<>();
        String query = "SELECT * FROM Chat";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int chatId = resultSet.getInt("chat_id");
                int userId = resultSet.getInt("user_id");
                int receiverId = resultSet.getInt("receiver_id");
                String message = resultSet.getString("message");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                Chat chat = new Chat(chatId, userId, receiverId, message, timestamp);
                chats.add(chat);
            }
        }
        return chats;
    }

    // Lấy tin nhắn theo ID
    public Chat getChatById(int chatId) throws SQLException {
        String query = "SELECT * FROM Chat WHERE chat_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, chatId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    int receiverId = resultSet.getInt("receiver_id");
                    String message = resultSet.getString("message");
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    return new Chat(chatId, userId, receiverId, message, timestamp);
                }
            }
        }
        return null;
    }

    // Cập nhật một tin nhắn
    public void updateChat(Chat chat) throws SQLException {
        String query = "UPDATE Chat SET user_id = ?, receiver_id = ?, message = ?, timestamp = ? WHERE chat_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, chat.getUserId());
            preparedStatement.setInt(2, chat.getReceiverId());
            preparedStatement.setString(3, chat.getMessage());
            preparedStatement.setTimestamp(4, chat.getTimestamp());
            preparedStatement.setInt(5, chat.getChatId());
            preparedStatement.executeUpdate();
        }
    }

    // Xóa một tin nhắn
    public void deleteChat(int chatId) throws SQLException {
        String query = "DELETE FROM Chat WHERE chat_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, chatId);
            preparedStatement.executeUpdate();
        }
    }
}
