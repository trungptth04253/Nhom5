/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Management;

import DAOS.UuDaiDAO;
import javax.swing.JOptionPane;
import Form.LoginSign;
import Models.UuDai;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class DiscountManagement extends javax.swing.JFrame {

    int idSelected = 0;
    ArrayList<UuDai> uudais;

    
    public DiscountManagement() {
         uudais = new ArrayList<>();
        initComponents();
    }

     public void LoadTable(ArrayList<UuDai> list) {
        DefaultTableModel df = new DefaultTableModel();
        df.addColumn("ID Ưu đãi");
        df.addColumn("Tên khuyến mãi");
        df.addColumn("Loại khuyến mãi");
        df.addColumn("Giá ưu đãi");
        df.addColumn("Mô tả");

        while (df.getRowCount() > 0) {
            df.removeRow(0); // Clear dữ liệu cũ
        }

        for (UuDai ud : list) {
            String[] rowData = new String[]{
                String.valueOf(ud.getID()),
                ud.getTen(),
                ud.getLoai(),
                String.valueOf(ud.getGiaPhong()),
                ud.getMota()
            };
            df.addRow(rowData);
        }

        tbl_Data.setModel(df); // Gán bảng hiển thị
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbo_loaiKhuyenMai = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btn_Show = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_Mota = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_Quaylai = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_tenKhuyenMai = new javax.swing.JTextField();
        txt_GiaPhong = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Data = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_IdUuDai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbo_loaiKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giám giá theo phần trăm", "Giảm giá cố định", "Mua 1 tặng 2", "Giảm giá theo dịp lễ" }));

        jLabel4.setText("Loại khuyến mãi");

        btn_Show.setText("Show");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });

        jLabel6.setText("Tên khuyến mãi");

        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        jLabel7.setText("Mô tả");

        txt_Mota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MotaActionPerformed(evt);
            }
        });

        jButton1.setText("No file choosen");

        btn_Quaylai.setText("Quay lại");
        btn_Quaylai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuaylaiActionPerformed(evt);
            }
        });

        jLabel5.setText("Hình ảnh");

        jLabel8.setText("Giá phòng");

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("QUẢN LÝ ƯU ĐÃI");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Thông tin khuyến mãi");

        tbl_Data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Data);

        jLabel9.setText("ID Ưu đãi");

        txt_IdUuDai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdUuDaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel9)
                                                .addComponent(txt_IdUuDai)
                                                .addComponent(jLabel4)
                                                .addComponent(cbo_loaiKhuyenMai, 0, 180, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(txt_tenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_GiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btn_Quaylai)
                                            .addGap(173, 173, 173)
                                            .addComponent(jLabel2)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(txt_Mota))
                                    .addGap(56, 56, 56))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_Them, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .addComponent(btn_Xoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Sua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(49, 49, 49))
                        .addComponent(jButton1)
                        .addComponent(jLabel7)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(36, 36, 36)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(458, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(btn_Quaylai))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_tenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Them))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_GiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(btn_Xoa)
                            .addGap(37, 37, 37)
                            .addComponent(btn_Show)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_IdUuDai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_Sua)
                                .addComponent(cbo_loaiKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(txt_Mota, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1)
                            .addGap(11, 11, 11)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed
        uudais = UuDaiDAO.getAllUD();
        if (uudais != null) {
            LoadTable(uudais);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi tải dữ liệu");
        }
    }//GEN-LAST:event_btn_ShowActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // Lấy ID đã chọn
        int id = idSelected;
        if (id == 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn ưu đãi để sửa");
            return;
        }

        // Lấy các giá trị từ form
        String ten = txt_tenKhuyenMai.getText();
        String loai = cbo_loaiKhuyenMai.getSelectedItem().toString();
        int giaUuDai;

        try {
            giaUuDai = Integer.parseInt(txt_GiaPhong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Giá ưu đãi phải là số");
            return;
        }

        String moTa = txt_Mota.getText();

        // Gọi phương thức cập nhật
        boolean checkSua = UuDaiDAO.suaUD(ten, loai, giaUuDai, moTa);

        if (checkSua) {
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
            // Cập nhật lại danh sách khuyến mãi và hiển thị trên bảng
            uudais = UuDaiDAO.getAllUD();
            LoadTable(uudais);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa thất bại");
        }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        int id = idSelected;
        boolean checkXoa = UuDaiDAO.xoaUD(id);

        if (checkXoa) {
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            uudais = UuDaiDAO.getAllUD(); // load lại danh sách sau khi xóa
            LoadTable(uudais);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");
        }
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void txt_MotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MotaActionPerformed

    private void btn_QuaylaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuaylaiActionPerformed
        this.dispose(); // Đóng cửa sổ hiện tại
        new LoginSign(); // Mở lại giao diện đăng nhập
    }//GEN-LAST:event_btn_QuaylaiActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // Không cần ID vì đã tự động tăng trong CSDL
        String ten = txt_tenKhuyenMai.getText();
        String loai = cbo_loaiKhuyenMai.getSelectedItem().toString();
        int giaUuDai;

        try {
            giaUuDai = Integer.parseInt(txt_GiaPhong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Giá ưu đãi phải là số");
            return;
        }

        String moTa = txt_Mota.getText();

        // Gọi DAO để thêm mới
        boolean checkAdd = UuDaiDAO.addUD(ten, loai, giaUuDai, moTa);

        if (checkAdd) {
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
            uudais = UuDaiDAO.getAllUD();
            LoadTable(uudais);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void tbl_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DataMouseClicked
        int row = tbl_Data.getSelectedRow();
        if (row != -1) {
            txt_IdUuDai.setText(uudais.get(row).getID() + "");
            txt_tenKhuyenMai.setText(uudais.get(row).getTen());
            cbo_loaiKhuyenMai.setSelectedItem(uudais.get(row).getLoai());
            txt_GiaPhong.setText(uudais.get(row).getGiaPhong() + "");
            txt_Mota.setText(uudais.get(row).getMota());

            // Lưu ID dòng đã chọn nếu cần xử lý tiếp theo
            idSelected = uudais.get(row).getID();
        }
    }//GEN-LAST:event_tbl_DataMouseClicked

    private void txt_IdUuDaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdUuDaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdUuDaiActionPerformed

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
            java.util.logging.Logger.getLogger(DiscountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiscountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiscountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiscountManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiscountManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Quaylai;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbo_loaiKhuyenMai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_Data;
    private javax.swing.JTextField txt_GiaPhong;
    private javax.swing.JTextField txt_IdUuDai;
    private javax.swing.JTextField txt_Mota;
    private javax.swing.JTextField txt_tenKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
