package Form;

import DAOS.KhachHangDAO;
import Models.KhachHang;
import grand.pkgfinal.CustomerManagement;
import grand.pkgfinal.HotelBookingUI;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class LoginSign extends JFrame {

    // Login components
    private JPanel loginPanel;
    private JLabel lblTitle, lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnForgotPassword, btnRegister;
    private JCheckBox chkIsHuman;

    // Registration components
    private JPanel registerPanel;
    private JLabel lblRegisterTitle, lblRegUsername, lblRegDOB, lblRegAge;
    private JLabel lblRegGender, lblRegID, lblRegPhone, lblRegEmail, lblRegPassword;
    private JTextField txtRegUsername, txtRegDOB, txtRegAge, txtRegID, txtRegPhone, txtRegEmail;
    private JPasswordField txtRegPassword;
    private JCheckBox chkRegIsHuman;
    private JCheckBox chkMale, chkFemale;
    private JButton btnRegisterSubmit;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public LoginSign() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Khách Sạn REVUER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Initialize login panel
        initLoginPanel();

        // Initialize registration panel
        initRegisterPanel();

        // Add panels to main panel with card layout
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");

        // Show login panel by default
        cardLayout.show(mainPanel, "login");

        // Add main panel to frame
        getContentPane().add(mainPanel);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(242, 242, 242));

        // Login title
        lblTitle = new JLabel("Đăng Nhập");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTitle.setForeground(new Color(255, 51, 51));
        lblTitle.setBounds(270, 30, 150, 30);
        loginPanel.add(lblTitle);

        // Username label and textfield
        lblUsername = new JLabel("Tên đăng nhập");
        lblUsername.setBounds(18, 110, 100, 20);
        loginPanel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(140, 110, 300, 25);
        loginPanel.add(txtUsername);

        // Password label and textfield
        lblPassword = new JLabel("Mật khẩu");
        lblPassword.setBounds(18, 180, 100, 20);
        loginPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 180, 300, 25);
        loginPanel.add(txtPassword);

        // Checkbox
        chkIsHuman = new JCheckBox("Xác nhận bạn là con người");
        chkIsHuman.setBounds(150, 230, 200, 20);
        chkIsHuman.setBackground(new Color(242, 242, 242));
        loginPanel.add(chkIsHuman);

        // Login button
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(106, 273, 100, 25);
        btnLogin.addActionListener((ActionEvent e) -> {
            login();
        });
        loginPanel.add(btnLogin);

        // Forgot password button
        btnForgotPassword = new JButton("Quên mật khẩu?");
        btnForgotPassword.setBounds(230, 273, 150, 25);
        loginPanel.add(btnForgotPassword);

        // Register link
        JLabel lblNoAccount = new JLabel("Chưa có tài khoản?");
        lblNoAccount.setBounds(77, 327, 150, 20);
        loginPanel.add(lblNoAccount);

        btnRegister = new JButton("Đăng ký");
        btnRegister.setBounds(265, 327, 100, 25);
        btnRegister.setForeground(Color.RED);
        btnRegister.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "register");
        });
        loginPanel.add(btnRegister);
    }

    private void initRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setLayout(null);
        registerPanel.setBackground(new Color(242, 242, 242));

        // Register title
        lblRegisterTitle = new JLabel("Khách Sạn REVUER");
        lblRegisterTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblRegisterTitle.setForeground(new Color(0, 0, 255));
        lblRegisterTitle.setBounds(230, 20, 200, 30);
        registerPanel.add(lblRegisterTitle);

        JLabel lblRegisterSubtitle = new JLabel("Đăng ký tài khoản");
        lblRegisterSubtitle.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblRegisterSubtitle.setBounds(250, 50, 150, 20);
        registerPanel.add(lblRegisterSubtitle);

        // Username
        lblRegUsername = new JLabel("Tên đăng nhập");
        lblRegUsername.setBounds(50, 90, 100, 20);
        registerPanel.add(lblRegUsername);

        txtRegUsername = new JTextField();
        txtRegUsername.setBounds(265, 90, 300, 25);
        registerPanel.add(txtRegUsername);

        // Date of Birth
        lblRegDOB = new JLabel("Ngày Sinh");
        lblRegDOB.setBounds(50, 130, 100, 20);
        registerPanel.add(lblRegDOB);

        txtRegDOB = new JTextField();
        txtRegDOB.setBounds(265, 130, 300, 25);
        registerPanel.add(txtRegDOB);

        // Age
        lblRegAge = new JLabel("Tuổi");
        lblRegAge.setBounds(50, 170, 100, 20);
        registerPanel.add(lblRegAge);

        txtRegAge = new JTextField();
        txtRegAge.setBounds(265, 170, 300, 25);
        registerPanel.add(txtRegAge);

        // Gender
        lblRegGender = new JLabel("Giới tính");
        lblRegGender.setBounds(50, 210, 100, 20);
        registerPanel.add(lblRegGender);

        chkMale = new JCheckBox("Nam");
        chkMale.setBounds(265, 210, 70, 20);
        chkMale.setBackground(new Color(242, 242, 242));
        chkMale.addActionListener((ActionEvent e) -> {
            if (chkMale.isSelected()) {
                chkFemale.setSelected(false);
            }
        });
        registerPanel.add(chkMale);

        chkFemale = new JCheckBox("Nữ");
        chkFemale.setBounds(350, 210, 70, 20);
        chkFemale.setBackground(new Color(242, 242, 242));
        chkFemale.addActionListener((ActionEvent e) -> {
            if (chkFemale.isSelected()) {
                chkMale.setSelected(false);
            }
        });
        registerPanel.add(chkFemale);

        // ID Card
        lblRegID = new JLabel("CCCD");
        lblRegID.setBounds(50, 250, 100, 20);
        registerPanel.add(lblRegID);

        txtRegID = new JTextField();
        txtRegID.setBounds(265, 250, 300, 25);
        registerPanel.add(txtRegID);

        // Phone
        lblRegPhone = new JLabel("Số điện thoại");
        lblRegPhone.setBounds(50, 290, 100, 20);
        registerPanel.add(lblRegPhone);

        txtRegPhone = new JTextField();
        txtRegPhone.setBounds(265, 290, 300, 25);
        registerPanel.add(txtRegPhone);

        // Email
        lblRegEmail = new JLabel("Email");
        lblRegEmail.setBounds(50, 330, 100, 20);
        registerPanel.add(lblRegEmail);

        txtRegEmail = new JTextField();
        txtRegEmail.setBounds(265, 330, 300, 25);
        registerPanel.add(txtRegEmail);

        // Password
        lblRegPassword = new JLabel("Mật khẩu");
        lblRegPassword.setBounds(50, 370, 100, 20);
        registerPanel.add(lblRegPassword);

        txtRegPassword = new JPasswordField();
        txtRegPassword.setBounds(265, 370, 300, 25);
        registerPanel.add(txtRegPassword);

        // Checkbox
        chkRegIsHuman = new JCheckBox("Xác nhận bạn là con người?");
        chkRegIsHuman.setBounds(265, 400, 200, 20);
        chkRegIsHuman.setBackground(new Color(242, 242, 242));
        registerPanel.add(chkRegIsHuman);

        // Register button
        btnRegisterSubmit = new JButton("Đăng ký");
        btnRegisterSubmit.setBounds(430, 430, 100, 25);
        btnRegisterSubmit.setForeground(Color.RED);
        btnRegisterSubmit.addActionListener((ActionEvent e) -> {
            register();
        });
        registerPanel.add(btnRegisterSubmit);

        // Back to login button
        JButton btnBackToLogin = new JButton("← Quay lại đăng nhập");
        btnBackToLogin.setBounds(50, 430, 200, 25);
        btnBackToLogin.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "login");
        });
        registerPanel.add(btnBackToLogin);
    }

    private void login() {
        String email = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        // Validate input
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!chkIsHuman.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng xác nhận bạn là con người!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get all customers
        ArrayList<KhachHang> customers = KhachHangDAO.getAllKH();
        boolean isValidUser = false;

        // Check credentials
        for (KhachHang customer : customers) {
            if (customer.getEmail().equals(email) && customer.getMatKhau().equals(password)) {
                isValidUser = true;
                break;
            }
        }

        if (isValidUser) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            // Open the HotelBookingUI instead of CustomerManagement
            HotelBookingUI.main(null); // This will start the hotel booking UI
            this.dispose(); // Close the login window
        } else {
            JOptionPane.showMessageDialog(this, "Email hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void register() {
        // Validate fields
        if (!validateRegisterForm()) {
            return;
        }

        String name = txtRegUsername.getText().trim();
        Date dob;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dob = sdf.parse(txtRegDOB.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String gender = chkMale.isSelected() ? "Nam" : "Nữ";
        int age = Integer.parseInt(txtRegAge.getText().trim());
        int cccd = Integer.parseInt(txtRegID.getText().trim());
        int phone = Integer.parseInt(txtRegPhone.getText().trim());
        String email = txtRegEmail.getText().trim();
        String password = new String(txtRegPassword.getPassword());

        // Call DAO to add customer
        boolean success = KhachHangDAO.addKH(name, dob, gender, age, cccd, phone, email, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công! Vui lòng đăng nhập để tiếp tục.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearRegisterForm();
            cardLayout.show(mainPanel, "login");
        } else {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateRegisterForm() {
        // Check if all fields are filled
        if (txtRegUsername.getText().trim().isEmpty()
                || txtRegDOB.getText().trim().isEmpty()
                || txtRegAge.getText().trim().isEmpty()
                || (!chkMale.isSelected() && !chkFemale.isSelected())
                || txtRegID.getText().trim().isEmpty()
                || txtRegPhone.getText().trim().isEmpty()
                || txtRegEmail.getText().trim().isEmpty()
                || new String(txtRegPassword.getPassword()).isEmpty()) {

            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate date format
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(txtRegDOB.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate age
        try {
            int age = Integer.parseInt(txtRegAge.getText().trim());
            if (age <= 0 || age > 120) {
                JOptionPane.showMessageDialog(this, "Tuổi không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate CCCD
        try {
            int cccd = Integer.parseInt(txtRegID.getText().trim());
            if (txtRegID.getText().trim().length() < 9) {
                JOptionPane.showMessageDialog(this, "CCCD không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "CCCD phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate phone number
        try {
            int phone = Integer.parseInt(txtRegPhone.getText().trim());
            if (txtRegPhone.getText().trim().length() < 9) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.matches(emailRegex, txtRegEmail.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate password
        if (new String(txtRegPassword.getPassword()).length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate human check
        if (!chkRegIsHuman.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng xác nhận bạn là con người!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void clearRegisterForm() {
        txtRegUsername.setText("");
        txtRegDOB.setText("");
        txtRegAge.setText("");
        chkMale.setSelected(false);
        chkFemale.setSelected(false);
        txtRegID.setText("");
        txtRegPhone.setText("");
        txtRegEmail.setText("");
        txtRegPassword.setText("");
        chkRegIsHuman.setSelected(false);
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
                new LoginSign();
            }
        });
    }
}
