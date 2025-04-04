package grand.pkgfinal;

import javax.swing.*;
import java.awt.*;

public class DichVu extends JFrame {
    public DichVu(JFrame parentFrame) {
        setTitle("Dịch Vụ Khách Sạn");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tiêu đề
        JLabel titleLabel = new JLabel("DỊCH VỤ CAO CẤP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Nội dung dịch vụ
        JPanel servicePanel = new JPanel(new GridLayout(3, 1, 20, 20));
        servicePanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        addServiceItem(servicePanel, "Dịch vụ Spa", "/spa.jpg", "Massage, tắm bùn khoáng, chăm sóc da mặt");
        addServiceItem(servicePanel, "Dịch vụ Ăn uống", "/dining.jpg", "Nhà hàng 5 sao với đa dạng ẩm thực");
        addServiceItem(servicePanel, "Dịch vụ Phòng", "/room_service.jpg", "Dọn phòng hàng ngày, dịch vụ 24/7");
        
        mainPanel.add(new JScrollPane(servicePanel), BorderLayout.CENTER);
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
    
    
    private void addServiceItem(JPanel panel, String title, String imagePath, String description) {
        JPanel itemPanel = new JPanel(new BorderLayout(20, 10));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        // Hình ảnh dịch vụ
        JLabel imageLabel = HotelBookingUI.createIconLabel(imagePath, 200, 150);
        itemPanel.add(imageLabel, BorderLayout.WEST);
        
        // Thông tin dịch vụ
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JTextArea descArea = new JTextArea(description);
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBackground(null);
        
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(descArea);
        
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        panel.add(itemPanel);
    }
}