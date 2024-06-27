package com.vku.DAO;

import com.vku.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class productDAO {
    // Thêm một sản phẩm mới vào cơ sở dữ liệu
    public static boolean addProduct(Product product) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.err.println("Không thể lấy kết nối đến cơ sở dữ liệu.");
            return false;
        }

        String sql = "INSERT INTO Products (name, description, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStock());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Cập nhật thông tin của một sản phẩm trong cơ sở dữ liệu
    public static boolean updateProduct(Product product) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.err.println("Không thể lấy kết nối đến cơ sở dữ liệu.");
            return false;
        }

        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, stock = ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getProductId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Xóa sản phẩm khỏi cơ sở dữ liệu
    public static boolean deleteProduct(int productId) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.err.println("Không thể lấy kết nối đến cơ sở dữ liệu.");
            return false;
        }

        try {
            // Xóa tất cả các đơn hàng chi tiết có product_id tương ứng
            String deleteOrderDetailsSql = "DELETE FROM OrderDetails WHERE product_id = ?";
            try (PreparedStatement deleteOrderDetailsStatement = connection.prepareStatement(deleteOrderDetailsSql)) {
                deleteOrderDetailsStatement.setInt(1, productId);
                deleteOrderDetailsStatement.executeUpdate();
            }

            // Sau khi xóa các đơn hàng chi tiết, thực hiện xóa sản phẩm
            String deleteProductSql = "DELETE FROM Products WHERE product_id = ?";
            try (PreparedStatement deleteProductStatement = connection.prepareStatement(deleteProductSql)) {
                deleteProductStatement.setInt(1, productId);
                int rowsAffected = deleteProductStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

  public static List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    Connection connection = Database.getConnection();
    if (connection == null) {
        System.err.println("Không thể lấy kết nối đến cơ sở dữ liệu.");
        return products;
    }

    String sql = "SELECT * FROM Products";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int stock = resultSet.getInt("stock");
            Product product = new Product(productId, name, description, price, stock);
            products.add(product);
        }
        System.out.println("Retrieved products from database: " + products.size());
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return products;
}


    // Tìm kiếm sản phẩm dựa trên từ khóa
    public static List<Product> getSearch(String keyword) {
        List<Product> products = new ArrayList<>();
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.err.println("Không thể lấy kết nối đến cơ sở dữ liệu.");
            return products;
        }

        try {
            String sql = "SELECT * FROM Products WHERE product_id LIKE ? OR name LIKE ? OR description LIKE ? OR price LIKE ? OR stock LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, '%' + keyword + '%');
            ps.setString(2, '%' + keyword + '%');
            ps.setString(3, '%' + keyword + '%');
            ps.setString(4, '%' + keyword + '%');
            ps.setString(5, '%' + keyword + '%');
            ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
            int productId = rs.getInt("product_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            int stock = rs.getInt("stock");
            Product product = new Product(productId, name, description, price, stock);
            products.add(product);
        }
    } catch (SQLException ex) {
        Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return products;
}
    
   public static List<Product> getProductByMa(int productId) {
    List<Product> products = new ArrayList<>();
    Connection connection = Database.getConnection();
    if (connection == null) {
        System.err.println("Không thể kết nối đến cơ sở dữ liệu.");
        return products;
    }

    String sql = "SELECT * FROM Products WHERE product_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int stock = resultSet.getInt("stock");

            Product product = new Product(id, name, description, price, stock);
            products.add(product);
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
    return products;
}

}
