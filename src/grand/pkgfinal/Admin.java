package grand.pkgfinal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Admin extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private Connection connection;
    private JTextField txtSoPhong, txtLoaiPhong, txtGiaPhong;
    private JComboBox<String> cbTrangThai;
    private JLabel lblImage;
    private String imagePath = "";

    public Admin() {
        super("Quản lý phòng - Admin");
        initializeUI();
        connectDB();
        loadData();
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
        try {
            // Tạo file database trong thư mục project
            String dbPath = "hotel_booking.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            // Tạo bảng nếu chưa tồn tại
            createTablesIfNotExist();
        } catch (SQLException e) {
            showError("Lỗi kết nối SQLite: " + e.getMessage());
        }
    }

    private void createTablesIfNotExist() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Phong (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "SoPhong INTEGER NOT NULL, " +
            "LoaiPhong TEXT NOT NULL, " +
            "GiaPhong REAL NOT NULL, " +
            "TrangThai TEXT NOT NULL, " +
            "HinhAnh TEXT)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    private void initializeUI() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        
        // Table setup
        model = new DefaultTableModel(
            new Object[]{"ID", "Số phòng", "Loại phòng", "Giá phòng", "Trạng thái"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton btnAdd = new JButton("Thêm phòng");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        
        // Button styling
        styleButton(btnAdd, new Color(0, 128, 0));
        styleButton(btnEdit, new Color(0, 0, 255));
        styleButton(btnDelete, new Color(255, 0, 0));
        
        // Add actions
        btnAdd.addActionListener(e -> showEditDialog(null));
        btnEdit.addActionListener(e -> editSelected());
        btnDelete.addActionListener(e -> deleteSelected());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        
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
        String query = "SELECT ID, SoPhong, LoaiPhong, GiaPhong, TrangThai FROM Phong";
        System.out.println("Executing query: " + query); // Debug

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getInt("SoPhong"),
                    rs.getString("LoaiPhong"),
                    rs.getDouble("GiaPhong"),
                    rs.getString("TrangThai")
                });
            }
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState()); // S0002
            System.err.println("Error Code: " + e.getErrorCode()); // 50000
            showError("Lỗi truy vấn: " + e.getMessage());
        }
    }

    private void showEditDialog(Integer id) {
        JDialog dialog = new JDialog(this, id == null ? "Thêm phòng mới" : "Chỉnh sửa phòng", true);
        JPanel panel = new JPanel(new GridLayout(6, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize components
        txtSoPhong = new JTextField();
        txtLoaiPhong = new JTextField();
        txtGiaPhong = new JTextField();
        cbTrangThai = new JComboBox<>(new String[]{"Trống", "Đã đặt"});
        lblImage = new JLabel();
        JButton btnChooseImage = new JButton("Chọn ảnh...");
        
        // Load data if editing
        if (id != null) {
            loadExistingData(id);
        }
        
        // Image chooser
        btnChooseImage.addActionListener(e -> selectImage());
        
        // Add components
        panel.add(new JLabel("Số phòng*:"));
        panel.add(txtSoPhong);
        panel.add(new JLabel("Loại phòng*:"));
        panel.add(txtLoaiPhong);
        panel.add(new JLabel("Giá phòng*:"));
        panel.add(txtGiaPhong);
        panel.add(new JLabel("Trạng thái*:"));
        panel.add(cbTrangThai);
        panel.add(new JLabel("Hình ảnh:"));
        panel.add(btnChooseImage);
        panel.add(new JLabel());
        panel.add(lblImage);
        
        // Save button
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
                cbTrangThai.setSelectedItem(rs.getString("TrangThai"));
                imagePath = rs.getString("HinhAnh");
                loadImagePreview();
            }
        } catch (SQLException e) {
            showError("Lỗi tải dữ liệu phòng: " + e.getMessage());
        }
    }

private void selectImage() {
    JFileChooser fc = new JFileChooser();
    fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
        "Ảnh phòng (*.jpg, *.png)", "jpg", "png"));
    
    if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File sourceFile = fc.getSelectedFile();
        
        // Tạo thư mục images nếu chưa có
        File imagesDir = new File("./images/rooms");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
        
        // Copy ảnh vào thư mục project
        String destPath = "./images/rooms/" + sourceFile.getName();
        try {
            Files.copy(sourceFile.toPath(), 
                      new File(destPath).toPath(), 
                      StandardCopyOption.REPLACE_EXISTING);
            imagePath = destPath; // Lưu đường dẫn tương đối
            loadImagePreview();
        } catch (IOException e) {
            showError("Lỗi khi sao chép ảnh: " + e.getMessage());
        }
    }
}

    private void loadImagePreview() {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(200, 150, Image.SCALE_SMOOTH));
            lblImage.setIcon(icon);
        } catch (Exception e) {
            lblImage.setIcon(null);
            showError("Không thể đọc file ảnh!");
        }
    }

    private boolean validateInput() {
        try {
            if (txtSoPhong.getText().trim().isEmpty() ||
                txtLoaiPhong.getText().trim().isEmpty() ||
                txtGiaPhong.getText().trim().isEmpty()) {
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
        String sql = id == null ?
            "INSERT INTO Phong (SoPhong, LoaiPhong, GiaPhong, TrangThai, HinhAnh) VALUES (?, ?, ?, ?, ?)" :
            "UPDATE Phong SET SoPhong = ?, LoaiPhong = ?, GiaPhong = ?, TrangThai = ?, HinhAnh = ? WHERE ID = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(txtSoPhong.getText()));
            pstmt.setString(2, txtLoaiPhong.getText());
            pstmt.setDouble(3, Double.parseDouble(txtGiaPhong.getText()));
            pstmt.setString(4, (String) cbTrangThai.getSelectedItem());
            pstmt.setString(5, imagePath);
            
            if (id != null) pstmt.setInt(6, id);
            
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