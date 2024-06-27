/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.Design;

import com.google.gson.Gson;
import com.vku.Model.Product;
import java.awt.Desktop;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QLSP extends javax.swing.JPanel {

    public QLSP() {
        initComponents();
        showData();
        search2();
        // Add Document Listener to txtSearch field
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search2(); // Call search method on text insert
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search2(); // Call search method on text remove
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used for plain text components
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        showData = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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
                {null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Name", "Description", "Price", "Stock"
            }
        ));
        showData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(showData);

        jPanel2.setBackground(new java.awt.Color(51, 97, 182));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("QUẢN LÝ SẢN PHẨM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setText("Product ID");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("Description");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setText("Stock");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setText("Price");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/Delete-icon.png"))); // NOI18N
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/edit-icon_1.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/Add-icon.png"))); // NOI18N
        jButton4.setText("Insert");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Socket clientSocket = null;
        DataOutputStream outClient = null;
        DataInputStream inClient = null;

        try {
            // Establish connection to server
            clientSocket = new Socket("localhost", 8300);

            // Send request to build Excel file
            outClient = new DataOutputStream(clientSocket.getOutputStream());
            outClient.writeUTF("BUILD_EXCEL");
            outClient.flush();

            // Read server response for file size
//            inClient = new DataInputStream(clientSocket.getInputStream());
//            int fileSize = inClient.readInt();
            // Read server response for product data
            inClient = new DataInputStream(clientSocket.getInputStream());

            // Read server response
            boolean done = false;

            while (!done) {
                byte messageType = inClient.readByte();

                System.out.println("TTTT messageType: " + messageType);

                switch (messageType) {
                    case 1:
                        boolean result = inClient.readBoolean();
                        System.out.println("TTTT result: " + result);

                        if (result) {
//                            JOptionPane.showMessageDialog(this, "Xuất file excel thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            String path = "D:/products.xlsx";
                            File f = new File(path);
                            if (f.exists() && f.isFile()) {
                                try {
                                    Thread.sleep(500);
                                    Desktop.getDesktop().open(new File(path));
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QLSP.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Không có file excel!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
                            done = true;
                        }

                        break;
                    default:
                        done = true;
                }
            }
            // Receive Excel file data if fileSize > 0
//        if (fileSize > 0) {
//            byte[] fileBytes = new byte[fileSize];
//            inClient.readFully(fileBytes, 0, fileSize);
//
//            // Save Excel file to disk
//            String filePath = "D:/products_from_server.xlsx"; // Adjust file path as needed
//            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//                fileOut.write(fileBytes);
//                JOptionPane.showMessageDialog(null, "Excel export successful. File saved to: " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Failed to save Excel file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "No data received from server.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to communicate with server: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (outClient != null) {
                    outClient.close();
                }
                if (inClient != null) {
                    inClient.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void showDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showDataMouseClicked

        int row = showData.getSelectedRow();
        String id = String.valueOf(showData.getValueAt(row, 0));
        String name = String.valueOf(showData.getValueAt(row, 1));
        String des = String.valueOf(showData.getValueAt(row, 2));
        String price = String.valueOf(showData.getValueAt(row, 3));
        String stock = String.valueOf(showData.getValueAt(row, 4));

        txtID.setText(id);
        txtName.setText(name);
        txtDescription.setText(des);
        txtPrice.setText(price);
        txtStock.setText(stock);
    }//GEN-LAST:event_showDataMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Socket clientSocket = null;
        DataOutputStream outClient = null;

        try {
            // Establish connection to server
            clientSocket = new Socket("localhost", 8300);

            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            String price = txtPrice.getText().trim();
            String stock = txtStock.getText().trim();

            // Xác thực đầu vào
            if (stock.isEmpty() || name.isEmpty() || description.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Các trường không được để trống", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Send data to server
            outClient = new DataOutputStream(clientSocket.getOutputStream());
            outClient.writeUTF("ADD_PRODUCT"); // Gửi thông điệp là chuỗi "TRANSFER"
            outClient.flush();
            outClient.writeUTF(name);
            outClient.flush();
            outClient.writeUTF(description);
            outClient.flush(); // Account to transfer to
            outClient.writeDouble(Double.parseDouble(price));
            outClient.flush();
            outClient.writeInt(Integer.parseInt(stock));
            outClient.flush();
            // Read server response
            DataInputStream inClient = new DataInputStream(clientSocket.getInputStream());

            boolean done = false;

            while (!done) {
                byte messageType = inClient.readByte();

                System.out.println("TTTT messageType: " + messageType);

                switch (messageType) {
                    case 1:
                        boolean result = inClient.readBoolean();
                        System.out.println("TTTT result: " + result);

                        if (result) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            showData();
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
                            done = true;
                        }

                        break;
                    default:
                        done = true;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure connections are properly closed
            try {
                if (outClient != null) {
                    outClient.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Socket clientSocket = null;
        DataOutputStream outClient = null;

        try {
            // Establish connection to server
            clientSocket = new Socket("localhost", 8300);

            String productIdStr = txtID.getText().trim();
            // Input validation
            if (productIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID sản phẩm không được để trống", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int productId = Integer.parseInt(productIdStr);

            // Send data to server
            outClient = new DataOutputStream(clientSocket.getOutputStream());
            outClient.writeUTF("DELETE_PRODUCT");
            outClient.flush();
            outClient.writeInt(productId);
            outClient.flush();

            // Read server response
            DataInputStream inClient = new DataInputStream(clientSocket.getInputStream());

            boolean done = false;

            while (!done) {
                byte messageType = inClient.readByte();

                System.out.println("TTTT messageType: " + messageType);

                switch (messageType) {
                    case 1:
                        boolean result = inClient.readBoolean();
                        System.out.println("TTTT result: " + result);

                        if (result) {
                            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            showData();
                        } else {
                            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
                            done = true;
                        }

                        break;
                    default:
                        done = true;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure connections are properly closed
            try {
                if (outClient != null) {
                    outClient.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Socket clientSocket = null;
        DataOutputStream outClient = null;

        try {

            clientSocket = new Socket("localhost", 8300);

            String productIdStr = txtID.getText().trim();
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            String priceStr = txtPrice.getText().trim();
            String stockStr = txtStock.getText().trim();
            if (productIdStr.isEmpty() || name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Các trường không được để trống", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int productId = Integer.parseInt(productIdStr);
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            // Send data to server
            outClient = new DataOutputStream(clientSocket.getOutputStream());
            outClient.writeUTF("UPDATE_PRODUCT");
            outClient.flush();
            outClient.writeInt(productId);
            outClient.flush();
            outClient.writeUTF(name);
            outClient.flush();
            outClient.writeUTF(description);
            outClient.flush();
            outClient.writeDouble(price);
            outClient.flush();
            outClient.writeInt(stock);
            outClient.flush();
            // Read server response
            DataInputStream inClient = new DataInputStream(clientSocket.getInputStream());

            boolean done = false;

            while (!done) {
                byte messageType = inClient.readByte();

                System.out.println("TTTT messageType: " + messageType);
                switch (messageType) {
                    case 1:
                        boolean result = inClient.readBoolean();
                        System.out.println("TTTT result: " + result);

                        if (result) {
                            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            showData();
                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
                            done = true;
                        }

                        break;
                    default:
                        done = true;
                }
            }

        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure connections are properly closed
            try {
                if (outClient != null) {
                    outClient.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable showData;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStock;
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
                    System.out.println("Received products JSON: " + strProducts);

                    Product[] products = gson.fromJson(strProducts, Product[].class);
                    List<Product> list = new ArrayList<>(Arrays.asList(products));
                    DefaultTableModel model = (DefaultTableModel) showData.getModel();
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

 private void search2() {
    String keyword = txtSearch.getText().trim();

    Socket clientSocket = null;
    DataOutputStream outClient = null;
    DataInputStream inClient = null;
    Gson gson = new Gson();

    try {
        // Establish connection to server if not already connected
        if (clientSocket == null || clientSocket.isClosed()) {
            clientSocket = new Socket("localhost", 8300);
            outClient = new DataOutputStream(clientSocket.getOutputStream());
            inClient = new DataInputStream(clientSocket.getInputStream());
        }

        // Send search request to server
        outClient.writeUTF("SEARCH_PRODUCT");
        outClient.flush();
        outClient.writeUTF(keyword);
        outClient.flush();

        // Receive and process server response
        boolean done = false;
        while (!done) {
            byte messageType = inClient.readByte();

            switch (messageType) {
                case 1:
                    // Read the JSON string of products from server
                    String strProducts = inClient.readUTF();
                    // Convert JSON to Product array using Gson
                    Product[] products = gson.fromJson(strProducts, Product[].class);
                    
                    // Update JTable (showData2) with search results
                    DefaultTableModel model = (DefaultTableModel) showData.getModel();
                    model.setRowCount(0); // Clear existing rows
                    for (Product product : products) {
                        model.addRow(new Object[] {
                            product.getProductId(),
                            product.getName(),
                            product.getPrice(),
                            // Add more columns if needed
                        });
                    }
                    done = true; // Exit loop after processing products
                    break;
                case -1:
                    done = true; // Exit loop at end of response
                    break;
                default:
                    done = true; // Exit loop for unknown message types
                    break;
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        // Close streams and socket in finally block
        try {
            if (outClient != null) {
                outClient.close();
            }
            if (inClient != null) {
                inClient.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    private void exportToExcel(List<Product> sps, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Books");

            // Tạo hàng đầu tiên cho tiêu đề
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Product ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Price");
            headerRow.createCell(4).setCellValue("Stock");

            // Đổ dữ liệu từ danh sách vào các hàng
            int rowNum = 1;
            for (Product sp : sps) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(sp.getProductId());
                row.createCell(0).setCellValue(sp.getName());
                row.createCell(1).setCellValue(sp.getDescription());
                row.createCell(2).setCellValue(sp.getPrice());
                row.createCell(3).setCellValue(sp.getStock());
            }

            // Ghi workbook ra file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
