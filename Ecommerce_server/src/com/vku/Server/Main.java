package com.vku.Server;

import com.google.gson.Gson;
import com.vku.DAO.ChatDAO;
import com.vku.DAO.Database;
import com.vku.DAO.MoneyTransferDAO;
import com.vku.DAO.OrderDAO;
import com.vku.DAO.OrderDetailDAO;
import com.vku.DAO.UserDAO;
import com.vku.DAO.productDAO; // Giả định tên lớp ProductDAO cần được viết hoa chữ cái đầu
import com.vku.Model.Chat;
import com.vku.Model.Order;
import com.vku.Model.OrderDetail;
import com.vku.Model.Product;
import com.vku.Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        try (ServerSocket serverSK = new ServerSocket(8300)) {
            System.out.println("Server đang chạy trên cổng 8300");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSK.accept();
                System.out.println("Client mới đã kết nối: " + clientSocket);

                // Tạo luồng đầu vào và đầu ra
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
                
                Gson gson = new Gson();
                
                System.out.println("Kết nối thành công");

                // Đọc loại thông điệp từ client
                String messageType = inputStream.readUTF();
                String username = "";
                String password = "";
                double amount = 0;
                String recipientAccount = "";

                // Kết nối cơ sở dữ liệu
                Connection connection = Database.getConnection();
                OrderDAO orderDAO = new OrderDAO(connection);
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                productDAO productDAO = new productDAO();
                ChatDAO chatDAO = new ChatDAO();
                System.out.println("messageType: " + messageType);

                // Xử lý các loại thông điệp khác nhau
                if (messageType.equals("LOGIN")) {
                    username = inputStream.readUTF();
                    password = inputStream.readUTF();
                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        outputStream.writeBoolean(true);
                        outputStream.writeUTF(user.getRole());
                    } else {
                        outputStream.writeBoolean(false);
                    }
                } else if (messageType.equals("WITHDRAW")) { //RutTien
                    username = inputStream.readUTF();
                    password = inputStream.readUTF();
                    amount = inputStream.readDouble();

                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        try {
                            MoneyTransferDAO moneyTransferDAO = new MoneyTransferDAO();
                            moneyTransferDAO.withdraw(user.getUserId(), amount);
                            outputStream.writeBoolean(true);
                        } catch (SQLException e) {
                            outputStream.writeBoolean(false);
                            System.out.println("Lỗi khi rút tiền: " + e.getMessage());
                        }
                    } else {
                        outputStream.writeBoolean(false);
                    }
                } else if (messageType.equals("TRANSFER")) { //Chuyentien
                    outputStream.writeByte(1);
                    outputStream.flush();
                    
                    username = inputStream.readUTF();
                    password = inputStream.readUTF();
                    recipientAccount = inputStream.readUTF();
                    amount = inputStream.readDouble();

                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        try {
                            MoneyTransferDAO moneyTransferDAO = new MoneyTransferDAO();
                            moneyTransferDAO.transfer(user.getUserId(), Integer.parseInt(recipientAccount), amount);
                            outputStream.writeBoolean(true);
                            outputStream.flush();
                        } catch (SQLException e) {
                            outputStream.writeBoolean(false);
                            System.out.println("Lỗi khi chuyển tiền: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            outputStream.writeBoolean(false);
                            System.out.println("Tài khoản người nhận không hợp lệ: " + recipientAccount);
                        }
                    } else {
                        outputStream.writeBoolean(false);
                    }
                    
                    outputStream.writeByte(-1);
                    outputStream.flush();
                } else if (messageType.equals("DEPOSIT")) {  //Nạp tiền                    username = inputStream.readUTF();
                    password = inputStream.readUTF();
                    amount = inputStream.readDouble();

                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        try {
                            MoneyTransferDAO moneyTransferDAO = new MoneyTransferDAO();
                            moneyTransferDAO.deposit(user.getUserId(), amount);
                            outputStream.writeBoolean(true);
                        } catch (SQLException e) {
                            outputStream.writeBoolean(false);
                            System.out.println("Lỗi khi nạp tiền: " + e.getMessage());
                        }
                    } else {
                        outputStream.writeBoolean(false);
                    }
                } else if (messageType.equals("CHECK_BALANCE")) { //check tài khoản
                    username = inputStream.readUTF();
                    password = inputStream.readUTF();

                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        try {
                            MoneyTransferDAO moneyTransferDAO = new MoneyTransferDAO();
                            double balance = moneyTransferDAO.getBalance(user.getUserId());
                            outputStream.writeBoolean(true);
                            outputStream.writeDouble(balance);
                        } catch (IOException e) {
                            outputStream.writeBoolean(false);
                            System.out.println("Lỗi khi kiểm tra số dư: " + e.getMessage());
                        }
                    } else {
                        outputStream.writeBoolean(false);
                    }
                } else if (messageType.equals("SEARCH_PRODUCT")) { //Search sp
                    try {
                        String keyword = inputStream.readUTF();
                        List<Product> products = productDAO.getSearch(keyword);

                        outputStream.writeInt(products.size());
                        for (Product product : products) {
                            outputStream.writeInt(product.getProductId());
                            outputStream.writeUTF(product.getName());
                            outputStream.writeDouble(product.getPrice());
                        }
                    } catch (IOException e) {
                        System.out.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
                    }
                } else if (messageType.equals("DELETE_PRODUCT")) { // xóa sp
                     outputStream.writeByte(1);
                    outputStream.flush();

                    try {
                        int productId = inputStream.readInt();
                        boolean success = productDAO.deleteProduct(productId);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
                    }
                     outputStream.writeByte(-1);
                    outputStream.flush();

                } else if (messageType.equals("ADD_ORDER")) {
                    try {
                        int customerId = inputStream.readInt();
                        String orderDate = inputStream.readUTF();
                        double totalAmount = inputStream.readDouble();

                        Order newOrder = new Order(customerId, Date.valueOf(orderDate), totalAmount);
                        boolean success = orderDAO.addOrder(newOrder);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi thêm đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("UPDATE_ORDER")) { // cập nhật sản phẩm
                    try {
                         int customerId = inputStream.readInt();
                        String orderDate = inputStream.readUTF();
                        double totalAmount = inputStream.readDouble();

                        Order newOrder = new Order(customerId, Date.valueOf(orderDate), totalAmount);
                        boolean success = orderDAO.updateOrder(newOrder);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
                    }
                }else if (messageType.equals("GET_ALL_ORDER")) { // lấy tất cả đơn hàng
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();

                        List<Order> orders = orderDAO.getAllOrders();
                        String strOrders = gson.toJson(orders);
                        outputStream.writeUTF(strOrders);
                        outputStream.flush();
//                        for (Order order : orders) {
//                            outputStream.writeInt(order.getOrderId());
//                            outputStream.writeInt(order.getCustomerId());
//                            outputStream.writeDouble(order.getTotalAmount());
//                            outputStream.writeUTF(order.getOrderDate().toString());
//                        }
                        outputStream.writeByte(-1);
                        outputStream.flush();

                    } catch (IOException e) {
                        System.out.println("Lỗi khi lấy tất cả đơn hàng: " + e.getMessage());
                    }
                }else if (messageType.equals("ADD_PRODUCT")) { // thêm sản phẩm
                    outputStream.writeByte(1);
                    outputStream.flush();

                    try {
                        String name = inputStream.readUTF();
                        String description = inputStream.readUTF();
                        double price = inputStream.readDouble();
                        int stock = inputStream.readInt();

                        Product product = new Product(0, name, description, price, stock);
                        boolean success = productDAO.addProduct(product);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
                    }
                finally {
                        outputStream.writeByte(-1);
                        outputStream.flush();
                    }
                }  else if (messageType.equals("UPDATE_PRODUCT")) { // cập nhật sản phẩm
                    outputStream.writeByte(1);
                    outputStream.flush();
                    try {
                        int productId = inputStream.readInt();
                        String name = inputStream.readUTF();
                        String description = inputStream.readUTF();
                        double price = inputStream.readDouble();
                        int stock = inputStream.readInt();

                        Product product = new Product(productId, name, description, price, stock);
                        boolean success = productDAO.updateProduct(product);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
                    }
                    outputStream.writeByte(1);
                    outputStream.flush();
                }else if (messageType.equals("GET_ALL_PRODUCTS")) { // lấy tất cả sản phẩm
                    try {
                        
                        outputStream.writeByte(1);
                        outputStream.flush();
                        
                        List<Product> products = productDAO.getAllProducts();
                        String strProducts = gson.toJson(products);
                        
                        outputStream.writeUTF(strProducts);
                        outputStream.flush();
//                        outputStream.writeInt(products.size());
//                        for (Product product : products) {
//                            outputStream.writeInt(product.getProductId());
//                            outputStream.writeUTF(product.getName());
//                            outputStream.writeUTF(product.getDescription());
//                            outputStream.writeDouble(product.getPrice());
//                            outputStream.writeInt(product.getStock());
//                        }
                        outputStream.writeByte(-1);
                        outputStream.flush();

                    } catch (IOException e) {
                        System.out.println("Lỗi khi lấy tất cả sản phẩm: " + e.getMessage());
                    }
                } else if (messageType.equals("DELETE_ORDER")) {
                    try {
                        int orderId = inputStream.readInt();
                        boolean success = orderDAO.deleteOrder(orderId);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi xóa đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("SEARCH_ORDER")) {
                    try {
                        int customerId = inputStream.readInt();
                        String keyword = inputStream.readUTF();
                        List<Order> orders = orderDAO.getSearch(keyword);

                        outputStream.writeInt(orders.size());
                        for (Order order : orders) {
                            outputStream.writeInt(order.getOrderId());
                            outputStream.writeInt(order.getUserID());
                            outputStream.writeUTF(order.getOrderDate().toString());
                            outputStream.writeDouble(order.getTotalAmount());
                        }
                    } catch (IOException e) {
                        System.out.println("Lỗi khi tìm kiếm đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("ADD_ORDER_DETAIL")) {
                    try {
                        int orderId = inputStream.readInt();
                        int productId = inputStream.readInt();
                        int quantity = inputStream.readInt();
                        double unitPrice = inputStream.readDouble();

                        OrderDetail newOrderDetail = new OrderDetail(orderId, productId, quantity, unitPrice);
                        boolean success = orderDetailDAO.addOrderDetail(newOrderDetail);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi thêm chi tiết đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("DELETE_ORDER_DETAIL")) {
                    try {
                        int orderDetailId = inputStream.readInt();
                        boolean success = orderDetailDAO.deleteOrderDetail(orderDetailId);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi xóa chi tiết đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("UPDATE_ORDER_DETAIL")) {
                    try {
                        int orderDetailId = inputStream.readInt();
                        int orderId = inputStream.readInt();
                        int productId = inputStream.readInt();
                        int quantity = inputStream.readInt();
                        double price = inputStream.readDouble();

                        OrderDetail updatedOrderDetail = new OrderDetail(orderDetailId, orderId, productId, quantity, price);
                        boolean success = orderDetailDAO.updateOrderDetail(updatedOrderDetail);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi cập nhật chi tiết đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("SEARCH_ORDER_DETAIL_BY_ORDER_ID")) {
                    try {
                        int orderId = inputStream.readInt();
                        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);

                        outputStream.writeInt(orderDetails.size());
                        for (OrderDetail orderDetail : orderDetails) {
                            outputStream.writeInt(orderDetail.getOrderDetailId());
                            outputStream.writeInt(orderDetail.getOrderId());
                            outputStream.writeInt(orderDetail.getProductId());
                            outputStream.writeInt(orderDetail.getQuantity());
                            outputStream.writeDouble(orderDetail.getPrice());
                        }
                    } catch (IOException e) {
                        System.out.println("Lỗi khi lấy chi tiết đơn hàng theo mã đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("SEARCH_ORDER_DETAIL")) {
                    try {
                        String keyword = inputStream.readUTF();
                        List<OrderDetail> orderDetails = orderDetailDAO.searchOrderDetail(keyword);

                        outputStream.writeInt(orderDetails.size());
                        for (OrderDetail orderDetail : orderDetails) {
                            outputStream.writeInt(orderDetail.getOrderDetailId());
                            outputStream.writeInt(orderDetail.getOrderId());
                            outputStream.writeInt(orderDetail.getProductId());
                            outputStream.writeInt(orderDetail.getQuantity());
                            outputStream.writeDouble(orderDetail.getPrice());
                        }
                    } catch (IOException e) {
                        System.out.println("Lỗi khi tìm kiếm chi tiết đơn hàng: " + e.getMessage());
                    }
                } else if (messageType.equals("SEND_CHAT")) {
                    try {
                        int senderId = inputStream.readInt();
                        int receiverId = inputStream.readInt();
                        String message = inputStream.readUTF();
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        Chat chat = new Chat(0, senderId, message, timestamp); // Assuming receiverId is not needed here
                        chatDAO.addChat(chat);
                        outputStream.writeBoolean(true);
                    } catch (IOException | SQLException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi gửi tin nhắn: " + e.getMessage());
                    }
                }


                // Đóng kết nối với client
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
