package grand.pkgfinal;

import javax.swing.*;
import java.awt.*;

public class Combo extends JFrame {
    public Combo(JFrame parentFrame) {
        setTitle("Combo Ưu Đãi");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tiêu đề
        JLabel titleLabel = new JLabel("COMBO TIẾT KIỆM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Danh sách combo
        JPanel comboPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        comboPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        addComboItem(comboPanel, "Combo Cơ Bản", "/combo1.jpg", "1 đêm + ăn sáng", "1,500,000 VND");
        addComboItem(comboPanel, "Combo Gia Đình", "/combo2.jpg", "2 đêm + ăn sáng + tối", "3,200,000 VND");
        addComboItem(comboPanel, "Combo VIP", "/combo3.jpg", "3 đêm + ăn thoải mái + spa", "5,800,000 VND");
        
        mainPanel.add(new JScrollPane(comboPanel), BorderLayout.CENTER);
        add(mainPanel);
                // ======= THÊM PHẦN HEADER (LOGO + NÚT BACK) =======
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        
        // Nút Back
        JButton backButton = new JButton("← Quay lại");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });
        
        // Logo
        JLabel logoLabel = HotelBookingUI.createIconLabel("/logo.png", 150, 40);
        
        // Thêm components vào header
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(logoLabel, BorderLayout.CENTER);
        
        // Thêm header vào frame
        add(headerPanel, BorderLayout.NORTH);
    }
    
    private void addComboItem(JPanel panel, String name, String imagePath, String details, String price) {
        JPanel itemPanel = new JPanel(new BorderLayout(20, 10));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        // Hình ảnh combo
        JLabel imageLabel = HotelBookingUI.createIconLabel(imagePath, 200, 150);
        itemPanel.add(imageLabel, BorderLayout.WEST);
        
        // Thông tin combo
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel detailsLabel = new JLabel(details);
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(new Color(220, 50, 50));
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(detailsLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        
        // Nút đặt combo
        JButton bookButton = new JButton("Đặt Ngay");
        bookButton.setBackground(new Color(50, 150, 250));
        bookButton.setForeground(Color.WHITE);
        itemPanel.add(bookButton, BorderLayout.EAST);
        
        panel.add(itemPanel);
    }
}