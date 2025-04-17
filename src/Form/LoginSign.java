package Form;

import grand.pkgfinal.HotelBookingUI;
import DAOS.KhachHangDAO;
import Models.KhachHang;
import Utils.UserSession;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginSign extends JFrame {
    
    private JPanel pnlLogin;
    private JPanel pnlSignUp;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblSignUp;
    private JLabel lblForgotPassword;
    
    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPasswordSignUp;
    private JPasswordField txtConfirmPassword;
    private JTextField txtPhone;
    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private ButtonGroup bgGender;
    private JCheckBox cbShowPass;
    private JDateChooser dateChooser;
    private JButton btnSignUp;
    private JLabel lblLogin;
    
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private final Color ACCENT_COLOR = new Color(231, 76, 60);
    
    private final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public LoginSign() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponents() {
        setTitle("Đăng nhập / Đăng ký");
        setMinimumSize(new Dimension(800, 600));
        getContentPane().setLayout(new BorderLayout());
        
        pnlLogin = createLoginPanel();
        pnlSignUp = createSignUpPanel();
        
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(pnlLogin, "login");
        cardPanel.add(pnlSignUp, "signup");
        
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "login");
    }
    
    private JPanel createLoginPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR, 1),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
        lblTitle.setFont(TITLE_FONT);
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(lblTitle);
        formPanel.add(Box.createVerticalStrut(30));
        
        // Email
        JLabel lblUsernameTitle = new JLabel("Email:");
        lblUsernameTitle.setFont(HEADER_FONT);
        lblUsernameTitle.setForeground(TEXT_COLOR);
        lblUsernameTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblUsernameTitle);
        formPanel.add(Box.createVerticalStrut(5));
        
        txtUsername = new JTextField();
        txtUsername.setFont(REGULAR_FONT);
        txtUsername.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(txtUsername);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Mật khẩu
        JLabel lblPasswordTitle = new JLabel("Mật khẩu:");
        lblPasswordTitle.setFont(HEADER_FONT);
        lblPasswordTitle.setForeground(TEXT_COLOR);
        lblPasswordTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblPasswordTitle);
        formPanel.add(Box.createVerticalStrut(5));
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(REGULAR_FONT);
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(txtPassword);
        formPanel.add(Box.createVerticalStrut(10));
        
        // Hiển thị mật khẩu
        JCheckBox cbShowPassword = new JCheckBox("Hiển thị mật khẩu");
        cbShowPassword.setFont(SMALL_FONT);
        cbShowPassword.setBackground(Color.WHITE);
        cbShowPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbShowPassword.addActionListener(e -> 
            txtPassword.setEchoChar(cbShowPassword.isSelected() ? (char) 0 : '•')
        );
        formPanel.add(cbShowPassword);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Nút đăng nhập
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(HEADER_FONT);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnLogin.addActionListener(e -> handleLogin());
        formPanel.add(btnLogin);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Panel bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.WHITE);
        
        // Quên mật khẩu
        lblForgotPassword = new JLabel("Quên mật khẩu?");
        lblForgotPassword.setFont(SMALL_FONT);
        lblForgotPassword.setForeground(SECONDARY_COLOR);
        lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblForgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblForgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handleForgotPassword();
            }
        });
        bottomPanel.add(lblForgotPassword);
        bottomPanel.add(Box.createVerticalStrut(15));
        
        // Đăng ký
        JPanel signupPanel = new JPanel();
        signupPanel.setBackground(Color.WHITE);
        signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblNoAccount = new JLabel("Chưa có tài khoản? ");
        lblNoAccount.setFont(SMALL_FONT);
        lblNoAccount.setForeground(TEXT_COLOR);
        signupPanel.add(lblNoAccount);
        
        lblSignUp = new JLabel("Đăng ký ngay");
        lblSignUp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSignUp.setForeground(PRIMARY_COLOR);
        lblSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSignUp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cl = (CardLayout)(pnlLogin.getParent().getLayout());
                cl.show(pnlLogin.getParent(), "signup");
            }
        });
        signupPanel.add(lblSignUp);
        
        bottomPanel.add(signupPanel);
        formPanel.add(bottomPanel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(formPanel, gbc);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }
    
    private JPanel createSignUpPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR, 1),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
        lblTitle.setFont(TITLE_FONT);
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(lblTitle);
        formPanel.add(Box.createVerticalStrut(25));
        
        // Họ và tên
        addFormField(formPanel, "Họ và tên:", txtName = new JTextField());
        
        // Ngày sinh
        JLabel lblBirthTitle = new JLabel("Ngày sinh:");
        lblBirthTitle.setFont(HEADER_FONT);
        lblBirthTitle.setForeground(TEXT_COLOR);
        lblBirthTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblBirthTitle);
        formPanel.add(Box.createVerticalStrut(5));
        
        dateChooser = new JDateChooser();
        dateChooser.setFont(REGULAR_FONT);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(dateChooser);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Giới tính
        JLabel lblGenderTitle = new JLabel("Giới tính:");
        lblGenderTitle.setFont(HEADER_FONT);
        lblGenderTitle.setForeground(TEXT_COLOR);
        lblGenderTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblGenderTitle);
        formPanel.add(Box.createVerticalStrut(5));
        
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.X_AXIS));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        rbMale = new JRadioButton("Nam");
        rbMale.setFont(REGULAR_FONT);
        rbMale.setBackground(Color.WHITE);
        rbMale.setSelected(true);
        
        rbFemale = new JRadioButton("Nữ");
        rbFemale.setFont(REGULAR_FONT);
        rbFemale.setBackground(Color.WHITE);
        
        bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        
        genderPanel.add(rbMale);
        genderPanel.add(Box.createHorizontalStrut(30));
        genderPanel.add(rbFemale);
        genderPanel.add(Box.createHorizontalGlue());
        
        formPanel.add(genderPanel);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Số điện thoại
        addFormField(formPanel, "Số điện thoại:", txtPhone = new JTextField());
        
        // Email
        addFormField(formPanel, "Email:", txtEmail = new JTextField());
        
        // Mật khẩu
        addFormField(formPanel, "Mật khẩu:", txtPasswordSignUp = new JPasswordField());
        
        // Nhập lại mật khẩu
        addFormField(formPanel, "Nhập lại mật khẩu:", txtConfirmPassword = new JPasswordField());
        
        // Hiển thị mật khẩu
        cbShowPass = new JCheckBox("Hiển thị mật khẩu");
        cbShowPass.setFont(SMALL_FONT);
        cbShowPass.setBackground(Color.WHITE);
        cbShowPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbShowPass.addActionListener(e -> {
            boolean show = cbShowPass.isSelected();
            txtPasswordSignUp.setEchoChar(show ? (char) 0 : '•');
            txtConfirmPassword.setEchoChar(show ? (char) 0 : '•');
        });
        formPanel.add(cbShowPass);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Nút đăng ký
        btnSignUp = new JButton("Đăng ký");
        btnSignUp.setFont(HEADER_FONT);
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(PRIMARY_COLOR);
        btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSignUp.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSignUp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnSignUp.addActionListener(e -> handleSignUp());
        formPanel.add(btnSignUp);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Đăng nhập
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblHaveAccount = new JLabel("Đã có tài khoản? ");
        lblHaveAccount.setFont(SMALL_FONT);
        lblHaveAccount.setForeground(TEXT_COLOR);
        loginPanel.add(lblHaveAccount);
        
        lblLogin = new JLabel("Đăng nhập");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLogin.setForeground(PRIMARY_COLOR);
        lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cl = (CardLayout)(pnlSignUp.getParent().getLayout());
                cl.show(pnlSignUp.getParent(), "login");
            }
        });
        loginPanel.add(lblLogin);
        
        formPanel.add(loginPanel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(formPanel, gbc);
        
        scrollPane.setViewportView(centerPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    private void addFormField(JPanel panel, String label, JTextField textField) {
        JLabel lblTitle = new JLabel(label);
        lblTitle.setFont(HEADER_FONT);
        lblTitle.setForeground(TEXT_COLOR);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(5));
        
        textField.setFont(REGULAR_FONT);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(textField);
        panel.add(Box.createVerticalStrut(15));
    }
    
    private void handleLogin() {
        String email = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            showError("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (!isValidEmail(email)) {
            showError("Email không hợp lệ");
            return;
        }
        
        KhachHang user = KhachHangDAO.getByEmail(email);
        if (user == null) {
            showError("Email không tồn tại trong hệ thống");
            return;
        }
        
        String hashedPassword = hashPassword(password);
        if (!user.getMatKhau().equals(hashedPassword)) {
            showError("Mật khẩu không chính xác");
            return;
        }
        
        // Set user session
        UserSession.setCurrentUser(user);
        
        JOptionPane.showMessageDialog(this, 
                "Đăng nhập thành công!\nChào mừng, " + user.getTen(), 
                "Thành công", 
                JOptionPane.INFORMATION_MESSAGE);
        
        // Đóng form đăng nhập và mở giao diện chính
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            HotelBookingUI hotelUI = new HotelBookingUI();
            hotelUI.showFrame();
        });
    }
    
    private void handleSignUp() {
        String name = txtName.getText().trim();
        Date birthDate = dateChooser.getDate();
        String gender = rbMale.isSelected() ? "Nam" : "Nữ";
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPasswordSignUp.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        
        if (name.isEmpty() || birthDate == null || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showError("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if (!isValidEmail(email)) {
            showError("Email không hợp lệ");
            return;
        }
        
        if (!isValidPhone(phone)) {
            showError("Số điện thoại không hợp lệ");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Mật khẩu nhập lại không khớp");
            return;
        }
        
        if (!isStrongPassword(password)) {
            showError("Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số");
            return;
        }
        
        String hashedPassword = hashPassword(password);
        boolean success = KhachHangDAO.addKH(name, birthDate, gender, phone, email, hashedPassword);
        
        if (success) {
            JOptionPane.showMessageDialog(this, 
                    "Đăng ký thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            // Chuyển về form đăng nhập
            CardLayout cl = (CardLayout)(pnlSignUp.getParent().getLayout());
            cl.show(pnlSignUp.getParent(), "login");
            
            // Clear form
            txtName.setText("");
            dateChooser.setDate(null);
            rbMale.setSelected(true);
            txtPhone.setText("");
            txtEmail.setText("");
            txtPasswordSignUp.setText("");
            txtConfirmPassword.setText("");
        } else {
            showError("Đăng ký thất bại. Email có thể đã tồn tại.");
        }
    }
    
    private void handleForgotPassword() {
        String email = JOptionPane.showInputDialog(this, 
                "Nhập email của bạn:", 
                "Quên mật khẩu", 
                JOptionPane.QUESTION_MESSAGE);
        
        if (email == null || email.trim().isEmpty()) {
            return;
        }
        
        if (!isValidEmail(email)) {
            showError("Email không hợp lệ");
            return;
        }
        
        KhachHang user = KhachHangDAO.getByEmail(email);
        if (user == null) {
            showError("Email không tồn tại trong hệ thống");
            return;
        }
        
        JOptionPane.showMessageDialog(this, 
                "Hướng dẫn đặt lại mật khẩu đã được gửi đến email của bạn.\n" +
                "Vui lòng kiểm tra hộp thư đến.", 
                "Thông báo", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9]{10}$";
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }
    
    private boolean isStrongPassword(String password) {
        return password.length() >= 8 && 
               Pattern.compile("[A-Z]").matcher(password).find() && 
               Pattern.compile("[a-z]").matcher(password).find() && 
               Pattern.compile("[0-9]").matcher(password).find();
    }
    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new LoginSign().setVisible(true);
        });
    }
}

class JDateChooser extends JPanel {
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private String dateFormatString;
    
    public JDateChooser() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        
        dayComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        yearComboBox = new JComboBox<>();
        
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(String.format("%02d", i));
        }
        
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(String.format("%02d", i));
        }
        
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 100; i--) {
            yearComboBox.addItem(String.valueOf(i));
        }
        
        customizeComboBox(dayComboBox);
        customizeComboBox(monthComboBox);
        customizeComboBox(yearComboBox);
        
        add(new JLabel("Ngày:"));
        add(Box.createHorizontalStrut(5));
        add(dayComboBox);
        add(Box.createHorizontalStrut(10));
        add(new JLabel("Tháng:"));
        add(Box.createHorizontalStrut(5));
        add(monthComboBox);
        add(Box.createHorizontalStrut(10));
        add(new JLabel("Năm:"));
        add(Box.createHorizontalStrut(5));
        add(yearComboBox);
        
        dateFormatString = "dd/MM/yyyy";
    }
    
    private void customizeComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(Color.WHITE);
        comboBox.setPreferredSize(new Dimension(80, 30));
        comboBox.setMaximumSize(new Dimension(80, 30));
    }
    
    public void setDateFormatString(String format) {
        this.dateFormatString = format;
    }
    
    public void setFont(Font font) {
        if (dayComboBox != null) {
            dayComboBox.setFont(font);
            monthComboBox.setFont(font);
            yearComboBox.setFont(font);
            
            for (Component comp : getComponents()) {
                comp.setFont(font);
            }
        }
    }
    
    public Date getDate() {
        try {
            String day = (String) dayComboBox.getSelectedItem();
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();
            
            if (day == null || month == null || year == null) {
                return null;
            }
            
            String dateString = day + "/" + month + "/" + year;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public void setDate(Date date) {
        if (date == null) {
            // Clear selection
            dayComboBox.setSelectedIndex(0);
            monthComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedIndex(0);
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = sdf.format(date);
        String[] parts = dateStr.split("/");
        
        if (parts.length == 3) {
            // Set day
            dayComboBox.setSelectedItem(parts[0]);
            
            // Set month
            monthComboBox.setSelectedItem(parts[1]);
            
            // Set year
            yearComboBox.setSelectedItem(parts[2]);
        }
    }
}