package grand.pkgfinal;

import javax.swing.*;
import java.awt.*;

class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
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

public class HotelBookingUI {
    public static void main(String[] args) {
        System.out.println("Initializing Hotel Booking UI...");
        JFrame frame = new JFrame("Hotel Booking");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Thêm khả năng cuộn
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Navigation Panel
        System.out.println("Setting up navigation panel...");
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setPreferredSize(new Dimension(1200, 80));

        JPanel socialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        socialPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 30, 30));
        socialPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 30, 30));
        socialPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 30, 30));

        JLabel logoLabel = createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 120, 60);
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        navPanel.add(socialPanel, BorderLayout.WEST);
        navPanel.add(logoPanel, BorderLayout.CENTER);
        mainPanel.add(navPanel);

        // Banner Panel
        System.out.println("Setting up banner panel...");
        JPanel bannerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bannerPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 500, 250));
        bannerPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 500, 250));
        mainPanel.add(bannerPanel);

        // Search Panel
        System.out.println("Setting up search panel...");
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JTextField searchField = new JTextField(40);
        searchField.setPreferredSize(new Dimension(500, 40));
        JButton searchButton = new JButton("Tìm kiếm");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);

        // Button Panel
        System.out.println("Setting up button panel...");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton menuButton = new RoundedButton("Menu");
        menuButton.setBackground(Color.GRAY);
        menuButton.setForeground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(120, 40));

        JButton bookButton = new RoundedButton("Đặt phòng");
        bookButton.setBackground(new Color(240, 194, 57));
        bookButton.setForeground(Color.WHITE);
        bookButton.setPreferredSize(new Dimension(150, 50));

        JButton bookingButton = new RoundedButton("Booking");
        bookingButton.setBackground(Color.GRAY);
        bookingButton.setForeground(Color.WHITE);
        bookingButton.setPreferredSize(new Dimension(120, 40));

        buttonPanel.add(menuButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(bookingButton);
        mainPanel.add(buttonPanel);

        // Hotel Listing Panel
        System.out.println("Setting up hotel listing panel...");
        JPanel hotelPanel = new JPanel(new GridLayout(0, 1, 0, 20));
        hotelPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));

        for (int i = 1; i <= 2; i++) {
            JPanel hotelItem = new JPanel(new BorderLayout(20, 0));
            JLabel hotelImage = createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 450, 320);
            JPanel hotelInfo = new JPanel();
            hotelInfo.setLayout(new BoxLayout(hotelInfo, BoxLayout.Y_AXIS));
            JLabel hotelName = new JLabel("Hotel Name " + i);
            JLabel hotelPrice = new JLabel("2,000,000 đ / night");
            hotelInfo.add(hotelName);
            hotelInfo.add(hotelPrice);
            hotelItem.add(hotelImage, BorderLayout.WEST);
            hotelItem.add(hotelInfo, BorderLayout.CENTER);
            hotelPanel.add(hotelItem);
        }
        mainPanel.add(hotelPanel);

        // Service Panel
        System.out.println("Setting up service panel...");
        JPanel servicePanel = new JPanel(new GridLayout(2, 3, 10, 10));
        servicePanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        servicePanel.add(new JLabel("Dịch vụ", SwingConstants.CENTER));
        servicePanel.add(new JLabel("Ưu đãi", SwingConstants.CENTER));
        servicePanel.add(new JLabel("Combo", SwingConstants.CENTER));
        servicePanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 350, 400));
        servicePanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 350, 400));
        servicePanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 350, 400));
        mainPanel.add(servicePanel);

        // Footer Panel
        System.out.println("Setting up footer panel...");
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(200, 200, 200));
        footerPanel.setPreferredSize(new Dimension(1200, 100));
        JLabel footerLabel = new JLabel("Thông tin liên hệ - Điều khoản sử dụng - Chính sách bảo mật", SwingConstants.CENTER);
        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel);

        System.out.println("Finalizing UI setup...");
        frame.setVisible(true);
    }

    private static JLabel createResizedImageLabel(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
}
