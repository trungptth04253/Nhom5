/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grand.pkgfinal;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author o
 */
public class Menu extends JFrame{
    private JFrame parentFrame;
    
    public Menu(JFrame parentFrame) {
        
        this.parentFrame = parentFrame;
        setTitle("Menu");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chỉ đóng frame này, không đóng ứng dụng
        setLayout(new BorderLayout());
        
        // Thêm nút quay lại vào headerPanel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(60, 60, 60));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        
        // Tạo nút quay lại
        JButton backButton = new JButton("Quay lại");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            this.dispose(); // Đóng frame Menu
            parentFrame.setVisible(true); // Hiển thị lại frame chính
        });
        
        // Logo
        JLabel logoLabel = new JLabel("REVUER");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        
        // Panel chứa nút quay lại
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new Color(60, 60, 60));
        backPanel.add(backButton);
        
        // Thanh tìm kiếm
        JTextField searchField = new JTextField("Find");
        searchField.setPreferredSize(new Dimension(150, 30));
        
        // Panel chứa các menu chính
        JPanel topMenuPanel = new JPanel();
        topMenuPanel.setBackground(new Color(60, 60, 60));
        topMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        
        // Thêm các component vào header
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(60, 60, 60));
        searchPanel.add(searchField);
        
        headerPanel.add(backPanel, BorderLayout.WEST);
        headerPanel.add(logoLabel, BorderLayout.CENTER);
        headerPanel.add(searchPanel, BorderLayout.EAST);
        
        // Panel chính cho nội dung
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        // Panel cho các danh mục bên trái
        JPanel leftMenuPanel = new JPanel();
        leftMenuPanel.setPreferredSize(new Dimension(250, 500));
        leftMenuPanel.setBackground(new Color(240, 240, 240));
        leftMenuPanel.setLayout(new BoxLayout(leftMenuPanel, BoxLayout.Y_AXIS));
        
        // Thêm tiêu đề CONTENT cho menu trái
        JLabel contentLabel = new JLabel("Menu");
        contentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contentLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 0));
        leftMenuPanel.add(contentLabel);
        
        // Tạo các menu dropdown bên trái
        String[] leftMenuItems = {"Trang chủ", "Hình ảnh", "Dịch vụ nổi bật", "Ưu đãi", "Khuyến mãi"};
        for (String item : leftMenuItems) {
            JPanel menuItemPanel = createDropdownMenuItem(item);
            leftMenuPanel.add(menuItemPanel);
        }
        
        // Panel cho danh mục About
        JLabel browseLabel = new JLabel("About");
        browseLabel.setFont(new Font("Arial", Font.BOLD, 14));
        browseLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 0));
        leftMenuPanel.add(browseLabel);
        
        // Tạo các menu dropdown bên trái phần BROWSE
        String[] browseItems = {"Khách sạn", "Phòng"};
        for (String item : browseItems) {
            JPanel menuItemPanel = createDropdownMenuItem(item);
            leftMenuPanel.add(menuItemPanel);
        }
        
        // Panel cho danh mục TOOLS
        JLabel toolsLabel = new JLabel("Contact");
        toolsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        toolsLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 0));
        leftMenuPanel.add(toolsLabel);
        
        // Tạo các menu dropdown bên trái phần TOOLS
        String[] toolItems = {"Email", "Địa chỉ", "Hotline"};
        for (String item : toolItems) {
            JPanel menuItemPanel = createDropdownMenuItem(item);
            leftMenuPanel.add(menuItemPanel);
        }
        
        // ===== THÊM MỚI: Tạo panel chính với layout để chứa 3 ô hình ảnh =====
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setBackground(Color.WHITE);
        
        // Tạo panel cho 3 ô hình ảnh nằm cạnh nhau
        JPanel imageRowPanel = new JPanel(new GridLayout(1, 3, 15, 0)); // 1 hàng, 3 cột, khoảng cách ngang 15px
        imageRowPanel.setBackground(Color.WHITE);
        imageRowPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tạo 3 ô hình ảnh trống
        for (int i = 0; i < 3; i++) {
            // Tạo panel cho mỗi ô hình ảnh
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            
            // Tạo panel cho khu vực hiển thị hình ảnh
            JPanel imageArea = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Vẽ nền màu xám nhạt
                    g.setColor(new Color(245, 245, 245));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    
                    // Vẽ chữ "Hình ảnh trống" ở giữa
                    g.setColor(new Color(120, 120, 120));
                    g.setFont(new Font("Arial", Font.BOLD, 14));
                    
                    // Tính toán vị trí để văn bản nằm giữa
                    FontMetrics metrics = g.getFontMetrics();
                    String text = "Hình ảnh trống";
                    int x = (getWidth() - metrics.stringWidth(text)) / 2;
                    int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                    
                    g.drawString(text, x, y);
                }
            };
            imageArea.setPreferredSize(new Dimension(200, 150));
            
            // Thêm hiệu ứng hover
            imagePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    imagePanel.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200), 2));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    imagePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
                }
            });
            
            imagePanel.add(imageArea, BorderLayout.CENTER);
            imageRowPanel.add(imagePanel);
        }
        
        mainContentPanel.add(imageRowPanel, BorderLayout.NORTH);
        // ==========================================================
        
        contentPanel.add(leftMenuPanel, BorderLayout.WEST);
        contentPanel.add(mainContentPanel, BorderLayout.CENTER);
        
        // Thêm header và content vào frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        
        // Hiển thị frame
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public Menu() {
        this(null);
    }
    
    // Phương thức tạo menu item dropdown
    private JPanel createDropdownMenuItem(String itemName) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setMaximumSize(new Dimension(250, 35));
        
        // Icon giả
        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(25, 25));
        
        // Tên menu
        JLabel nameLabel = new JLabel(itemName);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Icon mũi tên
        JLabel arrowLabel = new JLabel(">");
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Highlight menu Maps
        if (itemName.equals("Trang chủ")) {
            itemPanel.setBackground(new Color(180, 200, 220));
        } else {
            itemPanel.setBackground(new Color(240, 240, 240));
        }
        
        // Thêm border và padding
        itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        itemPanel.add(iconLabel, BorderLayout.WEST);
        itemPanel.add(nameLabel, BorderLayout.CENTER);
        itemPanel.add(arrowLabel, BorderLayout.EAST);
        
        // Thêm sự kiện click để mở dropdown
        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu dropdownMenu = new JPopupMenu();
                
                // Thêm các mục menu dropdown tùy theo danh mục
                if (itemName.equals("Maps")) {
                    addMenuItem(dropdownMenu, "Survival Maps");
                    addMenuItem(dropdownMenu, "Adventure Maps");
                    addMenuItem(dropdownMenu, "Creation Maps");
                    addMenuItem(dropdownMenu, "Parkour Maps");
                    addMenuItem(dropdownMenu, "Puzzle Maps");
                } else if (itemName.equals("Servers")) {
                    addMenuItem(dropdownMenu, "Survival Servers");
                    addMenuItem(dropdownMenu, "Creative Servers");
                    addMenuItem(dropdownMenu, "Minigame Servers");
                    addMenuItem(dropdownMenu, "Modded Servers");
                } else {
                    // Menu mặc định cho các danh mục khác
                    addMenuItem(dropdownMenu, "Popular " + itemName);
                    addMenuItem(dropdownMenu, "New " + itemName);
                    addMenuItem(dropdownMenu, "Top " + itemName);
                }
                
                dropdownMenu.show(itemPanel, 0, itemPanel.getHeight());
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!itemName.equals("Maps")) {
                    itemPanel.setBackground(new Color(220, 220, 220));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!itemName.equals("Maps")) {
                    itemPanel.setBackground(new Color(240, 240, 240));
                }
            }
        });
        
        return itemPanel;
    }
    
    // Phương thức thêm các mục vào menu popup
    private void addMenuItem(JPopupMenu menu, String itemName) {
        JMenuItem item = new JMenuItem(itemName);
        item.setFont(new Font("Arial", Font.PLAIN, 12));
        item.setPreferredSize(new Dimension(180, 25));
        menu.add(item);
        
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Đã chọn: " + itemName);
            }
        });
    }
    
    // Phương thức tùy chỉnh style cho button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(80, 80, 80));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!button.getText().equals("Maps")) {
                    button.setBackground(new Color(100, 100, 100));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.getText().equals("Maps")) {
                    button.setBackground(new Color(80, 80, 80));
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu(null);
            }
        });
    }
}
