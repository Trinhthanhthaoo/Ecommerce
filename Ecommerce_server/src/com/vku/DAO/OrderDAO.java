package com.vku.DAO;

import com.vku.Model.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection connection;
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM Orders WHERE order_id = ?";

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // Thêm một đơn hàng mới vào cơ sở dữ liệu
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO Orders (user_id, order_date, total_amount) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getUserID());
            statement.setDate(2, order.getOrderDate());
            statement.setDouble(3, order.getTotalAmount());
            int rowsAffected = statement.executeUpdate();

            // Kiểm tra xem hàng đã được chèn thành công chưa
            if (rowsAffected > 0) {
                ResultSet r = statement.getGeneratedKeys();
                if (r.next()) {
                    int orderId = r.getInt(1);
                    order.setOrderId(orderId); // Cập nhật ID đơn hàng vào đối tượng Order
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa một đơn hàng khỏi cơ sở dữ liệu
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả các đơn hàng từ cơ sở dữ liệu
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int userID = resultSet.getInt("user_id");
                Date orderDate = resultSet.getDate("order_date");
                double totalAmount = resultSet.getDouble("total_amount");
                Order order = new Order(orderId, userID, orderDate, totalAmount);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Tìm kiếm đơn hàng theo từ khóa
    public List<Order> getSearch(String keyword) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE user_id LIKE ? OR order_date LIKE ? OR total_amount LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("user_id");
                Date orderDate = resultSet.getDate("order_date");
                double totalAmount = resultSet.getDouble("total_amount");
                Order order = new Order(orderId, customerId, orderDate, totalAmount);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

   // Cập nhật thông tin một đơn hàng trong cơ sở dữ liệu
public boolean updateOrder(Order order) {
    String sql = "UPDATE Orders SET user_id = ?, order_date = ?, total_amount = ? WHERE order_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, order.getUserID());
        statement.setDate(2, order.getOrderDate());
        statement.setDouble(3, order.getTotalAmount());
        statement.setInt(4, order.getOrderId());
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    // Lấy một đơn hàng theo ID
    public Order getOrderById(int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userID = resultSet.getInt("user_id");
                    Date orderDate = resultSet.getDate("order_date");
                    double totalAmount = resultSet.getDouble("total_amount");
                    return new Order(orderId, userID, orderDate, totalAmount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
