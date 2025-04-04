package grand.pkgfinal;

import javax.swing.*;
import java.awt.*;

public class UuDai extends JFrame {
    public UuDai(JFrame parentFrame) {
        setTitle("Ưu Đãi Đặc Biệt");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tiêu đề
        JLabel titleLabel = new JLabel("ƯU ĐÃI ĐẶC BIỆT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Danh sách ưu đãi
        JPanel promoPanel = new JPanel();
        promoPanel.setLayout(new BoxLayout(promoPanel, BoxLayout.Y_AXIS));
        promoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        addPromoItem(promoPanel, "Giảm 30% Cuối Tuần", "/promo1.jpg", "Áp dụng thứ 7, chủ nhật", "30%");
        addPromoItem(promoPanel, "Ưu Đãi Thành Viên", "/promo2.jpg", "Tích điểm đổi quà", "15%");
        addPromoItem(promoPanel, "Combo Mùa Hè", "/promo3.jpg", "Đặt trước 7 ngày", "25%");
        
        mainPanel.add(new JScrollPane(promoPanel), BorderLayout.CENTER);
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
    
    private void addPromoItem(JPanel panel, String title, String imagePath, String condition, String discount) {
        JPanel itemPanel = new JPanel(new BorderLayout(20, 10));
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(15, 15, 15, 15))
        );
        
        // Hình ảnh ưu đãi
        JLabel imageLabel = HotelBookingUI.createIconLabel(imagePath, 150, 150);
        itemPanel.add(imageLabel, BorderLayout.WEST);
        
        // Thông tin ưu đãi
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel conditionLabel = new JLabel(condition);
        conditionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(conditionLabel);
        
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        
        // Hiển thị phần trăm giảm giá
        JLabel discountLabel = new JLabel(discount, SwingConstants.CENTER);
        discountLabel.setFont(new Font("Arial", Font.BOLD, 30));
        discountLabel.setForeground(new Color(220, 50, 50));
        discountLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        itemPanel.add(discountLabel, BorderLayout.EAST);
        
        panel.add(itemPanel);
        panel.add(Box.createVerticalStrut(20));
    }
}