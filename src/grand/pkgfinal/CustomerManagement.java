/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package grand.pkgfinal;

import DAOS.KhachHangDAO;
import Form.LoginSign;
import Models.KhachHang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CustomerManagement extends javax.swing.JFrame {

    int idSelected = 0;
    ArrayList<KhachHang> khachhangs;

    public CustomerManagement() {
        khachhangs = new ArrayList<>();
        initComponents();
        
    }

    public void Loadtable(ArrayList<KhachHang> list) {
        DefaultTableModel df = new DefaultTableModel();
        df.addColumn("IDKhachHang");
        df.addColumn("TenKhachHang");
        df.addColumn("Gioi tinh");
        df.addColumn("Tuoi");
        df.addColumn("CCCD");
        df.addColumn("SĐT");
        df.addColumn("Email");
        df.addColumn("MatKhau");
        while (df.getRowCount() > 0) {
            df.removeRow(0); // clear
        }
        int stt = 1;
        for (int i = 0; i < list.size(); i++) { // Them tung doi tuong vao table
            KhachHang kh = list.get(i);
            String[] rowData = new String[]{kh.getID() + "", kh.getTen(), kh.getNgaySinh() + "", kh.getGioiTinh(), kh.getTuoi() + "", kh.getCCCD() + "", kh.getSĐT() + "", kh.getEmail(), kh.getMatKhau()};
            //stt++ de sau moi lan lap stt tu dong tang 1
            df.addRow(rowData);
        }
        tbl_Data.setModel(df);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_IDKhachHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_CanCuocCongDan = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        txt_TenKhachHang = new javax.swing.JTextField();
        txt_Tuoi = new javax.swing.JTextField();
        txt_SoDienThoai = new javax.swing.JTextField();
        txt_MatKhau = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Data = new javax.swing.JTable();
        cbo_Gioitinh = new javax.swing.JComboBox<>();
        btn_Show = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_NgaySinh = new javax.swing.JTextField();
        btn_Quaylai = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin khách hàng");

        jLabel3.setText("ID Khách hàng");

        txt_IDKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDKhachHangActionPerformed(evt);
            }
        });

        jLabel4.setText("Giới tính ");

        jLabel5.setText("Căn cước công dân");

        jLabel6.setText("Tên khách hàng");

        jLabel7.setText("Email");

        jLabel8.setText("Tuổi");

        jLabel9.setText("Số điện thoại");

        jLabel10.setText("Mật khẩu");

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

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

        cbo_Gioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btn_Show.setText("Show");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });

        jLabel11.setText("Ngày Sinh");

        btn_Quaylai.setText("Quay lại");
        btn_Quaylai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuaylaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel11)
                                .addGap(36, 36, 36)
                                .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_CanCuocCongDan)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_IDKhachHang)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Email)
                                    .addComponent(jLabel5)
                                    .addComponent(cbo_Gioitinh, 0, 180, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_Tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Quaylai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Them, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(btn_Xoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_Quaylai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_IDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Sua)
                            .addComponent(cbo_Gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_CanCuocCongDan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addGap(11, 11, 11)
                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Them))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Xoa))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Show))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_IDKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IDKhachHangActionPerformed

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed
        khachhangs = KhachHangDAO.getAllKH();
        if (khachhangs != null) {
            Loadtable(khachhangs);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi tải dữ liệu");
        }
    }//GEN-LAST:event_btn_ShowActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // Không cần ID vì đó là trường tự động tăng
        String Ten = txt_TenKhachHang.getText();
        Date NgaySinh = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            NgaySinh = sdf.parse(txt_NgaySinh.getText());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ");
            return; // Thoát nếu ngày sinh không hợp lệ
        }

        String GioiTinh = cbo_Gioitinh.getSelectedItem().toString();
        int Tuoi, CCCD, SDT;

        try {
            Tuoi = Integer.parseInt(txt_Tuoi.getText());
            CCCD = Integer.parseInt(txt_CanCuocCongDan.getText());
            SDT = Integer.parseInt(txt_SoDienThoai.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi: Tuổi, CCCD và SĐT phải là số");
            return;
        }

        String Email = txt_Email.getText();
        String MatKhau = txt_MatKhau.getText();

        boolean checkadd = KhachHangDAO.addKH(Ten, NgaySinh, GioiTinh, Tuoi, CCCD, SDT, Email, MatKhau);
        if (checkadd) {
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
            khachhangs = KhachHangDAO.getAllKH();
            Loadtable(khachhangs);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void tbl_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DataMouseClicked
       int row = tbl_Data.getSelectedRow();
    if (row != -1) {
        // Hiển thị dữ liệu vào các trường nhập liệu
        txt_IDKhachHang.setText(khachhangs.get(row).getID() + "");
        txt_TenKhachHang.setText(khachhangs.get(row).getTen());
        
        // Định dạng ngày sinh để hiển thị
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (khachhangs.get(row).getNgaySinh() != null) {
            txt_NgaySinh.setText(sdf.format(khachhangs.get(row).getNgaySinh()));
        } else {
            txt_NgaySinh.setText("");
        }
        
        cbo_Gioitinh.setSelectedItem(khachhangs.get(row).getGioiTinh());
        txt_Tuoi.setText(khachhangs.get(row).getTuoi() + "");
        txt_CanCuocCongDan.setText(khachhangs.get(row).getCCCD() + "");
        txt_SoDienThoai.setText(khachhangs.get(row).getSĐT() + "");
        txt_Email.setText(khachhangs.get(row).getEmail());
        txt_MatKhau.setText(khachhangs.get(row).getMatKhau());
        
        // Lưu ID của dòng đã chọn
        idSelected = khachhangs.get(row).getID();
    }
    }//GEN-LAST:event_tbl_DataMouseClicked

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // Lấy ID đã chọn
    int id = idSelected;
    if (id == 0) {
        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn khách hàng để sửa");
        return;
    }
    
    // Lấy các giá trị từ form
    String ten = txt_TenKhachHang.getText();
    Date ngaySinh = null;
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ngaySinh = sdf.parse(txt_NgaySinh.getText());
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(rootPane, "Ngày sinh không hợp lệ. Vui lòng nhập định dạng yyyy-MM-dd");
        return;
    }
    
    String gioiTinh = cbo_Gioitinh.getSelectedItem().toString();
    int tuoi, cccd, sdt;
    
    try {
        tuoi = Integer.parseInt(txt_Tuoi.getText());
        cccd = Integer.parseInt(txt_CanCuocCongDan.getText());
        sdt = Integer.parseInt(txt_SoDienThoai.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(rootPane, "Lỗi: Tuổi, CCCD và SĐT phải là số");
        return;
    }
    
    String email = txt_Email.getText();
    String matKhau = txt_MatKhau.getText();
    
    // Gọi phương thức cập nhật
    boolean checkSua = KhachHangDAO.suaKH(id, ten, ngaySinh, gioiTinh, tuoi, cccd, sdt, email, matKhau);
    
    if (checkSua) {
        JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        // Cập nhật lại danh sách khách hàng và hiển thị trên bảng
        khachhangs = KhachHangDAO.getAllKH();
        Loadtable(khachhangs);
    } else {
        JOptionPane.showMessageDialog(rootPane, "Sửa thất bại");
    }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        String NgaySinh = txt_NgaySinh.getText();
        int id = idSelected;
        boolean checkXoa = KhachHangDAO.xoaSP(id);
        if (checkXoa) {
            JOptionPane.showMessageDialog(rootPane, "Xoa thanh cong");
            khachhangs = KhachHangDAO.getAllKH();
            Loadtable(khachhangs);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Xoa that bai");
        }
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_QuaylaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuaylaiActionPerformed
        this.dispose(); // Đóng cửa sổ hiện tại
        new LoginSign(); // Mở lại giao diện đăng nhập
    }//GEN-LAST:event_btn_QuaylaiActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Quaylai;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbo_Gioitinh;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_Data;
    private javax.swing.JTextField txt_CanCuocCongDan;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_IDKhachHang;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_TenKhachHang;
    private javax.swing.JTextField txt_Tuoi;
    // End of variables declaration//GEN-END:variables

}
