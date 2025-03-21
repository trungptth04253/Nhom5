/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grand.pkgfinal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class TrangChu {
        public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Booking");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Sử dụng layout tùy chỉnh để định vị tự do

        // Navigation Panel (Chứa logo và icon mạng xã hội)
        JPanel navPanel = new JPanel();
        navPanel.setBounds(0, 0, 800, 50);
        navPanel.setLayout(null);
        
        // Logo (Căn giữa)
        ImageIcon logoIcon = new ImageIcon("path/to/logo.jpg");
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(350, 0, 100, 50); // Căn giữa thanh nav
        navPanel.add(logoLabel);

        // Mạng xã hội (Góc trên cùng bên trái)
        JLabel fbIcon = createImageLabel("path/to/facebook.jpg", 30, 30, 10, 10);
        JLabel ytIcon = createImageLabel("path/to/youtube.jpg", 30, 30, 50, 10);
        JLabel tiktokIcon = createImageLabel("path/to/tiktok.jpg", 30, 30, 90, 10);
        navPanel.add(fbIcon);
        navPanel.add(ytIcon);
        navPanel.add(tiktokIcon);

        frame.add(navPanel);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(50, 60, 700, 50);
        headerPanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("REVUER - *Slogan");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Tìm kiếm");
        headerPanel.add(titleLabel);
        headerPanel.add(searchField);
        headerPanel.add(searchButton);
        frame.add(headerPanel);

        // Buttons
        JButton menuButton = new RoundedButton("Menu");
        menuButton.setBounds(200, 120, 120, 40);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(new Color(100, 100, 100));
        frame.add(menuButton);

        JButton bookButton = new RoundedButton("Đặt phòng");
        bookButton.setBounds(340, 110, 160, 40); // Dịch lên trên chút
        bookButton.setBackground(new Color(240, 194, 57)); // Màu F0C239
        bookButton.setForeground(Color.WHITE); // Chữ màu trắng
        frame.add(bookButton);

        JButton bookingButton = new RoundedButton("Booking");
        bookingButton.setBounds(520, 120, 120, 40);
        bookingButton.setForeground(Color.WHITE);
        bookingButton.setBackground(new Color(100, 100, 100));
        frame.add(bookingButton);

        // Hotel Listings
        JLabel hotelImage1 = createImageLabel("path/to/hotel1.jpg", 200, 150, 50, 170);
        JLabel hotel1 = createTextLabel("Hotel name - 8.6 - 2,000,000 đ / room / night", 50, 330);
        JLabel hotelImage2 = createImageLabel("path/to/hotel2.jpg", 200, 150, 50, 370);
        JLabel hotel2 = createTextLabel("Hotel name - 8.6 - 2,000,000 đ / room / night", 50, 530);
        frame.add(hotelImage1);
        frame.add(hotel1);
        frame.add(hotelImage2);
        frame.add(hotel2);

        // Footer Panel
        JLabel serviceLabel = createImageLabel("path/to/service.jpg", 150, 100, 50, 570);
        JLabel discountLabel = createImageLabel("path/to/discount.jpg", 150, 100, 250, 570);
        JLabel comboLabel = createImageLabel("path/to/combo.jpg", 150, 100, 450, 570);
        frame.add(serviceLabel);
        frame.add(discountLabel);
        frame.add(comboLabel);

        frame.setVisible(true);
    }

    private static JLabel createImageLabel(String path, int width, int height, int x, int y) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(x, y, width, height);
        return label;
    }

    private static JLabel createTextLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 250, 20);
        return label;
    }
}

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
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Không vẽ viền
    }
}
