package grand.pkgfinal;

import Form.LoginSign;
import Utils.UserSession;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Không vẽ viền
    }
}

public class HotelBookingUI extends JFrame {
    private JFrame mainFrame;

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Lỗi tải driver CSDL: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public HotelBookingUI() {
        initialize();
    }

    private void initialize() {
        System.out.println("Khởi động ứng dụng đặt phòng khách sạn...");
        mainFrame = new JFrame("Hệ Thống Đặt Phòng Khách Sạn");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1200, 800));

        // Main panel với scroll
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        // Thêm các thành phần vào mainPanel
        mainPanel.add(createNavPanel(mainFrame));
        mainPanel.add(createBannerSection());
        mainPanel.add(createActionButtons());
        mainPanel.add(createSearchSection());
        mainPanel.add(createHotelListings());
        mainPanel.add(createServiceSection());
        mainPanel.add(createFooter());

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    public void showFrame() {
        mainFrame.setVisible(true);
    }

    private void openServiceFrame(String serviceType) {
        mainFrame.setVisible(false);

        switch (serviceType) {
            case "Dịch vụ":
                new DichVu(mainFrame).setVisible(true);
                break;
            case "Combo":
                new Combo(mainFrame).setVisible(true);
                break;
            case "Ưu đãi":
                new UuDai(mainFrame).setVisible(true);
                break;
        }
    }

    // ========== CÁC PHƯƠNG THỨC TẠO COMPONENT ==========
    private JPanel createNavPanel(JFrame mainFrame) {
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setPreferredSize(new Dimension(1200, 80));
        navPanel.setBackground(new Color(240, 240, 240));

        // ===== PHẦN SOCIAL ICONS (TRÁI) =====
        JPanel socialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        socialPanel.setBackground(new Color(240, 240, 240));

        // Thêm các icon mạng xã hội
        socialPanel.add(createIconLabel("/facebook.png", 30, 30));
        socialPanel.add(Box.createHorizontalStrut(10));
        socialPanel.add(createIconLabel("/twitter.png", 30, 30));
        socialPanel.add(Box.createHorizontalStrut(10));
        socialPanel.add(createIconLabel("/instagram.png", 30, 30));

        // ===== PHẦN LOGO (GIỮA) =====
        JLabel logoLabel = createIconLabel("/logo.png", 200, 50);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(new Color(240, 240, 240));
        logoPanel.add(logoLabel);

        // ===== PHẦN ĐĂNG NHẬP/ĐĂNG KÝ (PHẢI) =====
        JPanel authPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        authPanel.setBackground(new Color(240, 240, 240));

        // Thêm nút đăng nhập/đăng ký
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(e -> {
            mainFrame.dispose();
            new LoginSign().setVisible(true);
        });
        authPanel.add(btnLogin);

        // ===== GẮN CÁC PHẦN VÀO NAV PANEL =====
        navPanel.add(socialPanel, BorderLayout.WEST);
        navPanel.add(logoPanel, BorderLayout.CENTER);
        navPanel.add(authPanel, BorderLayout.EAST);
        
        return navPanel;
    }

    private JPanel createBannerSection() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        panel.add(createBannerCard("/banner1.jpg", "Ưu đãi mùa hè", "Giảm đến 40%"));
        panel.add(createBannerCard("/banner2.jpg", "Trải nghiệm sang trọng", "Phòng Suite VIP"));

        return panel;
    }

    private JPanel createBannerCard(String imgPath, String title, String subtitle) {
        JPanel card = new JPanel(new BorderLayout());
        JLabel imgLabel = createIconLabel(imgPath, 500, 250);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JLabel subLabel = new JLabel(subtitle);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subLabel.setForeground(Color.WHITE);

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(titleLabel);
        textPanel.add(subLabel);

        card.add(imgLabel, BorderLayout.CENTER);
        card.add(textPanel, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createSearchSection() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JTextField searchField = new JTextField(30);
        searchField.setPreferredSize(new Dimension(500, 45));
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));

        RoundedButton searchButton = new RoundedButton("TÌM KIẾM");
        customizeButton(searchButton, new Color(0, 120, 215));
        searchButton.addActionListener(e
                -> JOptionPane.showMessageDialog(null, "Đang tìm kiếm: " + searchField.getText())
        );

        panel.add(searchField);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(searchButton);
        return panel;
    }

    private JPanel createActionButtons() {
       JPanel panel = new JPanel();
       panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

       // Nút Menu
       RoundedButton btnMenu = new RoundedButton("MENU");
       customizeButton(btnMenu, Color.GRAY);
       btnMenu.addActionListener(e -> {
           mainFrame.setVisible(false);
           new Menu(mainFrame);
       });

       // Nút Đặt phòng (CORE FUNCTION)
       RoundedButton btnBook = new RoundedButton("ĐẶT PHÒNG");
       customizeButton(btnBook, new Color(240, 194, 57));
       btnBook.addActionListener(e -> {
           // Kiểm tra trạng thái đăng nhập
           if (UserSession.getCurrentUser() == null) {
               int choice = JOptionPane.showConfirmDialog(
                   mainFrame,
                   "Bạn cần đăng nhập để đặt phòng! Đăng nhập ngay?",
                   "Yêu cầu đăng nhập",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.QUESTION_MESSAGE
               );

               if (choice == JOptionPane.YES_OPTION) {
                   mainFrame.dispose();
                   new LoginSign().setVisible(true);
               }
           } else {
               mainFrame.dispose();
               new DanhSachPhong().setVisible(true);
           }
       });

       // Nút My Booking
       RoundedButton btnMyBooking = new RoundedButton("MY BOOKING");
       customizeButton(btnMyBooking, Color.GRAY);
       btnMyBooking.addActionListener(e -> {
           if (UserSession.getCurrentUser() == null) {
               JOptionPane.showMessageDialog(mainFrame,
                   "Vui lòng đăng nhập để xem đặt phòng của bạn",
                   "Yêu cầu đăng nhập",
                   JOptionPane.WARNING_MESSAGE);
           } else {
               // Triển khai logic xem lịch sử đặt phòng
               JOptionPane.showMessageDialog(mainFrame,
                   "Hiển thị danh sách đặt phòng của bạn...",
                   "Lịch sử đặt phòng",
                   JOptionPane.INFORMATION_MESSAGE);
           }
       });

    panel.add(btnMenu);
    panel.add(Box.createHorizontalStrut(15));
    panel.add(btnBook);
    panel.add(Box.createHorizontalStrut(15));
    panel.add(btnMyBooking);
    
    return panel;
}
    private JPanel createHotelListings() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 150, 40, 150));

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://KEANDANHSIEUPHA:1433;"
                + "databaseName=SOF2042_FINAL_TEST;"
                + "user=sa;"
                + "password=123;"
                + "trustServerCertificate=true"); 
             PreparedStatement pstmt = conn.prepareStatement(
                "SELECT TOP 2 * FROM Phong WHERE ID IN (1,2) ORDER BY ID")) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                panel.add(createHotelItem(
                        rs.getString("LoaiPhong"),
                        String.format("%,.0f VND/đêm", rs.getDouble("GiaPhong")),
                        "/hotel" + rs.getInt("ID") + ".jpg"
                ));
                panel.add(Box.createVerticalStrut(20));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Lỗi tải dữ liệu phòng: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }

        return panel;
    }

    private JPanel createHotelItem(String name, String price, String imgPath) {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15))
        );

        JLabel imgLabel = createIconLabel(imgPath, 350, 200);
        panel.add(imgLabel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setForeground(new Color(240, 194, 57));

        JTextArea desc = new JTextArea("Phòng rộng rãi với đầy đủ tiện nghi, view đẹp");
        desc.setLineWrap(true);
        desc.setEditable(false);
        desc.setBackground(null);

        RoundedButton bookBtn = new RoundedButton("ĐẶT NGAY");
        customizeButton(bookBtn, new Color(50, 168, 82));
        bookBtn.addActionListener(e -> showBookingDialog(name));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(desc);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(bookBtn);

        panel.add(infoPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createServiceSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 50));
        panel.setBackground(new Color(248, 248, 248));

        JLabel title = new JLabel("DỊCH VỤ & ƯU ĐÃI", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        JPanel grid = new JPanel(new GridLayout(1, 3, 20, 20));
        grid.setMaximumSize(new Dimension(1000, 400));

        grid.add(createServiceCard("/spa.jpg", "Dịch vụ", "Massage thư giãn"));
        grid.add(createServiceCard("/promo.jpg", "Ưu đãi", "Giảm giá 30%"));
        grid.add(createServiceCard("/combo.jpg", "Combo", "Phòng + Ăn sáng"));

        panel.add(grid);
        return panel;
    }

    private JPanel createServiceCard(String imgPath, String title, String desc) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15))
        );
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openServiceFrame(title.trim());
            }
        });

        JLabel imgLabel = createIconLabel(imgPath, 280, 160);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(imgLabel);
        card.add(Box.createVerticalStrut(15));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));

        JLabel descLabel = new JLabel(desc, SwingConstants.CENTER);
        card.add(descLabel);

        return card;
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));
        panel.setPreferredSize(new Dimension(1200, 100));

        JLabel label = new JLabel(
                "<html><center><font color='white'>HOTEL BOOKING SYSTEM</font><br>"
                + "<font color='silver'>Địa chỉ: 123 Nguyễn Huệ, TP.HCM | Hotline: 1900 1234</font></center></html>"
        );
        panel.add(label);
        return panel;
    }

    private JLabel createIconLabel(String path, int width, int height) {
        try (InputStream imgStream = HotelBookingUI.class.getResourceAsStream(path)) {
            if (imgStream == null) {
                return createErrorLabel(width, height);
            }

            BufferedImage image = ImageIO.read(imgStream);
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (IOException e) {
            return createErrorLabel(width, height);
        }
    }

    private JLabel createErrorLabel(int width, int height) {
        JLabel label = new JLabel("[ẢNH LỖI]");
        label.setPreferredSize(new Dimension(width, height));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setBorder(BorderFactory.createLineBorder(Color.RED));
        return label;
    }

    private void customizeButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void showBookingDialog(String roomType) {
        JDialog dialog = new JDialog(mainFrame, "Đặt phòng", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Loại phòng:"));
        JComboBox<String> roomCombo = new JComboBox<>(new String[]{"Phòng Đơn", "Phòng Đôi", "Suite"});
        if (roomType != null) {
            roomCombo.setSelectedItem(roomType);
        }
        formPanel.add(roomCombo);

        formPanel.add(new JLabel("Ngày nhận phòng:"));
        formPanel.add(new JTextField());

        formPanel.add(new JLabel("Ngày trả phòng:"));
        formPanel.add(new JTextField());

        formPanel.add(new JLabel("Số lượng:"));
        formPanel.add(new JComboBox<>(new String[]{"1", "2", "3", "4"}));

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Đặt phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(confirmButton, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelBookingUI hotelUI = new HotelBookingUI();
            hotelUI.showFrame();
        });
    }
}