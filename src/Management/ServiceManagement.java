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
        super("Quản lý dịch vụ");
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
        model = new DefaultTableModel(
                new Object[]{"ID", "Tên DV", "Thời gian", "Mô tả"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton btnAdd = new JButton("Thêm DV");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnBack = new JButton("Quay lại");

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

        // Panel chi tiết
        JPanel detailPanel = createDetailPanel();

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(detailPanel, BorderLayout.EAST);

        table.getSelectionModel().addListSelectionListener(e -> showServiceDetails());
        this.add(mainPanel);
    }

    private JPanel createDetailPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Chi tiết dịch vụ"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(300, 0));

        lblImage = new JLabel("", SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(280, 200));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JTextArea detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);

        panel.add(lblImage);
        panel.add(new JScrollPane(detailArea));

        return panel;
    }

    private void showServiceDetails() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) model.getValueAt(selectedRow, 0);
            
            try (PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM DichVu WHERE ID = ?")) {
                
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    String imagePath = rs.getString("HinhAnh");
                    if (imagePath != null && !imagePath.isEmpty()) {
                        ImageIcon icon = new ImageIcon(imagePath);
                        lblImage.setIcon(new ImageIcon(icon.getImage()
                                .getScaledInstance(280, 200, Image.SCALE_SMOOTH)));
                    }
                    
                    String details = "Tên dịch vụ: " + rs.getString("TenDV") + "\n\n"
                            + "Thời gian: " + rs.getString("ThoiGian") + "\n\n"
                            + "Mô tả:\n" + rs.getString("MoTa");
                    ((JTextArea)((JScrollPane)detailPanel.getComponent(1)).getViewport().getView()).setText(details);
                }
            } catch (SQLException ex) {
                showError("Lỗi tải chi tiết: " + ex.getMessage());
            }
        }
    }

    private void loadData() {
        model.setRowCount(0);
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM DichVu")) {
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("TenDV"),
                    rs.getString("ThoiGian"),
                    rs.getString("MoTa").substring(0, Math.min(50, rs.getString("MoTa").length())) + "..."
                });
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void showEditDialog(Integer id) {
        JDialog dialog = new JDialog(this, id == null ? "Thêm dịch vụ" : "Sửa dịch vụ", true);
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        txtTenDV = new JTextField();
        txtMoTa = new JTextArea(3, 20);
        JScrollPane scrollMoTa = new JScrollPane(txtMoTa);
        JComboBox<String> cbThoiGian = new JComboBox<>(new String[]{
            "Full time 24/7", 
            "07:00 - 22:00", 
            "08:00 - 20:00", 
            "Tùy chỉnh"
        });
        JButton btnChooseImage = new JButton("Chọn ảnh");
        lblImage = new JLabel();

        if (id != null) {
            loadExistingService(id);
        }

        btnChooseImage.addActionListener(e -> selectImage());

        inputPanel.add(new JLabel("Tên dịch vụ*:"));
        inputPanel.add(txtTenDV);
        inputPanel.add(new JLabel("Thời gian*:"));
        inputPanel.add(cbThoiGian);
        inputPanel.add(new JLabel("Mô tả*:"));
        inputPanel.add(scrollMoTa);
        inputPanel.add(new JLabel("Hình ảnh:"));
        inputPanel.add(btnChooseImage);

        JPanel imagePanel = new JPanel();
        imagePanel.add(lblImage);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            if (validateInput()) {
                saveService(id);
                dialog.dispose();
                loadData();
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(btnSave, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void selectImage() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "png", "jpeg"));
        
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File source = fc.getSelectedFile();
                File destDir = new File("service_images");
                if (!destDir.exists()) destDir.mkdir();
                
                File dest = new File(destDir, "service_" + System.currentTimeMillis() + ".png");
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                imagePath = dest.getAbsolutePath();
                lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath)
                        .getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH));
            } catch (Exception ex) {
                showError("Lỗi tải ảnh: " + ex.getMessage());
            }
        }
    }

    private void loadExistingService(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM DichVu WHERE ID = ?")) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                txtTenDV.setText(rs.getString("TenDV"));
                txtMoTa.setText(rs.getString("MoTa"));
                imagePath = rs.getString("HinhAnh");
                if (imagePath != null) {
                    lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath)
                            .getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH));
                }
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private boolean validateInput() {
        if (txtTenDV.getText().trim().isEmpty() || 
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
            pstmt.setString(3, ((JComboBox<String>)inputPanel.getComponent(3)).getSelectedItem().toString());
            pstmt.setString(4, imagePath);
            
            if (id != null) pstmt.setInt(5, id);
            
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

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 35));
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }
}