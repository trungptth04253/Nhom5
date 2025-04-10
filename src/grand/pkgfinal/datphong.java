/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grand.pkgfinal;
import grand.pkgfinal.DanhSachPhong.Room;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;

public class datphong extends JDialog {
    // Màu sắc chủ đạo
    private static final Color MAIN_COLOR = new Color(240, 194, 57);
    private static final Color SECONDARY_COLOR = new Color(245, 245, 245);
    
    private Connection connection;
    private JFrame parentFrame;
    private Room room;
    
    // Form components
    private JDateChooser checkInChooser;
    private JDateChooser checkOutChooser;
    private JTextField txtTenKhach;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtCMND;
    private JTextField txtDiaChi;
    private JComboBox<String> cmbNhanVien;
    private JLabel lblTotalPrice;
    private double totalPrice;
    
    public datphong(JFrame parent, Connection conn, Room selectedRoom) {
        super(parent, "Đặt Phòng", true);
        this.parentFrame = parent;
        this.connection = conn;
        this.room = selectedRoom;
        
        initializeUI();
        loadNhanVien();
        calculatePrice();
        
        setSize(800, 700);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Header panel with room info
        JPanel roomInfoPanel = createRoomInfoPanel();
        
        // Form panel
        JScrollPane formPanel = createFormPanel();
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        
        mainPanel.add(roomInfoPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createRoomInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(0, 0, 10, 0))
        );
        
        // Room image
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 150));
        imagePanel.setBackground(new Color(250, 250, 250));
        
        try {
            Image image = javax.imageio.ImageIO.read(new java.io.File(room.getHinhAnh()))
                .getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imagePanel.add(new JLabel(new ImageIcon(image)));
        } catch (Exception e) {
            imagePanel.add(new JLabel("Ảnh không khả dụng"));
        }
        
        // Room details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("THÔNG TIN PHÒNG " + room.getSoPhong());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(MAIN_COLOR.darker());
        
        JLabel lblID = new JLabel("ID: " + room.getId());
        lblID.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblType = new JLabel("Loại phòng: " + room.getLoaiPhong());
        lblType.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblPrice = new JLabel("Giá phòng: " + String.format("%,.0f VND/đêm", room.getGiaPhong()));
        lblPrice.setFont(new Font("Arial", Font.BOLD, 16));
        lblPrice.setForeground(MAIN_COLOR.darker().darker());
        
        detailsPanel.add(lblTitle);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(lblID);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(lblType);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(lblPrice);
        
        panel.add(imagePanel, BorderLayout.WEST);
        panel.add(detailsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JScrollPane createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        // Booking details panel
        JPanel bookingPanel = new JPanel(new GridLayout(2, 2, 20, 10));
        bookingPanel.setBackground(Color.WHITE);
        bookingPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đặt phòng"));
        
        // Initialize date choosers
        checkInChooser = new JDateChooser();
        checkInChooser.setDate(new Date());
        checkInChooser.setDateFormatString("dd/MM/yyyy");
        checkInChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                calculatePrice();
            }
        });
        
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        checkOutChooser = new JDateChooser();
        checkOutChooser.setDate(tomorrow.getTime());
        checkOutChooser.setDateFormatString("dd/MM/yyyy");
        checkOutChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                calculatePrice();
            }
        });
        
        JPanel checkInPanel = createLabelFieldPanel("Check-in:", checkInChooser);
        JPanel checkOutPanel = createLabelFieldPanel("Check-out:", checkOutChooser);
        
        // Nhân viên dropdown
        cmbNhanVien = new JComboBox<>();
        JPanel nhanVienPanel = createLabelFieldPanel("Nhân viên:", cmbNhanVien);
        
        // Total price panel
        JPanel pricePanel = new JPanel(new BorderLayout());
        pricePanel.setBackground(Color.WHITE);
        JLabel lblPriceTitle = new JLabel("Tổng tiền:");
        lblPriceTitle.setFont(new Font("Arial", Font.BOLD, 16));
        
        lblTotalPrice = new JLabel();
        lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalPrice.setForeground(MAIN_COLOR.darker().darker());
        
        pricePanel.add(lblPriceTitle, BorderLayout.WEST);
        pricePanel.add(lblTotalPrice, BorderLayout.CENTER);
        
        bookingPanel.add(checkInPanel);
        bookingPanel.add(checkOutPanel);
        bookingPanel.add(nhanVienPanel);
        bookingPanel.add(pricePanel);
        
        // Customer info panel
        JPanel customerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        customerPanel.setBackground(Color.WHITE);
        customerPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        
        txtTenKhach = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtCMND = new JTextField();
        txtDiaChi = new JTextField();
        
        customerPanel.add(createLabelFieldPanel("Tên khách hàng:", txtTenKhach));
        customerPanel.add(createLabelFieldPanel("Số điện thoại:", txtSDT));
        customerPanel.add(createLabelFieldPanel("Email:", txtEmail));
        customerPanel.add(createLabelFieldPanel("CMND/CCCD:", txtCMND));
        customerPanel.add(createLabelFieldPanel("Địa chỉ:", txtDiaChi));
        
        panel.add(bookingPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(customerPanel);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        
        return scrollPane;
    }
    
    private JPanel createLabelFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 25));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        
        panel.add(label, BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setBackground(Color.WHITE);
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(120, 40));
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.addActionListener(e -> dispose());
        
        JButton btnConfirm = new JButton("Xác nhận");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirm.setPreferredSize(new Dimension(120, 40));
        btnConfirm.setBackground(MAIN_COLOR);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.addActionListener(e -> bookRoom());
        
        panel.add(btnCancel);
        panel.add(btnConfirm);
        
        return panel;
    }
    
    private void loadNhanVien() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, HoTen FROM NhanVien");
            
            while (rs.next()) {
                String nhanVienInfo = rs.getInt("ID") + " - " + rs.getString("HoTen");
                cmbNhanVien.addItem(nhanVienInfo);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi tải danh sách nhân viên: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculatePrice() {
        Date checkIn = checkInChooser.getDate();
        Date checkOut = checkOutChooser.getDate();
        
        if (checkIn != null && checkOut != null) {
            // Calculate days difference
            long diff = checkOut.getTime() - checkIn.getTime();
            int days = (int) (diff / (1000 * 60 * 60 * 24));
            
            // If check-out is same day or before check-in, set minimum to 1 day
            if (days <= 0) {
                days = 1;
            }
            
            totalPrice = days * room.getGiaPhong();
            lblTotalPrice.setText(String.format("%,.0f VND (%d ngày)", totalPrice, days));
        }
    }
    
    private void bookRoom() {
        if (!validateForm()) {
            return;
        }
        
        try {
            // Begin transaction
            connection.setAutoCommit(false);
            
            // 1. Insert into KhachHang if not exists
            int khachHangID = insertOrGetKhachHang();
            
            // 2. Insert into DatPhong
            int nhanVienID = Integer.parseInt(cmbNhanVien.getSelectedItem().toString().split(" - ")[0]);
            
            PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO DatPhong (ID_KhachHang, ID_Phong, ID_NhanVien, NgayDatPhong, CheckIn, CheckOut, TrangThai, ThanhToan) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            pstmt.setInt(1, khachHangID);
            pstmt.setInt(2, room.getId());
            pstmt.setInt(3, nhanVienID);
            pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(5, sdf.format(checkInChooser.getDate()));
            pstmt.setString(6, sdf.format(checkOutChooser.getDate()));
            pstmt.setString(7, "Đã đặt");
            pstmt.setDouble(8, totalPrice);
            
            pstmt.executeUpdate();
            
            // 3. Update room status
            PreparedStatement updateRoom = connection.prepareStatement(
                "UPDATE Phong SET TrangThai = ? WHERE ID = ?"
            );
            updateRoom.setString(1, "Đã đặt");
            updateRoom.setInt(2, room.getId());
            updateRoom.executeUpdate();
            
            // Commit transaction
            connection.commit();
            connection.setAutoCommit(true);
            
            JOptionPane.showMessageDialog(this,
                "Đặt phòng thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Refresh room list in parent frame
            if (parentFrame instanceof DanhSachPhong) {
                ((DanhSachPhong) parentFrame).reloadRooms();
            }
            
            dispose();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            JOptionPane.showMessageDialog(this,
                "Lỗi đặt phòng: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int insertOrGetKhachHang() throws SQLException {
        // First check if customer exists
        PreparedStatement checkStmt = connection.prepareStatement(
            "SELECT ID FROM KhachHang WHERE CMND = ? OR (HoTen = ? AND SDT = ?)"
        );
        checkStmt.setString(1, txtCMND.getText().trim());
        checkStmt.setString(2, txtTenKhach.getText().trim());
        checkStmt.setString(3, txtSDT.getText().trim());
        
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            // Customer exists
            return rs.getInt("ID");
        } else {
            // Insert new customer
            PreparedStatement insertStmt = connection.prepareStatement(
                "INSERT INTO KhachHang (HoTen, SDT, Email, CMND, DiaChi) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            insertStmt.setString(1, txtTenKhach.getText().trim());
            insertStmt.setString(2, txtSDT.getText().trim());
            insertStmt.setString(3, txtEmail.getText().trim());
            insertStmt.setString(4, txtCMND.getText().trim());
            insertStmt.setString(5, txtDiaChi.getText().trim());
            
            insertStmt.executeUpdate();
            
            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }
        }
    }
    
    private boolean validateForm() {
        // Validate check-in/check-out dates
        if (checkInChooser.getDate() == null || checkOutChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn ngày check-in và check-out",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (checkOutChooser.getDate().before(checkInChooser.getDate())) {
            JOptionPane.showMessageDialog(this,
                "Ngày check-out phải sau ngày check-in",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate customer info
        if (txtTenKhach.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập tên khách hàng",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            txtTenKhach.requestFocus();
            return false;
        }
        
        if (txtSDT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập số điện thoại",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            txtSDT.requestFocus();
            return false;
        }
        
        if (txtCMND.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập CMND/CCCD",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            txtCMND.requestFocus();
            return false;
        }
        
        // Validate staff selection
        if (cmbNhanVien.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn nhân viên phụ trách",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}
