package com.vku.DAO;

import com.vku.Model.Chat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
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

// Retrieve all chats between a sender and receiver
    public List<Chat> getAllChats(int senderId, int receiverId) {
        List<Chat> chats = new ArrayList<>();
        String query = "SELECT * FROM Chat WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, receiverId);
            preparedStatement.setInt(3, receiverId);
            preparedStatement.setInt(4, senderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int chatId = resultSet.getInt("chat_id");
                    int userId = resultSet.getInt("sender_id");
                    int receiverIdDb = resultSet.getInt("receiver_id");
                    String message = resultSet.getString("message");
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    Chat chat = new Chat(chatId, userId, receiverIdDb, message, timestamp);
                    chats.add(chat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }
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
public void deleteChat(int chatId) throws SQLException {
    String query = "DELETE FROM Chat WHERE chat_id = ?";
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, chatId);
        preparedStatement.executeUpdate();
    }
}

}
