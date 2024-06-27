package com.vku.Server;

import com.google.gson.Gson;
import com.vku.DAO.ChatDAO;
import com.vku.DAO.Database;
import com.vku.DAO.MoneyTransferDAO;
import static com.vku.DAO.MoneyTransferDAO.saveTransactionHistory;
import com.vku.DAO.OrderDAO;
import com.vku.DAO.OrderDetailDAO;
import com.vku.DAO.UserDAO;
import com.vku.DAO.productDAO; // Giả định tên lớp ProductDAO cần được viết hoa chữ cái đầu
import static com.vku.DAO.productDAO.getProductByMa;
import com.vku.Model.Chat;
import com.vku.Model.History;
import com.vku.Model.MoneyTransfer;
import com.vku.Model.Order;
import com.vku.Model.OrderDetail;
import com.vku.Model.Product;
import com.vku.Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
                java.util.Date date = new java.util.Date();  // Thời gian hiện tại
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                String updateDate = formatter.format(date);  // Định dạng thời gian

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
                }else if (messageType.equals("REGISTER")) {
                    try {
                        username = inputStream.readUTF();
                        password = inputStream.readUTF();
                        String role = inputStream.readUTF();

                        User user = UserDAO.register(username, password, role);

                        if (user != null) {
                            outputStream.writeBoolean(true);
                            outputStream.writeUTF(user.getRole());
                        } else {
                            outputStream.writeBoolean(false);
                        }
                    } catch (IOException e) {
                        System.out.println("Error handling registration request: " + e.getMessage());
                        try {
                            outputStream.writeBoolean(false);
                        } catch (IOException ex) {
                            System.out.println("Error sending failure response: " + ex.getMessage());
                        }
                    }
                }
                else if (messageType.equals("WITHDRAW")) { //RutTien
                     outputStream.writeByte(1);  // Indicate message type success
                    outputStream.flush();

                    String id = inputStream.readUTF();  // Read ID from client
                     username = inputStream.readUTF();
                     password = inputStream.readUTF();
                     amount = inputStream.readDouble();
                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        try {
                            MoneyTransferDAO moneyTransferDAO = new MoneyTransferDAO();
                            moneyTransferDAO.withdraw(user.getUserId(), amount);
                              // Tạo chi tiết lịch sử giao dịch
                                String transactionId = java.util.UUID.randomUUID().toString();
                                String details = "rút tiền" + recipientAccount + " tien: " + amount; 
                                
                                // Lưu lịch sử giao dịch
                                saveTransactionHistory(transactionId, username, details, updateDate);
                            
                            outputStream.writeBoolean(true);
                        } catch (SQLException e) {
                            outputStream.writeBoolean(false);
                            System.out.println("Lỗi khi rút tiền: " + e.getMessage());
                        }
                    } else {
                        outputStream.writeBoolean(false);
                    }
                     outputStream.writeByte(-1);  // Indicate message type success
                    outputStream.flush();

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
                           // Tạo chi tiết lịch sử giao dịch
                                String transactionId = java.util.UUID.randomUUID().toString();
                                String details = "chuyen tien toi " + recipientAccount + " tien: " + amount; 
                                
                                // Lưu lịch sử giao dịch
                                saveTransactionHistory(transactionId, username, details, updateDate);
                            
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
                } else if (messageType.equals("DEPOSIT")) {  // Handle deposit request
                    outputStream.writeByte(1);  // Indicate message type success
                    outputStream.flush();

                    String id = inputStream.readUTF();  // Read ID from client
                     username = inputStream.readUTF();
                     password = inputStream.readUTF();
                     amount = inputStream.readDouble();

                    // Authenticate user using ID, username, and password
                    User user = UserDAO.checkLogin(username, password);
                    if (user != null) {
                        try {
                            MoneyTransferDAO moneyTransferDAO = new MoneyTransferDAO();
                            moneyTransferDAO.deposit(user.getUserId(), amount);
                            outputStream.writeBoolean(true);  // Indicate success
                        } catch (SQLException e) {
                            outputStream.writeBoolean(false);  // Indicate failure
                            System.out.println("Error depositing money: " + e.getMessage());
                        }
                    } else {
                        outputStream.writeBoolean(false);  // Indicate authentication failure
                    }
                    outputStream.writeByte(-1);  // Indicate end of message
                    outputStream.flush();
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
                } else if (messageType.equals("SEARCH_PRODUCT")) {
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();

                        // Đọc keyword từ client
                        String keyword = inputStream.readUTF();

                        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
                        List<Product> products = productDAO.getSearch(keyword);
                          String strOrders = gson.toJson(products);
                        outputStream.writeUTF(strOrders);
                        // Gửi số lượng sản phẩm tìm được
                        outputStream.writeInt(products.size());

                        // Gửi từng sản phẩm chi tiết
                        for (Product product : products) {
                            outputStream.writeInt(product.getProductId());
                            outputStream.writeUTF(product.getName());
                            outputStream.writeDouble(product.getPrice());
                            // Gửi thêm các thông tin khác của sản phẩm nếu cần thiết
                        }
                    } catch (IOException e) {
                        System.out.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
                    } finally {
                        try {
                            outputStream.writeByte(-1); // Gửi byte kết thúc
                            outputStream.flush();
                        } catch (IOException e) {
                            System.out.println("Lỗi khi gửi phản hồi kết thúc: " + e.getMessage());
                        }
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
                        outputStream.writeByte(1);
                        outputStream.flush();
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
                    outputStream.writeByte(-1);
                    outputStream.flush();
                } else if (messageType.equals("UPDATE_ORDER")) { // cập nhật đơn hàng
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();

                        int orderId = inputStream.readInt();
                        int customerId = inputStream.readInt();
                        String orderDate = inputStream.readUTF();
                        double totalAmount = inputStream.readDouble();

                        Order newOrder = new Order(orderId, customerId, Date.valueOf(orderDate), totalAmount);
                        boolean success = orderDAO.updateOrder(newOrder);

                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        System.out.println("Lỗi khi cập nhật đơn hàng: " + e.getMessage());
                    } finally {
                        try {
                            outputStream.writeByte(-1);
                            outputStream.flush();
                        } catch (IOException e) {
                            System.out.println("Lỗi khi đóng luồng: " + e.getMessage());
                        }
                    }
                } else if (messageType.equals("GET_ALL_ORDER")) { // lấy tất cả đơn hàng
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();

                        List<Order> orders = orderDAO.getAllOrders();
                        String strOrders = gson.toJson(orders);
                        outputStream.writeUTF(strOrders);
                        outputStream.flush();
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
                } else if (messageType.equals("GET_ALL_PRODUCTS")) { // lấy tất cả sản phẩm
                    try {
                        System.out.println("Handling GET_ALL_PRODUCTS request.");

                        outputStream.writeByte(1);
                        outputStream.flush();

                        List<Product> products = productDAO.getAllProducts();
                        String strProducts = gson.toJson(products);
                        System.out.println("Sending products JSON: " + strProducts);

                        outputStream.writeUTF(strProducts);
                        outputStream.flush();
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
                } else if (messageType.equals("getProductByMa")) {
                    try {
                        // Thông báo cho máy khách biết rằng quá trình lấy sản phẩm đang bắt đầu
                        outputStream.writeByte(1);
                        outputStream.flush();

                        // Đọc mã sản phẩm từ tin nhắn của máy khách
                        int productId = inputStream.readInt();

                        // Lấy thông tin sản phẩm từ cơ sở dữ liệu
                        List<Product> products = getProductByMa(productId);

                        // Chuyển đổi danh sách sản phẩm thành chuỗi JSON
                        String strProducts = gson.toJson(products);

                        // Gửi chuỗi JSON cho máy khách
                        outputStream.writeUTF(strProducts);
                        outputStream.flush();

                        // Thông báo cho máy khách biết rằng quá trình lấy sản phẩm đã hoàn thành
                        outputStream.writeByte(-1);
                        outputStream.flush();

                        // Ghi thông tin chat vào cơ sở dữ liệu (tuỳ chọn)
                        // Chat chat = new Chat(0, senderId, message, timestamp);
                        // chatDAO.addChat(chat);
                        // Thông báo cho máy khách biết là quá trình thành công
                        outputStream.writeBoolean(true);
                        outputStream.flush();
                    } catch (IOException e) {
                        // Xử lý các ngoại lệ
                        outputStream.writeBoolean(false);
                        outputStream.flush();
                        System.out.println("Lỗi khi gửi chi tiết sản phẩm: " + e.getMessage());
                    }
                }
                else if (messageType.equals("GET_ALL_HISTORY")) { // lấy tất cả đơn hàng
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();

                        List<History> his = MoneyTransferDAO.getAllHistory();
                        String strOrders = gson.toJson(his);
                        outputStream.writeUTF(strOrders);
                        outputStream.flush();
                        outputStream.writeByte(-1);
                        outputStream.flush();

                    } catch (IOException e) {
                        System.out.println("Lỗi khi lấy tất cả: " + e.getMessage());
                    }
                } else if (messageType.equals("ADD_ORDERDETAILS")) {
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();
                        int orderId = inputStream.readInt();
                        int productId = inputStream.readInt();
                        int quantity = inputStream.readInt();
                        double price = inputStream.readDouble();

                        OrderDetail newOrderDetail = new OrderDetail(orderId, productId, quantity, price);
                        boolean success = orderDetailDAO.addOrderDetail(newOrderDetail);
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        outputStream.writeBoolean(false);
                        System.out.println("Lỗi khi thêm chi tiết đơn hàng: " + e.getMessage());
                    }
                    outputStream.writeByte(-1);
                    outputStream.flush();
                } else if (messageType.equals("UPDATE_PASSWORD")) {
                    try {
                        // Gửi phản hồi xác nhận yêu cầu đã được nhận
                        outputStream.writeByte(1);
                        outputStream.flush();

                        // Đọc user ID và mật khẩu mới từ input stream
                        int userId = inputStream.readInt();
                        String newPassword = inputStream.readUTF();

                        System.out.println("UserID: " + userId);
                        System.out.println("New Password: " + newPassword);

                        // Cập nhật mật khẩu người dùng
                        boolean success = UserDAO.updateUserPassword(userId, newPassword);

                        // Gửi trạng thái thành công hoặc thất bại tới output stream
                        outputStream.writeBoolean(success);
                    } catch (IOException e) {
                        System.out.println("Lỗi khi cập nhật mật khẩu: " + e.getMessage());
                        try {
                            outputStream.writeBoolean(false);
                        } catch (IOException ioException) {
                            System.out.println("Lỗi khi gửi phản hồi: " + ioException.getMessage());
                        }
                    }
                    outputStream.writeByte(-1);
                    outputStream.flush();
                } else if (messageType.equals("BUILD_EXCEL")) {
                    try {
                        outputStream.writeByte(1);
                        outputStream.flush();

                        List<Product> products = productDAO.getAllProducts(); // Retrieve all products

                        Workbook workbook = new XSSFWorkbook();
                        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Products");

                        // Create header row
                        Row headerRow = sheet.createRow(0);
                        headerRow.createCell(0).setCellValue("Product ID");
                        headerRow.createCell(1).setCellValue("Name");
                        headerRow.createCell(2).setCellValue("Description");
                        headerRow.createCell(3).setCellValue("Price");
                        headerRow.createCell(4).setCellValue("Stock");

                        // Populate data rows
                        int rowNum = 1;
                        for (Product product : products) {
                            Row row = sheet.createRow(rowNum++);
                            row.createCell(0).setCellValue(product.getProductId());
                            row.createCell(1).setCellValue(product.getName());
                            row.createCell(2).setCellValue(product.getDescription());
                            row.createCell(3).setCellValue(product.getPrice());
                            row.createCell(4).setCellValue(product.getStock());
                        }

                        // Write the output to a file
                        try (FileOutputStream fileOut = new FileOutputStream("D:/products.xlsx")) {
                            workbook.write(fileOut);
                        }
                        workbook.close();
                        outputStream.writeBoolean(true); // Success response
                        outputStream.writeByte(-1);
                        outputStream.flush();
                    } catch (IOException e) {
                        outputStream.writeBoolean(false); // Error response
                        System.out.println("Lỗi khi tạo file Excel: " + e.getMessage());
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
