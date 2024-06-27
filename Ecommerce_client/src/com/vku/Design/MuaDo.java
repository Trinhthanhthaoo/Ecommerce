/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.Design;

import com.google.gson.Gson;
import com.vku.Model.OrderDetail;
import com.vku.Model.Product;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin!
 */
public class MuaDo extends javax.swing.JPanel {
    private javax.swing.JSpinner quantitySpinner; // Để nhập số lượng sản phẩm
    private DefaultTableModel model;
    
    private ArrayList<OrderDetail> orderDetails ;
    private  int quantity[];

    /**
     * Creates new form MuaSach
     */
    public MuaDo() {
        initComponents();
        orderDetails = new ArrayList<>();
        quantity = new int[1000000];
        showData();
      // Khởi tạo JTextArea cho chi tiết đơn hàng và thêm vào jScrollPane2
        textAreaDetails = new JTextArea();
        jScrollPane2.setViewportView(textAreaDetails);

        // Thêm sự kiện MouseClicked cho showData
        showData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showDataMouseClicked(evt);
            }
        });

        // Khởi tạo model cho bảng showData
        model = (DefaultTableModel) showData.getModel();
    }
  private void showDataMouseClicked(java.awt.event.MouseEvent evt) {                                       
        int selectedRow = showData.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy thông tin chi tiết sản phẩm từ hàng được chọn
            int id = (int) model.getValueAt(selectedRow, 0);
            String name = (String) model.getValueAt(selectedRow, 1); // Name
            String description = (String) model.getValueAt(selectedRow, 2); // Description
            double price = (double) model.getValueAt(selectedRow, 3); // Price
            int stock = (int) model.getValueAt(selectedRow, 4); // Stock
            
            quantity[id] ++;
            OrderDetail orderDetail = new OrderDetail(0, 0, id, 0, price);
            orderDetails.add(orderDetail);

            // Hiển thị thông tin chi tiết lên textAreaDetails
            showProductDetails(name, description, price, stock);
        }
    }
private void showProductDetails(String name, String description, double price, int stock) {
        // Hiển thị thông tin chi tiết của sản phẩm lên textAreaDetails
        String productDetails = "Tên sản phẩm: " + name + "\n"
                              + "Mô tả: " + description + "\n"
                              + "Giá: " + price + "\n"
                              + "Số lượng trong kho: " + stock + "\n\n";
        

        // Thêm thông tin mới vào textarea, giữ lại thông tin cũ
        textAreaDetails.append(productDetails);
    }

 
    // Phương thức giả định lấy chi tiết đơn hàng từ CSDL hoặc API
    private String getOrderDetailsFromDatabase(int orderId) {
        // Code để lấy chi tiết đơn hàng từ CSDL hoặc API
        // Trong ví dụ này, tôi giả định đơn giản là trả về một chuỗi
        return "Chi tiết đơn hàng có OrderID = " + orderId + "\nThông tin chi tiết khác...";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        showData = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDetails = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );

        showData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Description", "Price", "Stock"
            }
        ));
        jScrollPane1.setViewportView(showData);

        textAreaDetails.setColumns(20);
        textAreaDetails.setRows(5);
        jScrollPane2.setViewportView(textAreaDetails);

        jButton1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton1.setText("Đặt hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(196, 831, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      OrderUI i = new OrderUI();
      i.show();
      
//      int orderId = addOrder(order);
//
//        for(OrderDetail x :  orderDetails) {
//            x.setQuantity(quantity[x.getProductId()]);
//            x.setOrderId(orderId);
//        }
//      
      
      
      
      quantity = new int[1000000];
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable showData;
    private javax.swing.JTextArea textAreaDetails;
    // End of variables declaration//GEN-END:variables
  private void showData() {
        Socket clientSocket = null;
        DataOutputStream outClient = null;
        
        Gson gson = new Gson();
        
        try {
            // Establish connection to server
            clientSocket = new Socket("localhost", 8300);
            
            // Send data to server
            outClient = new DataOutputStream(clientSocket.getOutputStream());
            outClient.writeUTF("GET_ALL_PRODUCTS");
            outClient.flush();
        
            // Read server response
            DataInputStream inClient = new DataInputStream(clientSocket.getInputStream());

            boolean done = false;

            while (!done) {
                byte messageType = inClient.readByte();

                switch (messageType) {
                    case 1:
                        String strProducts = inClient.readUTF();
                        
                        Product[] products = gson.fromJson(strProducts, Product[].class);
                        List<Product> list = new ArrayList<>(Arrays.asList(products));
                        DefaultTableModel model = (DefaultTableModel) (showData.getModel());
                        model.setRowCount(0);
                        for (Product product : list) {
                            model.addRow(new Object[]{
                                product.getProductId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStock(),
                            });
                        }
                        
                        break;
                    default:
                        done = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}