/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Management;
import DAOS.KhachHangDAO;
import DAOS.NhanVienDAO;
import Models.NhanVien;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EmployeeManagementtt extends javax.swing.JFrame {
    int idSelected = 0;
    ArrayList<NhanVien> nhanviens;

    public EmployeeManagementtt() {
        nhanviens = new ArrayList<>();
        initComponents();
    }
     public void Loadtable(ArrayList<NhanVien> list) {
        DefaultTableModel df = new DefaultTableModel();
        df.addColumn("IDNhanVien");
        df.addColumn("TenNhanVien");
        df.addColumn("CaLam");
        df.addColumn("SĐT");
        df.addColumn("Email");
        df.addColumn("ChucVu");
        while (df.getRowCount() > 0) {
            df.removeRow(0); // clear
        }
        int stt = 1;
        for (int i = 0; i < list.size(); i++) { // Them tung doi tuong vao table
            NhanVien nv = list.get(i);
            String[] rowData = new String[]{nv.getID() + "", nv.getTen(), nv.getCaLam(), nv.getSDT()+ "", nv.getEmail(), nv.getChucVu()};
            //stt++ de sau moi lan lap stt tu dong tang 1
            df.addRow(rowData);
        }
        tbl_Data.setModel(df);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_IDNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_TenNhanVien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_CaLam = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbo_ChucVu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Data = new javax.swing.JTable();
        btn_Xoa = new javax.swing.JButton();
        btn_BoSung = new javax.swing.JButton();
        btn_QuayLai = new javax.swing.JButton();
        btn_SuaThongTin = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_EmailNV = new javax.swing.JTextField();
        btn_Show = new javax.swing.JButton();

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin nhân viên");

        jLabel3.setText("ID Nhân viên");

        jLabel4.setText("Tên nhân viên");

        jLabel5.setText("Ca làm");

        jLabel6.setText("Số điện thoại");

        jLabel7.setText("Chức vụ");

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
        jScrollPane1.setViewportView(tbl_Data);

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_BoSung.setText("Bổ sung");
        btn_BoSung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BoSungActionPerformed(evt);
            }
        });

        btn_QuayLai.setText("Quay lại");
        btn_QuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuayLaiActionPerformed(evt);
            }
        });

        btn_SuaThongTin.setText("Sửa");
        btn_SuaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaThongTinActionPerformed(evt);
            }
        });

        jLabel8.setText("Email");

        btn_Show.setText("Show");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_IDNhanVien)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_CaLam)
                                    .addComponent(jLabel7)
                                    .addComponent(cbo_ChucVu, 0, 160, Short.MAX_VALUE))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_EmailNV, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_Xoa)
                                            .addComponent(btn_SuaThongTin)
                                            .addComponent(btn_Show)
                                            .addComponent(btn_BoSung)))))
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_QuayLai)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_QuayLai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_IDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_BoSung))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(btn_Xoa))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btn_SuaThongTin))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txt_CaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_EmailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Show))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BoSungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BoSungActionPerformed
     // Không cần ID vì đó là trường tự động tăng
        String Ten = txt_TenNhanVien.getText();
        

        String ChucVu = cbo_ChucVu.getSelectedItem().toString();
        int SDT, ID;
        try {
            SDT = Integer.parseInt(txt_SoDienThoai.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi: ID, SĐT phải là số");
            return;
        }

        String Email = txt_EmailNV.getText();
        String CaLam = txt_CaLam.getText();

        boolean checkadd = NhanVienDAO.addNV(Ten, CaLam, SDT, Email, ChucVu);
        if (checkadd) {
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
            nhanviens = NhanVienDAO.getAllNV();
            Loadtable(nhanviens);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
        }
    }//GEN-LAST:event_btn_BoSungActionPerformed

    private void btn_QuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuayLaiActionPerformed
          this.dispose(); // Close current window
        new Admin().setVisible(true); // Open Admin window
    }//GEN-LAST:event_btn_QuayLaiActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        String NgaySinh = txt_TenNhanVien.getText();
        int id = idSelected;
        boolean checkXoa = NhanVienDAO.xoaNV(id);
        if (checkXoa) {
            JOptionPane.showMessageDialog(rootPane, "Xoa thanh cong");
            nhanviens = NhanVienDAO.getAllNV();
            Loadtable(nhanviens);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Xoa that bai");
        }
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaThongTinActionPerformed
        int id = idSelected;
    if (id == 0) {
        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn nhân viên để sửa");
        return;
    }
    }//GEN-LAST:event_btn_SuaThongTinActionPerformed

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed
          nhanviens = NhanVienDAO.getAllNV();
        if (nhanviens != null) {
            Loadtable(nhanviens);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi tải dữ liệu");
        }
    }//GEN-LAST:event_btn_ShowActionPerformed

    private void tbl_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DataMouseClicked
        int row = tbl_Data.getSelectedRow();
    if (row != -1) {
        // Hiển thị dữ liệu vào các trường nhập liệu
        txt_IDNhanVien.setText(nhanviens.get(row).getID() + "");
        txt_TenNhanVien.setText(nhanviens.get(row).getTen());
        txt_CaLam.setText(nhanviens.get(row).getCaLam());
        txt_SoDienThoai.setText(nhanviens.get(row).getSDT()+ "");
        txt_EmailNV.setText(nhanviens.get(row).getEmail());
        cbo_ChucVu.setSelectedItem(nhanviens.get(row).getChucVu());
        // Lưu ID của dòng đã chọn
        idSelected = nhanviens.get(row).getID();
    }
    }//GEN-LAST:event_tbl_DataMouseClicked

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
            java.util.logging.Logger.getLogger(EmployeeManagementtt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementtt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementtt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementtt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagementtt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_BoSung;
    private javax.swing.JButton btn_QuayLai;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_SuaThongTin;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbo_ChucVu;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Data;
    private javax.swing.JTextField txt_CaLam;
    private javax.swing.JTextField txt_EmailNV;
    private javax.swing.JTextField txt_IDNhanVien;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_TenNhanVien;
    // End of variables declaration//GEN-END:variables
}
