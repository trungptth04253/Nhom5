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
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navPanel.setPreferredSize(new Dimension(1200, 80));

        JLabel fbIcon = new JLabel(new ImageIcon("path/to/facebook.jpg"));
        JLabel ytIcon = new JLabel(new ImageIcon("path/to/youtube.jpg"));
        JLabel tiktokIcon = new JLabel(new ImageIcon("path/to/tiktok.jpg"));
        JLabel logoLabel = new JLabel(new ImageIcon("D:/KhachSan/tải xuống.jpg"));

        navPanel.add(fbIcon);
        navPanel.add(ytIcon);
        navPanel.add(tiktokIcon);
        navPanel.add(logoLabel);

        mainPanel.add(navPanel);

        // Banner Panel
        System.out.println("Setting up banner panel...");
        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bannerPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 500, 250));
        bannerPanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 500, 250));
        mainPanel.add(bannerPanel);

        // Search Panel
        System.out.println("Setting up search panel...");
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JTextField searchField = new JTextField(60);
        searchField.setPreferredSize(new Dimension(700, 40));
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
        menuButton.setPreferredSize(new Dimension(150, 50));

        JButton bookButton = new RoundedButton("Đặt phòng");
        bookButton.setBackground(new Color(240, 194, 57));
        bookButton.setForeground(Color.WHITE);
        bookButton.setPreferredSize(new Dimension(200, 70));

        JButton bookingButton = new RoundedButton("Booking");
        bookingButton.setBackground(Color.GRAY);
        bookingButton.setForeground(Color.WHITE);
        bookingButton.setPreferredSize(new Dimension(150, 50));

        buttonPanel.add(menuButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(bookingButton);
        mainPanel.add(buttonPanel);

        // Hotel Listing Panel
        System.out.println("Setting up hotel listing panel...");
        JPanel hotelPanel = new JPanel();
        hotelPanel.setLayout(new GridLayout(0, 1, 0, 20));
        hotelPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));

        for (int i = 1; i <= 2; i++) {
            System.out.println("Adding hotel item " + i);
            JPanel hotelItem = new JPanel(new BorderLayout(20, 0));
            JLabel hotelImage = createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 450, 320);
            JPanel hotelInfo = new JPanel();
            hotelInfo.setLayout(new BoxLayout(hotelInfo, BoxLayout.Y_AXIS));
            JLabel hotelName = new JLabel("Hotel Name " + i);
            JLabel hotelPrice = new JLabel("2,000,000 đ / night");
            hotelName.setFont(new Font("Arial", Font.BOLD, 20));
            hotelPrice.setFont(new Font("Arial", Font.PLAIN, 18));
            hotelInfo.add(hotelName);
            hotelInfo.add(hotelPrice);
            hotelItem.add(hotelImage, BorderLayout.WEST);
            hotelItem.add(hotelInfo, BorderLayout.CENTER);
            hotelPanel.add(hotelItem);
        }
        mainPanel.add(hotelPanel);

        // Panel Dịch vụ - Ưu đãi - Combo
        System.out.println("Setting up service panel...");
        JPanel servicePanel = new JPanel(new GridLayout(2, 3, 10, 10));
        servicePanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JLabel serviceLabel = new JLabel("Dịch vụ", SwingConstants.CENTER);
        JLabel discountLabel = new JLabel("Ưu đãi", SwingConstants.CENTER);
        JLabel comboLabel = new JLabel("Combo", SwingConstants.CENTER);

        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Color lightGray = new Color(120, 120, 120);

        serviceLabel.setFont(titleFont);
        serviceLabel.setForeground(lightGray);
        discountLabel.setFont(titleFont);
        discountLabel.setForeground(lightGray);
        comboLabel.setFont(titleFont);
        comboLabel.setForeground(lightGray);

        servicePanel.add(serviceLabel);
        servicePanel.add(discountLabel);
        servicePanel.add(comboLabel);

        servicePanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 200, 150));
        servicePanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 200, 150));
        servicePanel.add(createResizedImageLabel("D:/KhachSan/tải xuống.jpg", 200, 150));

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
