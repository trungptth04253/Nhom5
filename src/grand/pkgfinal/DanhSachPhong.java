package grand.pkgfinal;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

public class DanhSachPhong extends JFrame {
    // Màu sắc chủ đạo
    private static final Color MAIN_COLOR = new Color(240, 194, 57);
    private static final Color SECONDARY_COLOR = new Color(245, 245, 245);
    private static final Color DISABLED_COLOR = new Color(200, 200, 200);
    
    private Connection connection;
    private JPanel contentPanel;

    public DanhSachPhong() {
        super("Danh sách phòng khách sạn");
        initializeUI();
        connectDB();
        loadRooms();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbPath = "hotel_booking.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi kết nối SQLite: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void initializeUI() {
        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(SECONDARY_COLOR);

        // Header với logo
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setPreferredSize(new Dimension(1200, 100));
        headerPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        
        try {
            ImageIcon logoIcon = new ImageIcon(ImageIO.read(new File("logo.png"))
                .getScaledInstance(200, 60, Image.SCALE_SMOOTH));
            JLabel logoLabel = new JLabel(logoIcon);
            headerPanel.add(logoLabel);
        } catch (Exception e) {
            JLabel title = new JLabel("HOTEL BOOKING SYSTEM");
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setForeground(MAIN_COLOR);
            headerPanel.add(title);
        }

        // Panel danh sách phòng
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    public void reloadRooms() {
        contentPanel.removeAll();
        loadRooms();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
        
    private void loadRooms() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Phong ORDER BY SoPhong")) {
            
            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("ID"),
                    rs.getInt("SoPhong"),
                    rs.getString("LoaiPhong"),
                    rs.getDouble("GiaPhong"),
                    rs.getString("TrangThai"),
                    rs.getString("HinhAnh")
                );
                addRoomCard(room);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi tải dữ liệu: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRoomCard(Room room) {
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(20, 20, 20, 20))
        );
        card.setMaximumSize(new Dimension(1140, 250));
        // Panel hình ảnh
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(300, 200));
        imagePanel.setBackground(new Color(250, 250, 250));
        
        try {
            Image image = ImageIO.read(new File(room.getHinhAnh()))
                .getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imagePanel.add(new JLabel(new ImageIcon(image)));
        } catch (Exception e) {
            imagePanel.add(new JLabel("Ảnh không khả dụng"));
        }

        // Panel thông tin
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel lblSoPhong = new JLabel("PHÒNG " + room.getSoPhong());
        lblSoPhong.setFont(new Font("Arial", Font.BOLD, 24));
        lblSoPhong.setForeground(MAIN_COLOR.darker());

        JLabel lblLoai = new JLabel("Loại: " + room.getLoaiPhong());
        lblLoai.setFont(new Font("Arial", Font.PLAIN, 16));
        lblLoai.setForeground(new Color(100, 100, 100));

        JLabel lblGia = new JLabel("Giá: " + String.format("%,.0f VND/đêm", room.getGiaPhong()));
        lblGia.setFont(new Font("Arial", Font.BOLD, 18));
        lblGia.setForeground(MAIN_COLOR.darker().darker());

        JLabel lblTrangThai = new JLabel("Trạng thái: " + room.getTrangThai());
        lblTrangThai.setFont(new Font("Arial", Font.BOLD, 16));
        lblTrangThai.setForeground(room.getTrangThai().equals("Trống") ? 
            new Color(50, 168, 82) : new Color(220, 50, 50));

        // Nút đặt phòng
        JButton btnDatPhong = new JButton("ĐẶT PHÒNG");
        btnDatPhong.setPreferredSize(new Dimension(150, 40));
        btnDatPhong.setFont(new Font("Arial", Font.BOLD, 14));
        btnDatPhong.setBackground(MAIN_COLOR);
        btnDatPhong.setForeground(Color.WHITE);
        btnDatPhong.setBorder(new RoundBorder(20, MAIN_COLOR));
        btnDatPhong.setFocusPainted(false);
        
        if (!room.getTrangThai().equals("Trống")) {
            btnDatPhong.setEnabled(false);
            btnDatPhong.setBackground(DISABLED_COLOR);
            btnDatPhong.setText("ĐÃ ĐẶT");
        } else {
            // Add ActionListener for booking
            btnDatPhong.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    datphong bookingForm = new datphong(DanhSachPhong.this, connection, room);
                    bookingForm.setVisible(true);
                }
            });
        }

        // Hiệu ứng hover
        btnDatPhong.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if(btnDatPhong.isEnabled()) {
                    btnDatPhong.setBackground(MAIN_COLOR.brighter());
                }
            }
            
            public void mouseExited(MouseEvent e) {
                if(btnDatPhong.isEnabled()) {
                    btnDatPhong.setBackground(MAIN_COLOR);
                }
            }
        });

        // Thêm components
        infoPanel.add(lblSoPhong);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblLoai);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblGia);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblTrangThai);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(btnDatPhong);

        card.add(imagePanel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);
        
        contentPanel.add(card);
        contentPanel.add(Box.createVerticalStrut(20));
    }

    class RoundBorder extends AbstractBorder {
        private int radius;
        private Color color;

        public RoundBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
            g2.dispose();
        }
    }

    public class Room {
        private int id;
        private int soPhong;
        private String loaiPhong;
        private double giaPhong;
        private String trangThai;
        private String hinhAnh;

        public Room(int id, int soPhong, String loaiPhong, double giaPhong, String trangThai, String hinhAnh) {
            this.id = id;
            this.soPhong = soPhong;
            this.loaiPhong = loaiPhong;
            this.giaPhong = giaPhong;
            this.trangThai = trangThai;
            this.hinhAnh = hinhAnh;
        }

        public String getHinhAnh() { return hinhAnh; }
        public int getSoPhong() { return soPhong; }
        public String getLoaiPhong() { return loaiPhong; }
        public double getGiaPhong() { return giaPhong; }
        public String getTrangThai() { return trangThai; }
        public int getId() { return id; }  // Fixed this method
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DanhSachPhong());
    }
}