package Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeManagement extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private Connection connection;
    private JTextField txtHoTen, txtChucVu, txtLuong;
    private JComboBox<String> cbNgay, cbThang, cbNam;

    public EmployeeManagement() {
        super("Quản lý Nhân Viên");
        initializeUI();
        connectDB();
        loadData();
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
        try {
            String dbPath = "hotel_booking.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            showError("Lỗi kết nối database: " + e.getMessage());
        }
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Bảng danh sách nhân viên
        model = new DefaultTableModel(new Object[]{"ID", "Họ tên", "Chức vụ", "Lương", "Ngày vào làm"}, 0) {
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
        JButton btnAdd = new JButton("Thêm NV");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnBack = new JButton("Quay lại");

        // Style buttons
        styleButton(btnAdd, new Color(0, 128, 0));
        styleButton(btnEdit, new Color(0, 0, 255));
        styleButton(btnDelete, new Color(255, 0, 0));
        styleButton(btnBack, new Color(128, 128, 128));

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
        String query = "SELECT ID, HoTen, ChucVu, Luong, NgayVaoLam FROM NhanVien";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            while (rs.next()) {
                Date ngayVaoLam = dateFormat.parse(rs.getString("NgayVaoLam"));
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("HoTen"),
                    rs.getString("ChucVu"),
                    String.format("%,.0f VND", rs.getDouble("Luong")),
                    dateFormat.format(ngayVaoLam)
                });
            }
        } catch (Exception e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void showEditDialog(Integer id) {
        JDialog dialog = new JDialog(this, id == null ? "Thêm nhân viên" : "Sửa thông tin NV", true);
        JPanel panel = new JPanel(new GridLayout(5, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtHoTen = new JTextField();
        txtChucVu = new JTextField();
        txtLuong = new JTextField();

        // ComboBox cho ngày/tháng/năm
        cbNgay = new JComboBox<>();
        cbThang = new JComboBox<>();
        cbNam = new JComboBox<>();

        for (int i = 1; i <= 31; i++) cbNgay.addItem(String.format("%02d", i));
        for (int i = 1; i <= 12; i++) cbThang.addItem(String.format("%02d", i));
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear - 10; i <= currentYear; i++) cbNam.addItem(String.valueOf(i));

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(cbNgay);
        datePanel.add(new JLabel("/"));
        datePanel.add(cbThang);
        datePanel.add(new JLabel("/"));
        datePanel.add(cbNam);

        if (id != null) {
            loadExistingData(id);
        }

        panel.add(new JLabel("Họ tên*:"));
        panel.add(txtHoTen);
        panel.add(new JLabel("Chức vụ*:"));
        panel.add(txtChucVu);
        panel.add(new JLabel("Lương* (VND):"));
        panel.add(txtLuong);
        panel.add(new JLabel("Ngày vào làm*:"));
        panel.add(datePanel);

        JButton btnSave = new JButton("Lưu thông tin");
        btnSave.addActionListener(e -> {
            if (validateInput()) {
                saveEmployee(id);
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
                "SELECT * FROM NhanVien WHERE ID = ?")) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtHoTen.setText(rs.getString("HoTen"));
                txtChucVu.setText(rs.getString("ChucVu"));
                txtLuong.setText(String.format("%.0f", rs.getDouble("Luong")));

                String[] dateParts = rs.getString("NgayVaoLam").split("/");
                cbNgay.setSelectedItem(dateParts[0]);
                cbThang.setSelectedItem(dateParts[1]);
                cbNam.setSelectedItem(dateParts[2]);
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu NV: " + e.getMessage());
        }
    }

    private boolean validateInput() {
        try {
            if (txtHoTen.getText().trim().isEmpty() ||
                txtChucVu.getText().trim().isEmpty() ||
                txtLuong.getText().trim().isEmpty()) {
                showError("Vui lòng điền đầy đủ các trường bắt buộc (*)");
                return false;
            }

            Double.parseDouble(txtLuong.getText());
            return true;

        } catch (NumberFormatException e) {
            showError("Lương phải là số hợp lệ!");
            return false;
        }
    }

    private void saveEmployee(Integer id) {
        String sql = id == null
                ? "INSERT INTO NhanVien (HoTen, ChucVu, Luong, NgayVaoLam) VALUES (?, ?, ?, ?)"
                : "UPDATE NhanVien SET HoTen = ?, ChucVu = ?, Luong = ?, NgayVaoLam = ? WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String ngayVaoLam = cbNgay.getSelectedItem() + "/" + 
                               cbThang.getSelectedItem() + "/" + 
                               cbNam.getSelectedItem();

            pstmt.setString(1, txtHoTen.getText());
            pstmt.setString(2, txtChucVu.getText());
            pstmt.setDouble(3, Double.parseDouble(txtLuong.getText()));
            pstmt.setString(4, ngayVaoLam);

            if (id != null) {
                pstmt.setInt(5, id);
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
            showError("Vui lòng chọn một nhân viên để chỉnh sửa");
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa nhân viên này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) model.getValueAt(row, 0);

                try (PreparedStatement pstmt = connection.prepareStatement(
                        "DELETE FROM NhanVien WHERE ID = ?")) {

                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } catch (SQLException e) {
                    showError("Lỗi xóa dữ liệu: " + e.getMessage());
                }
            }
        } else {
            showError("Vui lòng chọn một nhân viên để xóa");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagement());
    }
}