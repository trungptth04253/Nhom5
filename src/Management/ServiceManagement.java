package Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ServiceManagement extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private Connection connection;
    private JTextField txtTenDV, txtThoiGian;
    private JTextArea txtMoTa;
    private JLabel lblImage;
    private String imagePath = "";

    public ServiceManagement() {
        super("Quản lý Dịch Vụ");
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
            
            // Tạo bảng nếu chưa tồn tại
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS DichVu ("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "TenDV TEXT NOT NULL UNIQUE, "
                        + "MoTa TEXT NOT NULL, "
                        + "ThoiGian TEXT NOT NULL, "
                        + "HinhAnh TEXT)");
            }
        } catch (SQLException e) {
            showError("Lỗi kết nối database: " + e.getMessage());
        }
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Bảng danh sách dịch vụ
        model = new DefaultTableModel(new Object[]{"ID", "Tên DV", "Thời gian", "Mô tả"}, 0) {
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
        JButton btnAdd = new JButton("Thêm DV");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnBack = new JButton("Quay lại");

        // Style buttons
        styleButton(btnAdd, new Color(46, 204, 113)); // Xanh lá nhạt
        styleButton(btnEdit, new Color(52, 152, 219)); // Xanh dương
        styleButton(btnDelete, new Color(231, 76, 60)); // Đỏ
        styleButton(btnBack, new Color(149, 165, 166)); // Xám

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

        // Panel chi tiết
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setPreferredSize(new Dimension(300, 0));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết dịch vụ"));
        
        lblImage = new JLabel("", SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(280, 200));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JTextArea detailText = new JTextArea();
        detailText.setEditable(false);
        detailText.setLineWrap(true);
        detailText.setWrapStyleWord(true);
        detailText.setFont(new Font("Arial", Font.PLAIN, 14));
        
        detailPanel.add(lblImage, BorderLayout.NORTH);
        detailPanel.add(new JScrollPane(detailText), BorderLayout.CENTER);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(detailPanel, BorderLayout.EAST);

        // Sự kiện chọn hàng
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showDetails(detailText);
            }
        });

        this.add(mainPanel);
    }

    private void showDetails(JTextArea detailText) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) model.getValueAt(selectedRow, 0);
            
            try (PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM DichVu WHERE ID = ?")) {
                
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    // Hiển thị ảnh
                    String imgPath = rs.getString("HinhAnh");
                    if (imgPath != null && !imgPath.isEmpty()) {
                        ImageIcon icon = new ImageIcon(imgPath);
                        Image img = icon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH);
                        lblImage.setIcon(new ImageIcon(img));
                    } else {
                        lblImage.setIcon(null);
                        lblImage.setText("Không có ảnh");
                    }
                    
                    // Hiển thị thông tin
                    String details = "Tên dịch vụ: " + rs.getString("TenDV") + "\n\n"
                            + "Thời gian: " + rs.getString("ThoiGian") + "\n\n"
                            + "Mô tả:\n" + rs.getString("MoTa");
                    detailText.setText(details);
                }
            } catch (Exception ex) {
                showError("Lỗi tải chi tiết dịch vụ: " + ex.getMessage());
            }
        } else {
            lblImage.setIcon(null);
            lblImage.setText("Chọn dịch vụ để xem chi tiết");
            detailText.setText("");
        }
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
        String query = "SELECT ID, TenDV, ThoiGian, MoTa FROM DichVu";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String moTa = rs.getString("MoTa");
                String moTaRutGon = moTa.length() > 50 ? moTa.substring(0, 50) + "..." : moTa;
                
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("TenDV"),
                    rs.getString("ThoiGian"),
                    moTaRutGon
                });
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void showEditDialog(Integer id) {
        JDialog dialog = new JDialog(this, id == null ? "Thêm dịch vụ mới" : "Chỉnh sửa dịch vụ", true);
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtTenDV = new JTextField();
        
        // ComboBox thời gian
        JComboBox<String> cbThoiGian = new JComboBox<>(new String[]{
            "24/7",
            "07:00 - 22:00", 
            "08:00 - 20:00",
            "Tùy chỉnh"
        });
        cbThoiGian.addActionListener(e -> {
            if (cbThoiGian.getSelectedIndex() == 3) {
                txtThoiGian.setEditable(true);
                txtThoiGian.setText("");
            } else {
                txtThoiGian.setEditable(false);
                txtThoiGian.setText(cbThoiGian.getSelectedItem().toString());
            }
        });
        
        txtThoiGian = new JTextField();
        txtThoiGian.setEditable(false);
        txtMoTa = new JTextArea(5, 20);
        JScrollPane moTaScroll = new JScrollPane(txtMoTa);
        JButton btnChooseImage = new JButton("Chọn ảnh...");

        if (id != null) {
            loadExistingData(id, cbThoiGian);
        }

        btnChooseImage.addActionListener(e -> selectImage());

        inputPanel.add(new JLabel("Tên dịch vụ*:"));
        inputPanel.add(txtTenDV);
        inputPanel.add(new JLabel("Thời gian*:"));
        inputPanel.add(cbThoiGian);
        inputPanel.add(new JLabel("Mô tả*:"));
        inputPanel.add(moTaScroll);
        inputPanel.add(new JLabel("Hình ảnh:"));
        inputPanel.add(btnChooseImage);

        // Panel hình ảnh
        JPanel imagePanel = new JPanel();
        lblImage = new JLabel("", SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(200, 150));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        imagePanel.add(lblImage);

        // Nút lưu
        JButton btnSave = new JButton("Lưu thông tin");
        btnSave.addActionListener(e -> {
            if (validateInput()) {
                saveService(id);
                dialog.dispose();
                loadData();
            }
        });

        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(imagePanel, BorderLayout.CENTER);
        contentPanel.add(btnSave, BorderLayout.SOUTH);

        dialog.add(contentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void selectImage() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Chọn ảnh dịch vụ");
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "png", "jpeg"));

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File source = fc.getSelectedFile();
                File destDir = new File("service_images");
                if (!destDir.exists()) {
                    destDir.mkdir();
                }

                String fileName = "service_" + System.currentTimeMillis() + 
                                 source.getName().substring(source.getName().lastIndexOf("."));
                File dest = new File(destDir, fileName);

                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                imagePath = dest.getAbsolutePath();

                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                showError("Lỗi tải ảnh: " + ex.getMessage());
            }
        }
    }

    private void loadExistingData(int id, JComboBox<String> cbThoiGian) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM DichVu WHERE ID = ?")) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtTenDV.setText(rs.getString("TenDV"));
                txtMoTa.setText(rs.getString("MoTa"));
                imagePath = rs.getString("HinhAnh");
                
                // Load ảnh nếu có
                if (imagePath != null && !imagePath.isEmpty()) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    lblImage.setIcon(new ImageIcon(img));
                }
                
                // Xử lý thời gian
                String thoiGian = rs.getString("ThoiGian");
                boolean found = false;
                for (int i = 0; i < cbThoiGian.getItemCount() - 1; i++) {
                    if (cbThoiGian.getItemAt(i).equals(thoiGian)) {
                        cbThoiGian.setSelectedIndex(i);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    cbThoiGian.setSelectedIndex(3); // Chọn "Tùy chỉnh"
                    txtThoiGian.setText(thoiGian);
                }
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu dịch vụ: " + e.getMessage());
        }
    }

    private boolean validateInput() {
        if (txtTenDV.getText().trim().isEmpty() || 
            txtThoiGian.getText().trim().isEmpty() || 
            txtMoTa.getText().trim().isEmpty()) {
            showError("Vui lòng điền đầy đủ các trường bắt buộc (*)");
            return false;
        }
        return true;
    }

    private void saveService(Integer id) {
        String sql = id == null
                ? "INSERT INTO DichVu (TenDV, MoTa, ThoiGian, HinhAnh) VALUES (?, ?, ?, ?)"
                : "UPDATE DichVu SET TenDV = ?, MoTa = ?, ThoiGian = ?, HinhAnh = ? WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, txtTenDV.getText());
            pstmt.setString(2, txtMoTa.getText());
            pstmt.setString(3, txtThoiGian.getText());
            pstmt.setString(4, imagePath);

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
            showError("Vui lòng chọn một dịch vụ để chỉnh sửa");
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa dịch vụ này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) model.getValueAt(row, 0);

                try (PreparedStatement pstmt = connection.prepareStatement(
                        "DELETE FROM DichVu WHERE ID = ?")) {

                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } catch (SQLException e) {
                    showError("Lỗi xóa dữ liệu: " + e.getMessage());
                }
            }
        } else {
            showError("Vui lòng chọn một dịch vụ để xóa");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServiceManagement());
    }
}