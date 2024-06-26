/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.Design;

import com.vku.Model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author admin!
 */
public class TC_Admin extends javax.swing.JFrame {

    /**
     * Creates new form TC_Server
     */
    public TC_Admin() {
        initComponents();
        dt();
        time();
        lblName.setText("Xin chào, " + User.name);

    }
  public void dt() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dd = sdf.format(d);
        l_date.setText(dd);
    }
    Timer t;
    SimpleDateFormat st;

    public void time() {

        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");
                String tt = st.format(dt);
                l_time.setText(tt);
            }
        });
        t.start();
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
        QLDH = new javax.swing.JLabel();
        Banking = new javax.swing.JLabel();
        TL = new javax.swing.JLabel();
        QLSP = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TL1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bg = new javax.swing.JPanel();
        l_date = new javax.swing.JLabel();
        l_time = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        QLDH.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        QLDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/Ecommerce-Product-icon.png"))); // NOI18N
        QLDH.setText("Quản lý đơn hàng");
        QLDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLDHMouseClicked(evt);
            }
        });

        Banking.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        Banking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/Check-icon.png"))); // NOI18N
        Banking.setText("Kiểm tra tài khoản");
        Banking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BankingMouseClicked(evt);
            }
        });

        TL.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        TL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/System-settings-icon.png"))); // NOI18N
        TL.setText("Thiết lập");
        TL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TLMouseClicked(evt);
            }
        });

        QLSP.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        QLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/manager.png"))); // NOI18N
        QLSP.setText("Quản lý sản phẩm");
        QLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLSPMouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/Exit.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        TL1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        TL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/logout-icon.png"))); // NOI18N
        TL1.setText("Đăng xuất");
        TL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TL1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TL1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QLDH, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Banking, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TL, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(92, 92, 92)
                .addComponent(QLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(QLDH, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Banking, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TL, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(TL1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 830));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/menu.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        bg.setLayout(new java.awt.BorderLayout());
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        l_date.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        l_date.setText("jLabel1");
        getContentPane().add(l_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 300, 30));

        l_time.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        l_time.setText("jLabel2");
        getContentPane().add(l_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 260, -1));

        lblName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblName.setText("Xin Chào");
        getContentPane().add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 140, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/icon/Thiết kế chưa có tên (3).png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1253, 828));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        openMenu();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       closeMenu();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void QLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSPMouseClicked
        taiTrang(1);
    }//GEN-LAST:event_QLSPMouseClicked

    private void QLDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDHMouseClicked
        taiTrang(2);
    }//GEN-LAST:event_QLDHMouseClicked

    private void BankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BankingMouseClicked
        taiTrang(4);
    }//GEN-LAST:event_BankingMouseClicked

    private void TLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TLMouseClicked
        taiTrang(5);
    }//GEN-LAST:event_TLMouseClicked

    private void TL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TL1MouseClicked
       int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {
        Login dn = new Login();  // Tạo đối tượng DangNhap mới
        dn.setVisible(true);           // Hiển thị trang DangNhap
        this.dispose();                // Đóng trang TrangChu hiện tại
    }
    }//GEN-LAST:event_TL1MouseClicked

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
            java.util.logging.Logger.getLogger(TC_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TC_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TC_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TC_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TC_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Banking;
    private javax.swing.JLabel QLDH;
    private javax.swing.JLabel QLSP;
    private javax.swing.JLabel TL;
    private javax.swing.JLabel TL1;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel l_date;
    private javax.swing.JLabel l_time;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
    int width = 260;
    int height = 950;
    ThietLap_A tl;
    QLSP sp;
    QLDH dh;
    TK tk;
    Banking b;
    Edit e;

    private void openMenu() {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < width; i++) {
                    jPanel1.setSize(width, height);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }).start();
    }

    private void closeMenu() {
        new Thread(new Runnable() {
            public void run() {
                for (int i = width; i > 0; i--) {
                    jPanel1.setSize(i, height);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }).start();
    }

    private void taiTrang(int i) {
        bg.removeAll();
        switch (i) {
            case 1:
                if (sp == null) {
                    sp = new QLSP();
                }
                bg.add(sp);

                break;
            case 2:
                if (dh == null) {
                    dh = new QLDH();
                }
                bg.add(dh);

                break;
            case 3:
                if (tk == null) {
                    tk = new TK();
                }
                bg.add(tk);

                break;
            case 4:
                if (b == null) {
                    b = new Banking();
                }
                bg.add(b);

                break;
             case 5:
                if (tl == null) {
                    tl = new ThietLap_A();
                }
                bg.add(tl);

                break;
            default:

                break;
        }
        bg.updateUI();
    }
}
