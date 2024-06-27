/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.Design;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

/**
 *
 * @author admin!
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 153));
        jLabel1.setText("UserName");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 153));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 480, 40));
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 480, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOGIN");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 40, 840, -1));

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jCheckBox1.setText("Hiển thị mật khẩu");
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 170, 20));

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 220, 50));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Register");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 170, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/moving out sale.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Socket clientSK = null;
    DataOutputStream outClient = null;
    try {
        // Thiết lập kết nối tới máy chủ
        clientSK = new Socket(InetAddress.getByName("localhost"), 8300);

        // Đọc giá trị từ các ô nhập liệu
        String accountID = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String key = "LOGIN";

        // Kiểm tra giá trị đầu vào
        if (accountID.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
       String hashedPassword = passwordHash(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Gửi dữ liệu qua socket
        outClient = new DataOutputStream(clientSK.getOutputStream());

        outClient.writeUTF(key); // Gửi thông điệp là chuỗi "LOGIN"
        outClient.flush();
        outClient.writeUTF(accountID);  // Gửi ID tài khoản
        outClient.flush();
        outClient.writeUTF(hashedPassword);  // Gửi mật khẩu
        outClient.flush();

        // Nhận phản hồi từ máy chủ
        DataInputStream inClient = new DataInputStream(clientSK.getInputStream());

        boolean auth = inClient.readBoolean();
        if (auth) {
            // Yêu cầu máy chủ gửi lại vai trò của tài khoản
            String role = inClient.readUTF();

            if (role.equals("admin")) {
                Loading1 tc = new Loading1();
                tc.show();
                this.hide();
            } else if (role.equals("user")) {
               KhachHang kh = new KhachHang();
               kh.show();
               this.hide();
            } else {
                KhachHang kh = new KhachHang();
               kh.show();
               this.hide();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Đăng nhập thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Đảm bảo đóng các kết nối
        try {
            if (outClient != null) outClient.close();
            if (clientSK != null) clientSK.close();
        } catch (Exception ex) {
            System.err.println("Error closing connection: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       Register r = new Register();
       r.show();
       this.hide();
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
   public static String passwordHash(String password){
       try {
           MessageDigest md = MessageDigest.getInstance("SHA-256");
           md.update(password.getBytes());
           byte[] rbt = md.digest();
           StringBuilder sb= new StringBuilder();
           for(byte b: rbt){
               sb.append(String.format("%02x", b));
           }
           return sb.toString();
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

}
