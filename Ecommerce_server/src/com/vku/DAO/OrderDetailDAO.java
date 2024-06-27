package com.vku.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.vku.Model.OrderDetail;
import com.vku.DAO.Database;

public class OrderDetailDAO {

    // Thêm một chi tiết đơn hàng mới vào cơ sở dữ liệu
    public boolean addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderDetail.getOrderId());
            statement.setInt(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setDouble(4, orderDetail.getPrice());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   public boolean deleteOrderDetail(int orderId, int productId) {
        String sql = "DELETE FROM OrderDetails WHERE order_id = ? AND product_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Lấy danh sách các chi tiết đơn hàng dựa trên orderId
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails WHERE order_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderDetailId = resultSet.getInt("order_detail_id");
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, productId, quantity, price);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    // Tìm kiếm chi tiết đơn hàng theo từ khóa
    public List<OrderDetail> searchOrderDetail(String keyword) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails WHERE product_id LIKE ? OR quantity LIKE ? OR price Like ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Gán giá trị cho các tham số của câu truy vấn
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderDetailId = resultSet.getInt("order_detail_id");
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, productId, quantity, price);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    // Cập nhật thông tin của một chi tiết đơn hàng
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        String sql = "UPDATE OrderDetails SET order_id = ?, product_id = ?, quantity = ?, price = ? WHERE order_detail_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderDetail.getOrderId());
            statement.setInt(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setDouble(4, orderDetail.getPrice());
            statement.setInt(5, orderDetail.getOrderDetailId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
