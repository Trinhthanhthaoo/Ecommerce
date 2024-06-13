package com.vku.Server;
import com.vku.Model.Product;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHelper {

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            Socket clientSK = new Socket("localhost", 8300);
            DataOutputStream outClient = new DataOutputStream(clientSK.getOutputStream());
            DataInputStream inClient = new DataInputStream(clientSK.getInputStream());

            // Gửi yêu cầu lấy tất cả sản phẩm
            outClient.writeUTF("GET_ALL_PRODUCTS");
            outClient.flush();

            // Đọc số lượng sản phẩm
            int numberOfProducts = inClient.readInt();

            // Đọc thông tin từng sản phẩm
            for (int i = 0; i < numberOfProducts; i++) {
                int productId = inClient.readInt();
                String name = inClient.readUTF();
                String description = inClient.readUTF();
                double price = inClient.readDouble();
                int stock = inClient.readInt();
                productList.add(new Product(productId, name, description, price, stock));
            }

            outClient.close();
            inClient.close();
            clientSK.close();
        } catch (Exception e) {
            System.err.println("Error during GET_ALL_PRODUCTS: " + e.getMessage());
        }
        return productList;
    }
}
