package grand.pkgfinal;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVu extends JFrame {

    private JFrame parentFrame;

    public DichVu(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setTitle("Dịch Vụ Khách Sạn");
        initializeUI();
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ======= HEADER =======
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nút back
        JButton backButton = new JButton("← Quay lại");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });

        // Logo
        JLabel logoLabel = new JLabel("KHÁCH SẠN LUXURY", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(new Color(41, 128, 185));

        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(logoLabel, BorderLayout.CENTER);

        // ======= NỘI DUNG CHÍNH =======
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JLabel titleLabel = new JLabel("DỊCH VỤ CAO CẤP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(46, 204, 113));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(30));

        // Panel chứa các dịch vụ
        JPanel servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));

        // Lấy dữ liệu dịch vụ từ database
        List<Service> services = getServicesFromDB();

        if (services.isEmpty()) {
            servicesPanel.add(new JLabel("Hiện chưa có dịch vụ nào", SwingConstants.CENTER));
        } else {
            for (Service service : services) {
                servicesPanel.add(createServicePanel(service));
                servicesPanel.add(Box.createVerticalStrut(20));
            }
        }

        contentPanel.add(new JScrollPane(servicesPanel));

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    private List<Service> getServicesFromDB() {
        List<Service> services = new ArrayList<>();
        String dbPath = "hotel_booking.db";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM DichVu")) {

            while (rs.next()) {
                Service service = new Service(
                        rs.getInt("ID"),
                        rs.getString("TenDV"),
                        rs.getString("MoTa"),
                        rs.getString("ThoiGian"),
                        rs.getString("HinhAnh")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải danh sách dịch vụ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return services;
    }

    private JPanel createServicePanel(Service service) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219)), 
                BorderFactory.createEmptyBorder(15, 15, 15, 15))
        );
        panel.setMaximumSize(new Dimension(800, 250));
        panel.setBackground(Color.WHITE);

        // Panel hình ảnh
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(250, 200));
        
        JLabel imageLabel = new JLabel();
        if (service.getImagePath() != null && !service.getImagePath().isEmpty()) {
            ImageIcon icon = new ImageIcon(service.getImagePath());
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image img = icon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } else {
                imageLabel.setText("Không tải được ảnh");
            }
        } else {
            imageLabel.setText("Không có ảnh");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        imagePanel.add(imageLabel);

        // Panel thông tin
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(service.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(new Color(41, 128, 185));

        JLabel timeLabel = new JLabel("⏰ " + service.getTime());
        timeLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        timeLabel.setForeground(new Color(149, 165, 166));

        JTextArea descArea = new JTextArea(service.getDescription());
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBackground(null);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(timeLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(new JScrollPane(descArea));

        panel.add(imagePanel, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);

        return panel;
    }

    // Lớp nội bộ để lưu trữ thông tin dịch vụ
    private static class Service {
        private int id;
        private String name;
        private String description;
        private String time;
        private String imagePath;

        public Service(int id, String name, String description, String time, String imagePath) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.time = time;
            this.imagePath = imagePath;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getTime() { return time; }
        public String getImagePath() { return imagePath; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DichVu(null));
    }
}