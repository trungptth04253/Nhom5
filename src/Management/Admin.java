package Management;

import Form.LoginSign;
import grand.pkgfinal.DanhSachPhong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Admin extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private Connection connection;
    private JTextField txtSoPhong, txtLoaiPhong, txtGiaPhong;
    private JLabel lblImage, lblDetailImage, lblDetailSoPhong, lblDetailLoaiPhong, lblDetailGiaPhong;
    private String imagePath = "";

    public Admin() {
        super("Quản lý khách sạn - Admin");
        initializeUI();
        connectDB();
        loadData();
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
        try {
            String dbPath = "hotel_booking.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            try (Statement stmt = connection.createStatement()) {
                // Tạo bảng Phòng
                stmt.execute("CREATE TABLE IF NOT EXISTS Phong ("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "SoPhong INTEGER NOT NULL UNIQUE, "
                        + "LoaiPhong TEXT NOT NULL, "
                        + "GiaPhong REAL NOT NULL, "
                        + "TrangThai TEXT NOT NULL DEFAULT 'Trống', "
                        + "HinhAnh TEXT)");
                
                // Tạo bảng Dịch Vụ
                stmt.execute("CREATE TABLE IF NOT EXISTS DichVu ("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "TenDV TEXT NOT NULL UNIQUE, "
                        + "MoTa TEXT NOT NULL, "
                        + "ThoiGian TEXT NOT NULL, "
                        + "HinhAnh TEXT)");
                
                // Tạo bảng Nhân Viên
                stmt.execute("CREATE TABLE IF NOT EXISTS NhanVien ("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "HoTen TEXT NOT NULL, "
                        + "ChucVu TEXT NOT NULL, "
                        + "Luong REAL NOT NULL, "
                        + "NgayVaoLam TEXT NOT NULL)");
                
                // Tạo bảng Khách Hàng
                stmt.execute("CREATE TABLE IF NOT EXISTS KhachHang ("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "HoTen TEXT NOT NULL, "
                        + "CCCD TEXT UNIQUE, "
                        + "SDT TEXT NOT NULL, "
                        + "Email TEXT)");
            }
        } catch (SQLException e) {
            showError("Lỗi kết nối database: " + e.getMessage());
        }
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Bảng danh sách
        model = new DefaultTableModel(new Object[]{"ID", "Số phòng", "Loại phòng", "Giá phòng"}, 0) {
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
        JButton btnAdd = new JButton("Thêm phòng");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnView = new JButton("Xem DS Phòng");
        JButton btnEmployeeMgmt = new JButton("Quản lý NV");
        JButton btnCustomerMgmt = new JButton("Quản lý KH");
        JButton btnServiceMgmt = new JButton("Quản lý DV");
        JButton btnLogout = new JButton("Đăng xuất");

        // Style buttons
        styleButton(btnAdd, new Color(0, 128, 0)); // Xanh lá
        styleButton(btnEdit, new Color(0, 0, 255)); // Xanh dương
        styleButton(btnDelete, new Color(255, 0, 0)); // Đỏ
        styleButton(btnView, new Color(128, 0, 128)); // Tím
        styleButton(btnEmployeeMgmt, new Color(255, 165, 0)); // Cam
        styleButton(btnCustomerMgmt, new Color(0, 191, 255)); // Xanh nhạt
        styleButton(btnServiceMgmt, new Color(46, 204, 113)); // Xanh lá nhạt
        styleButton(btnLogout, new Color(128, 128, 128)); // Xám

        // Thêm sự kiện
        btnAdd.addActionListener(e -> showEditDialog(null));
        btnEdit.addActionListener(e -> editSelected());
        btnDelete.addActionListener(e -> deleteSelected());
        btnView.addActionListener(e -> new DanhSachPhong().setVisible(true));
        btnEmployeeMgmt.addActionListener(e -> openEmployeeManagement());
        btnCustomerMgmt.addActionListener(e -> openCustomerManagement());
        btnServiceMgmt.addActionListener(e -> openServiceManagement());
        btnLogout.addActionListener(e -> logout());

        // Thêm nút vào panel
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);
        buttonPanel.add(btnEmployeeMgmt);
        buttonPanel.add(btnCustomerMgmt);
        buttonPanel.add(btnServiceMgmt);
        buttonPanel.add(btnLogout);

        // Panel chi tiết phòng
        JPanel detailPanel = createDetailPanel();

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(detailPanel, BorderLayout.EAST);

        // Sự kiện chọn hàng
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showRoomDetails();
            }
        });

        this.add(mainPanel);
    }

    private JPanel createDetailPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Thông tin chi tiết"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(300, 0));

        // Panel hình ảnh
        lblDetailImage = new JLabel("", SwingConstants.CENTER);
        lblDetailImage.setPreferredSize(new Dimension(280, 200));
        lblDetailImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Panel thông tin
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblSoPhongTitle = new JLabel("Số phòng:");
        lblSoPhongTitle.setFont(labelFont);
        lblDetailSoPhong = new JLabel();
        lblDetailSoPhong.setFont(valueFont);

        JLabel lblLoaiPhongTitle = new JLabel("Loại phòng:");
        lblLoaiPhongTitle.setFont(labelFont);
        lblDetailLoaiPhong = new JLabel();
        lblDetailLoaiPhong.setFont(valueFont);

        JLabel lblGiaPhongTitle = new JLabel("Giá phòng:");
        lblGiaPhongTitle.setFont(labelFont);
        lblDetailGiaPhong = new JLabel();
        lblDetailGiaPhong.setFont(valueFont);

        infoPanel.add(lblSoPhongTitle);
        infoPanel.add(lblDetailSoPhong);
        infoPanel.add(lblLoaiPhongTitle);
        infoPanel.add(lblDetailLoaiPhong);
        infoPanel.add(lblGiaPhongTitle);
        infoPanel.add(lblDetailGiaPhong);

        panel.add(lblDetailImage);
        panel.add(Box.createVerticalStrut(20));
        panel.add(infoPanel);

        return panel;
    }

    private void showRoomDetails() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) model.getValueAt(selectedRow, 0);

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM Phong WHERE ID = ?")) {

                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Hiển thị thông tin
                    lblDetailSoPhong.setText(String.valueOf(rs.getInt("SoPhong")));
                    lblDetailLoaiPhong.setText(rs.getString("LoaiPhong"));
                    lblDetailGiaPhong.setText(String.format("%,.0f VND", rs.getDouble("GiaPhong")));

                    // Hiển thị ảnh
                    String imagePath = rs.getString("HinhAnh");
                    if (imagePath != null && !imagePath.isEmpty()) {
                        try {
                            BufferedImage img = ImageIO.read(new File(imagePath));
                            ImageIcon icon = new ImageIcon(img.getScaledInstance(280, 200, Image.SCALE_SMOOTH));
                            lblDetailImage.setIcon(icon);
                            lblDetailImage.setText("");
                        } catch (IOException ex) {
                            lblDetailImage.setIcon(null);
                            lblDetailImage.setText("<html><center>Không tải được ảnh<br>" + imagePath + "</center></html>");
                        }
                    } else {
                        lblDetailImage.setIcon(null);
                        lblDetailImage.setText("Không có ảnh");
                    }
                }
            } catch (SQLException ex) {
                showError("Lỗi tải chi tiết phòng: " + ex.getMessage());
            }
        } else {
            // Xóa thông tin khi không có hàng nào được chọn
            lblDetailSoPhong.setText("");
            lblDetailLoaiPhong.setText("");
            lblDetailGiaPhong.setText("");
            lblDetailImage.setIcon(null);
            lblDetailImage.setText("Chọn một phòng để xem chi tiết");
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
        String query = "SELECT ID, SoPhong, LoaiPhong, GiaPhong FROM Phong";

        try (Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getInt("SoPhong"),
                    rs.getString("LoaiPhong"),
                    String.format("%,.0f VND", rs.getDouble("GiaPhong"))
                });
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void showEditDialog(Integer id) {
        JDialog dialog = new JDialog(this, id == null ? "Thêm phòng mới" : "Chỉnh sửa phòng", true);
        JPanel panel = new JPanel(new GridLayout(5, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtSoPhong = new JTextField();
        txtLoaiPhong = new JTextField();
        txtGiaPhong = new JTextField();
        lblImage = new JLabel("", SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(200, 150));
        JButton btnChooseImage = new JButton("Chọn ảnh...");

        if (id != null) {
            loadExistingData(id);
        }

        btnChooseImage.addActionListener(e -> selectImage());

        panel.add(new JLabel("Số phòng*:"));
        panel.add(txtSoPhong);
        panel.add(new JLabel("Loại phòng*:"));
        panel.add(txtLoaiPhong);
        panel.add(new JLabel("Giá phòng* (VND):"));
        panel.add(txtGiaPhong);
        panel.add(new JLabel("Hình ảnh:"));
        panel.add(btnChooseImage);
        panel.add(new JLabel("Xem trước:"));
        panel.add(lblImage);

        JButton btnSave = new JButton("Lưu thông tin");
        btnSave.addActionListener(e -> {
            if (validateInput()) {
                saveRoom(id);
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
                "SELECT * FROM Phong WHERE ID = ?")) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtSoPhong.setText(String.valueOf(rs.getInt("SoPhong")));
                txtLoaiPhong.setText(rs.getString("LoaiPhong"));
                txtGiaPhong.setText(String.valueOf(rs.getDouble("GiaPhong")));
                imagePath = rs.getString("HinhAnh");
                loadImagePreview();
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu phòng: " + e.getMessage());
        }
    }

    private void selectImage() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Chọn ảnh phòng");
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Ảnh (*.jpg, *.png)", "jpg", "png"));

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();

            File imagesDir = new File("images");
            if (!imagesDir.exists()) {
                boolean created = imagesDir.mkdir();
                if (!created) {
                    showError("Không thể tạo thư mục images!");
                    return;
                }
            }

            String fileName = "room_" + System.currentTimeMillis()
                    + selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            File destFile = new File(imagesDir, fileName);

            try {
                Files.copy(
                        selectedFile.toPath(),
                        destFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );

                imagePath = "images/" + fileName;
                loadImagePreview();

            } catch (IOException ex) {
                showError("Lỗi khi sao chép ảnh: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void loadImagePreview() {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                BufferedImage img = ImageIO.read(new File(imagePath));
                ImageIcon icon = new ImageIcon(img.getScaledInstance(200, 150, Image.SCALE_SMOOTH));
                lblImage.setIcon(icon);
            } catch (Exception e) {
                lblImage.setIcon(null);
                showError("Không thể đọc file ảnh!");
            }
        } else {
            lblImage.setIcon(null);
        }
    }

    private boolean validateInput() {
        try {
            if (txtSoPhong.getText().trim().isEmpty()
                    || txtLoaiPhong.getText().trim().isEmpty()
                    || txtGiaPhong.getText().trim().isEmpty()) {
                showError("Vui lòng điền đầy đủ các trường bắt buộc (*)");
                return false;
            }

            Integer.parseInt(txtSoPhong.getText());
            Double.parseDouble(txtGiaPhong.getText());
            return true;

        } catch (NumberFormatException e) {
            showError("Số phòng và giá phòng phải là số hợp lệ!");
            return false;
        }
    }

    private void saveRoom(Integer id) {
        String sql = id == null
                ? "INSERT INTO Phong (SoPhong, LoaiPhong, GiaPhong, HinhAnh, TrangThai) VALUES (?, ?, ?, ?, ?)"
                : "UPDATE Phong SET SoPhong = ?, LoaiPhong = ?, GiaPhong = ?, HinhAnh = ?, TrangThai = ? WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(txtSoPhong.getText()));
            pstmt.setString(2, txtLoaiPhong.getText());
            pstmt.setDouble(3, Double.parseDouble(txtGiaPhong.getText()));
            pstmt.setString(4, imagePath);
            pstmt.setString(5, "Trống");

            if (id != null) {
                pstmt.setInt(6, id);
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
            showError("Vui lòng chọn một phòng để chỉnh sửa");
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa phòng này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) model.getValueAt(row, 0);

                try (PreparedStatement pstmt = connection.prepareStatement(
                        "DELETE FROM Phong WHERE ID = ?")) {

                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");

                } catch (SQLException e) {
                    showError("Lỗi xóa dữ liệu: " + e.getMessage());
                }
            }
        } else {
            showError("Vui lòng chọn một phòng để xóa");
        }
    }

    private void openEmployeeManagement() {
        this.dispose();
        new EmployeeManagement().setVisible(true);
    }

    private void openCustomerManagement() {
        this.dispose();
        new CustomerManagement().setVisible(true);
    }

    private void openServiceManagement() {
        this.dispose();
        new ServiceManagement().setVisible(true);
    }

    private void logout() {
        this.dispose();
        // Quay lại màn hình đăng nhập
        new LoginSign().setVisible(true);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Admin());
    }
}