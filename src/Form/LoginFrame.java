/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {
 String connectionUrl = "jdbc:sqlserver://LAPTOP-4K0UOUJE\\MSSQLSERVER02;databaseName=QuanLyDatPhongKhachSan;user=sa;password=123;trustServerCertificate=true";

    public LoginFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUserNamee = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboCheckBot = new javax.swing.JCheckBox();
        txtSignUp = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Đăng Nhập");

        jLabel2.setText("Tên đăng nhập");

        jLabel3.setText("Mật khẩu");

        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel5.setText("Quên mật khẩu?");

        jLabel6.setText("Chưa có tài khoản?");

        cboCheckBot.setText("Xác nhận bạn là con người");

        txtSignUp.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtSignUp.setForeground(new java.awt.Color(204, 0, 0));
        txtSignUp.setText("Đăng ký");
        txtSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSignUpMouseClicked(evt);
            }
        });
        txtSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboCheckBot)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtUserNamee)
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSignUp))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUserNamee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addComponent(cboCheckBot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogin)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSignUp)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUserNamee.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để username trống");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để password trống");
            return;
        }
        if (!cboCheckBot.isSelected()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa check xem bạn có phải robot hay không");
            return;
        }

        try (Connection con = DriverManager.getConnection(connectionUrl); PreparedStatement stmt = con.prepareStatement("SELECT password, status, phonenumber FROM account WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    if (username.equals("admin")) {
                        String dbPassword = rs.getString("password");
                        if (password.equals(dbPassword)) {
                            System.out.println("admin");
                            JOptionPane.showMessageDialog(this, "Chào mừng admin!", "Đăng nhập thành công", JOptionPane.INFORMATION_MESSAGE);
                            AdminFrame af = new AdminFrame();
                            af.setVisible(true);
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Sai mật khẩu của admin", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        String dbPassword = rs.getString("password");
                        int status = rs.getInt("status");
                        String dbPhoneNumber = rs.getString("phonenumber");

                        if (password.equals(dbPassword)) {
                            if (status == -1) {
                                int confirm = JOptionPane.showConfirmDialog(
                                        this,
                                        "Tài khoản đã bị khóa. Bạn có muốn mở khóa tài khoản không?",
                                        "Xác nhận mở khóa",
                                        JOptionPane.YES_NO_OPTION);

                                if (confirm == JOptionPane.YES_OPTION) {
                                    String inputPhoneNumber = JOptionPane.showInputDialog(this, "Vui lòng nhập số điện thoại của bạn để xác minh:");

                                    if (inputPhoneNumber != null && inputPhoneNumber.equals(dbPhoneNumber)) {
                                        try (PreparedStatement updateStmt = con.prepareStatement("UPDATE account SET status = 1 WHERE username = ?")) {
                                            updateStmt.setString(1, username);
                                            int rowsUpdated = updateStmt.executeUpdate();

                                            if (rowsUpdated > 0) {
                                                JOptionPane.showMessageDialog(this, "Xác minh thành công. Tài khoản đã được mở khóa và bạn đã đăng nhập.");
                                                
                                                
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi mở khóa tài khoản.");
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Xác minh thất bại. Vui lòng kiểm tra lại thông tin.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Bạn đã hủy yêu cầu mở khóa.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                              
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Sai mật khẩu");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sai tài khoản");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSignUpMouseClicked
        RegisterFrame rg = new RegisterFrame();
        rg.setVisible(true);
    }//GEN-LAST:event_txtSignUpMouseClicked

    private void txtSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSignUpActionPerformed

    }//GEN-LAST:event_txtSignUpActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox cboCheckBot;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JButton txtSignUp;
    private javax.swing.JTextField txtUserNamee;
    // End of variables declaration//GEN-END:variables
}
