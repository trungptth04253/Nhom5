package Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerManagement extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private Connection connection;
    private JTextField txtHoTen, txtSDT, txtEmail;

    public CustomerManagement() {
        super("Quản lý Khách Hàng");
        initializeUI();
        connectDB();
        loadData();
        setSize(900, 600); // Giảm kích thước do bỏ 1 cột
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
        try {
            String dbPath = "hotel_booking.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            
            // Cập nhật cấu trúc bảng (bỏ cột CCCD nếu có)
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS KhachHang ("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "HoTen TEXT NOT NULL, "
                        + "SDT TEXT NOT NULL, "
                        + "Email TEXT)");
            }
        } catch (SQLException e) {
            showError("Lỗi kết nối database: " + e.getMessage());
        }
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Bảng danh sách khách hàng (đã bỏ cột CCCD)
        model = new DefaultTableModel(new Object[]{"ID", "Họ tên", "SĐT", "Email"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton btnAdd = new JButton("Thêm KH");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnBack = new JButton("Quay lại");

        // Style buttons
        styleButton(btnAdd, new Color(0, 128, 0)); // Xanh lá
        styleButton(btnEdit, new Color(0, 0, 255)); // Xanh dương
        styleButton(btnDelete, new Color(255, 0, 0)); // Đỏ
        styleButton(btnBack, new Color(128, 128, 128)); // Xám

        btnAdd.addActionListener(e -> showEditDialog(null));
        btnEdit.addActionListener(e -> editSelected());
        btnDelete.addActionListener(e -> deleteSelected());
        btnBack.addActionListener(e -> {
            this.dispose();
            new Admin().setVisible(true);
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 35));
    }

    private void loadData() {
        model.setRowCount(0);
        String query = "SELECT ID, HoTen, SDT, Email FROM KhachHang";

        try (Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("HoTen"),
                    rs.getString("SDT"),
                    rs.getString("Email")
                });
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void showEditDialog(Integer id) {
        JDialog dialog = new JDialog(this, id == null ? "Thêm khách hàng" : "Sửa thông tin KH", true);
        JPanel panel = new JPanel(new GridLayout(4, 2, 15, 15)); // Giảm 1 dòng do bỏ CCCD
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtHoTen = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();

        if (id != null) {
            loadExistingData(id);
        }

        panel.add(new JLabel("Họ tên*:"));
        panel.add(txtHoTen);
        panel.add(new JLabel("SĐT*:"));
        panel.add(txtSDT);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        JButton btnSave = new JButton("Lưu thông tin");
        btnSave.addActionListener(e -> {
            if (validateInput()) {
                saveCustomer(id);
                dialog.dispose();
                loadData();
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void loadExistingData(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM KhachHang WHERE ID = ?")) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtHoTen.setText(rs.getString("HoTen"));
                txtSDT.setText(rs.getString("SDT"));
                txtEmail.setText(rs.getString("Email"));
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu KH: " + e.getMessage());
        }
    }

    private boolean validateInput() {
        if (txtHoTen.getText().trim().isEmpty() || txtSDT.getText().trim().isEmpty()) {
            showError("Vui lòng điền đầy đủ các trường bắt buộc (*)");
            return false;
        }

        // Validate SĐT
        if (!txtSDT.getText().matches("(0|\\+84)\\d{9,10}")) {
            showError("SĐT không hợp lệ (VD: 0912345678 hoặc +84912345678)");
            return false;
        }

        // Validate email nếu có
        if (!txtEmail.getText().trim().isEmpty() && 
            !txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showError("Email không hợp lệ");
            return false;
        }

        return true;
    }

    private void saveCustomer(Integer id) {
        String sql = id == null
                ? "INSERT INTO KhachHang (HoTen, SDT, Email) VALUES (?, ?, ?)"
                : "UPDATE KhachHang SET HoTen = ?, SDT = ?, Email = ? WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, txtHoTen.getText());
            pstmt.setString(2, txtSDT.getText());
            pstmt.setString(3, txtEmail.getText().trim().isEmpty() ? null : txtEmail.getText());

            if (id != null) {
                pstmt.setInt(4, id);
            }

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Lưu thành công!");
        } catch (SQLException e) {
            showError("Lỗi lưu dữ liệu: " + e.getMessage());
        }
    }

    private void editSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = (int) model.getValueAt(row, 0);
            showEditDialog(id);
        } else {
            showError("Vui lòng chọn một khách hàng để chỉnh sửa");
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa khách hàng này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) model.getValueAt(row, 0);

                try (PreparedStatement pstmt = connection.prepareStatement(
                        "DELETE FROM KhachHang WHERE ID = ?")) {

                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } catch (SQLException e) {
                    showError("Lỗi xóa dữ liệu: " + e.getMessage());
                }
            }
        } else {
            showError("Vui lòng chọn một khách hàng để xóa");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerManagement());
    }
}