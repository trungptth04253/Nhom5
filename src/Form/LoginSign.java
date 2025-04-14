package Form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.util.regex.Pattern;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import DAOS.KhachHangDAO;
import Models.KhachHang;

public class LoginSign extends javax.swing.JFrame {
    
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
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public LoginSign() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập / Đăng ký");
        setSize(450, 550);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());
        
        // Initialize panels
        pnlLogin = new JPanel();
        pnlSignUp = new JPanel();
        
        // Setup login panel
        setupLoginPanel();
        
        // Setup sign up panel
        setupSignUpPanel();
        
        // Add panels to frame
        getContentPane().add(pnlLogin, "login");
        getContentPane().add(pnlSignUp, "signup");
        
        // Show login panel initially
        ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "login");
        
        pack();
    }
    
    private void setupLoginPanel() {
        pnlLogin.setLayout(null);
        pnlLogin.setPreferredSize(new java.awt.Dimension(450, 550));
        pnlLogin.setBackground(new Color(240, 240, 240));
        
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
        lblTitle.setFont(new Font("Segoe UI", 1, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 50, 450, 40);
        pnlLogin.add(lblTitle);
        
        JLabel lblUsernameTitle = new JLabel("Email:");
        lblUsernameTitle.setFont(new Font("Segoe UI", 0, 14));
        lblUsernameTitle.setBounds(75, 130, 300, 20);
        pnlLogin.add(lblUsernameTitle);
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", 0, 14));
        txtUsername.setBounds(75, 155, 300, 35);
        pnlLogin.add(txtUsername);
        
        JLabel lblPasswordTitle = new JLabel("Mật khẩu:");
        lblPasswordTitle.setFont(new Font("Segoe UI", 0, 14));
        lblPasswordTitle.setBounds(75, 200, 300, 20);
        pnlLogin.add(lblPasswordTitle);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", 0, 14));
        txtPassword.setBounds(75, 225, 300, 35);
        pnlLogin.add(txtPassword);
        
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Segoe UI", 1, 14));
        btnLogin.setBounds(75, 295, 300, 40);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBackground(new Color(65, 105, 225));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        pnlLogin.add(btnLogin);
        
        JCheckBox cbShowPassword = new JCheckBox("Hiển thị mật khẩu");
        cbShowPassword.setFont(new Font("Segoe UI", 0, 12));
        cbShowPassword.setBounds(75, 265, 300, 20);
        cbShowPassword.setBackground(new Color(240, 240, 240));
        cbShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPassword.setEchoChar(cbShowPassword.isSelected() ? (char) 0 : '•');
            }
        });
        pnlLogin.add(cbShowPassword);
        
        lblForgotPassword = new JLabel("Quên mật khẩu?");
        lblForgotPassword.setFont(new Font("Segoe UI", 2, 12));
        lblForgotPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblForgotPassword.setBounds(225, 350, 150, 25);
        lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblForgotPassword.setForeground(new Color(65, 105, 225));
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleForgotPassword();
            }
        });
        pnlLogin.add(lblForgotPassword);
        
        JLabel lblNoAccount = new JLabel("Chưa có tài khoản?");
        lblNoAccount.setFont(new Font("Segoe UI", 0, 12));
        lblNoAccount.setBounds(100, 390, 150, 25);
        pnlLogin.add(lblNoAccount);
        
        lblSignUp = new JLabel("Đăng ký ngay");
        lblSignUp.setFont(new Font("Segoe UI", 1, 12));
        lblSignUp.setBounds(235, 390, 150, 25);
        lblSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSignUp.setForeground(new Color(65, 105, 225));
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "signup");
            }
        });
        pnlLogin.add(lblSignUp);
    }
    
    private void setupSignUpPanel() {
        pnlSignUp.setLayout(null);
        pnlSignUp.setPreferredSize(new java.awt.Dimension(450, 550));
        pnlSignUp.setBackground(new Color(240, 240, 240));
        
        JLabel lblTitle = new JLabel("ĐĂNG KÝ");
        lblTitle.setFont(new Font("Segoe UI", 1, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 20, 450, 40);
        pnlSignUp.add(lblTitle);
        
        JLabel lblNameTitle = new JLabel("Họ và tên:");
        lblNameTitle.setFont(new Font("Segoe UI", 0, 14));
        lblNameTitle.setBounds(75, 70, 300, 20);
        pnlSignUp.add(lblNameTitle);
        
        txtName = new JTextField();
        txtName.setFont(new Font("Segoe UI", 0, 14));
        txtName.setBounds(75, 95, 300, 35);
        pnlSignUp.add(txtName);
        
        JLabel lblBirthTitle = new JLabel("Ngày sinh:");
        lblBirthTitle.setFont(new Font("Segoe UI", 0, 14));
        lblBirthTitle.setBounds(75, 135, 300, 20);
        pnlSignUp.add(lblBirthTitle);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(75, 160, 300, 35);
        dateChooser.setFont(new Font("Segoe UI", 0, 14));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        pnlSignUp.add(dateChooser);
        
        JLabel lblGenderTitle = new JLabel("Giới tính:");
        lblGenderTitle.setFont(new Font("Segoe UI", 0, 14));
        lblGenderTitle.setBounds(75, 200, 300, 20);
        pnlSignUp.add(lblGenderTitle);
        
        rbMale = new JRadioButton("Nam");
        rbMale.setFont(new Font("Segoe UI", 0, 14));
        rbMale.setBounds(75, 225, 100, 25);
        rbMale.setBackground(new Color(240, 240, 240));
        rbMale.setSelected(true);
        pnlSignUp.add(rbMale);
        
        rbFemale = new JRadioButton("Nữ");
        rbFemale.setFont(new Font("Segoe UI", 0, 14));
        rbFemale.setBounds(175, 225, 100, 25);
        rbFemale.setBackground(new Color(240, 240, 240));
        pnlSignUp.add(rbFemale);
        
        bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        
        JLabel lblPhoneTitle = new JLabel("Số điện thoại:");
        lblPhoneTitle.setFont(new Font("Segoe UI", 0, 14));
        lblPhoneTitle.setBounds(75, 255, 300, 20);
        pnlSignUp.add(lblPhoneTitle);
        
        txtPhone = new JTextField();
        txtPhone.setFont(new Font("Segoe UI", 0, 14));
        txtPhone.setBounds(75, 280, 300, 35);
        pnlSignUp.add(txtPhone);
        
        JLabel lblEmailTitle = new JLabel("Email:");
        lblEmailTitle.setFont(new Font("Segoe UI", 0, 14));
        lblEmailTitle.setBounds(75, 320, 300, 20);
        pnlSignUp.add(lblEmailTitle);
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Segoe UI", 0, 14));
        txtEmail.setBounds(75, 345, 300, 35);
        pnlSignUp.add(txtEmail);
        
        JLabel lblPasswordTitle = new JLabel("Mật khẩu:");
        lblPasswordTitle.setFont(new Font("Segoe UI", 0, 14));
        lblPasswordTitle.setBounds(75, 385, 300, 20);
        pnlSignUp.add(lblPasswordTitle);
        
        txtPasswordSignUp = new JPasswordField();
        txtPasswordSignUp.setFont(new Font("Segoe UI", 0, 14));
        txtPasswordSignUp.setBounds(75, 410, 300, 35);
        pnlSignUp.add(txtPasswordSignUp);
        
        JLabel lblConfirmPasswordTitle = new JLabel("Nhập lại mật khẩu:");
        lblConfirmPasswordTitle.setFont(new Font("Segoe UI", 0, 14));
        lblConfirmPasswordTitle.setBounds(75, 450, 300, 20);
        pnlSignUp.add(lblConfirmPasswordTitle);
        
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Segoe UI", 0, 14));
        txtConfirmPassword.setBounds(75, 475, 300, 35);
        pnlSignUp.add(txtConfirmPassword);
        
        cbShowPass = new JCheckBox("Hiển thị mật khẩu");
        cbShowPass.setFont(new Font("Segoe UI", 0, 12));
        cbShowPass.setBounds(75, 515, 300, 20);
        cbShowPass.setBackground(new Color(240, 240, 240));
        cbShowPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean show = cbShowPass.isSelected();
                txtPasswordSignUp.setEchoChar(show ? (char) 0 : '•');
                txtConfirmPassword.setEchoChar(show ? (char) 0 : '•');
            }
        });
        pnlSignUp.add(cbShowPass);
        
        btnSignUp = new JButton("Đăng ký");
        btnSignUp.setFont(new Font("Segoe UI", 1, 14));
        btnSignUp.setBounds(75, 545, 300, 40);
        btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSignUp.setBackground(new Color(65, 105, 225));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });
        pnlSignUp.add(btnSignUp);
        
        JLabel lblHaveAccount = new JLabel("Đã có tài khoản?");
        lblHaveAccount.setFont(new Font("Segoe UI", 0, 12));
        lblHaveAccount.setBounds(100, 595, 150, 25);
        pnlSignUp.add(lblHaveAccount);
        
        lblLogin = new JLabel("Đăng nhập");
        lblLogin.setFont(new Font("Segoe UI", 1, 12));
        lblLogin.setBounds(235, 595, 150, 25);
        lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblLogin.setForeground(new Color(65, 105, 225));
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "login");
            }
        });
        pnlSignUp.add(lblLogin);
    }
    
    private void handleLogin() {
        String email = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        // Validate inputs
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get user by email
        KhachHang user = KhachHangDAO.getByEmail(email);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Email không tồn tại trong hệ thống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verify password
        String hashedPassword = hashPassword(password);
        if (!user.getMatKhau().equals(hashedPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Login successful
        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!\nChào mừng, " + user.getTen(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
        // TODO: Navigate to main application screen
    }
    
    private void handleSignUp() {
        // Get form data
        String name = txtName.getText().trim();
        Date birthDate = dateChooser.getDate();
        String gender = rbMale.isSelected() ? "Nam" : "Nữ";
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPasswordSignUp.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        
        // Validate inputs
        if (name.isEmpty() || birthDate == null || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate phone number
        if (!isValidPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate password matching
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate password strength
        if (!isStrongPassword(password)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Calculate age
        int age = calculateAge(birthDate);
        
        // Hash password
        String hashedPassword = hashPassword(password);
        
        // Register user
        boolean success = KhachHangDAO.addKH(name, birthDate, gender, phone, email, hashedPassword);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            // Switch to login panel
            ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "login");
            
            // Clear sign up form
            txtName.setText("");
            dateChooser.setDate(null);
            rbMale.setSelected(true);
            txtPhone.setText("");
            txtEmail.setText("");
            txtPasswordSignUp.setText("");
            txtConfirmPassword.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại. Email có thể đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleForgotPassword() {
        String email = JOptionPane.showInputDialog(this, "Nhập email của bạn:", "Quên mật khẩu", JOptionPane.QUESTION_MESSAGE);
        
        if (email == null || email.trim().isEmpty()) {
            return;
        }
        
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        KhachHang user = KhachHangDAO.getByEmail(email);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Email không tồn tại trong hệ thống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // In a real application, you would send a reset password email
        // For now, we'll just show a message
        JOptionPane.showMessageDialog(this, 
                "Hướng dẫn đặt lại mật khẩu đã được gửi đến email của bạn.\n" +
                "Vui lòng kiểm tra hộp thư đến.", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
    
    private int calculateAge(Date birthDate) {
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.util.Calendar birthCal = java.util.Calendar.getInstance();
        birthCal.setTime(birthDate);
        
        int age = today.get(java.util.Calendar.YEAR) - birthCal.get(java.util.Calendar.YEAR);
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < birthCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }
        
        return age;
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginSign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginSign().setVisible(true);
            }
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
        
        // Initialize ComboBoxes
        dayComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        yearComboBox = new JComboBox<>();
        
        // Populate days
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(String.format("%02d", i));
        }
        
        // Populate months
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(String.format("%02d", i));
        }
        
        // Populate years (100 years back from current year)
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 100; i--) {
            yearComboBox.addItem(String.valueOf(i));
        }
        
        // Add components to panel
        add(dayComboBox);
        add(Box.createHorizontalStrut(5));
        add(monthComboBox);
        add(Box.createHorizontalStrut(5));
        add(yearComboBox);
        
        // Set default date format
        dateFormatString = "dd/MM/yyyy";
    }
    
    public void setDateFormatString(String format) {
        this.dateFormatString = format;
    }
    
    public void setFont(Font font) {
        if (dayComboBox != null) {
            dayComboBox.setFont(font);
            monthComboBox.setFont(font);
            yearComboBox.setFont(font);
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