package DAOS;

import Models.KhachHang;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO {
    
    private static final Logger LOGGER = Logger.getLogger(KhachHangDAO.class.getName());
    private static final String DB_PATH = "khachhang.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_PATH;
    
    // SQL tạo bảng (ĐÃ XÓA CCCD VÀ TUỔI)
    private static final String CREATE_TABLE = 
            "CREATE TABLE IF NOT EXISTS KhachHang (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Ten TEXT NOT NULL, " +
            "NgaySinh TEXT NOT NULL, " +
            "GioiTinh TEXT NOT NULL, " +
            "SDT TEXT NOT NULL, " +
            "Email TEXT NOT NULL UNIQUE, " +
            "MatKhau TEXT NOT NULL)";
    
    // Các câu lệnh SQL
    private static final String GET_ALL = "SELECT * FROM KhachHang";
    private static final String CREATE_KH = 
            "INSERT INTO KhachHang (Ten, NgaySinh, GioiTinh, SDT, Email, MatKhau) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_KH = 
            "UPDATE KhachHang SET Ten = ?, NgaySinh = ?, GioiTinh = ?, SDT = ?, Email = ?, MatKhau = ? WHERE ID = ?";
    private static final String DELETE_KH = "DELETE FROM KhachHang WHERE ID = ?";
    private static final String SEARCH_KH = "SELECT * FROM KhachHang WHERE Ten LIKE ?";
    private static final String GET_BY_EMAIL = "SELECT * FROM KhachHang WHERE Email = ?";

    // Khởi tạo database
    static {
        initializeDatabase();
    }
    
    private static void initializeDatabase() {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(CREATE_TABLE);
            LOGGER.info("Database initialized");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization failed", e);
        }
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "SQLite driver missing", e);
        }
        return DriverManager.getConnection(CONNECTION_STRING);
    }
    
    private static void closeResources(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error closing resources", e);
        }
    }

    // =================== CRUD OPERATIONS =================== //
    
    // Thêm khách hàng mới (ĐÃ XÓA CCCD VÀ TUỔI)
    public static boolean addKH(String Ten, Date NgaySinh, String GioiTinh, String SDT, String Email, String MatKhau) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = getConnection();
            
            // Kiểm tra trùng email
            if (emailExists(Email, con)) {
                LOGGER.log(Level.INFO, "Email already exists: {0}", Email);
                return false;
            }
            
            stmt = con.prepareStatement(CREATE_KH);
            stmt.setString(1, Ten);
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(NgaySinh)); 
            stmt.setString(3, GioiTinh);
            stmt.setString(4, SDT);
            stmt.setString(5, Email);
            stmt.setString(6, MatKhau);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding customer", e);
            return false;
        } finally {
            closeResources(con, stmt, null);
        }
    }

    // Lấy tất cả khách hàng
    public static ArrayList<KhachHang> getAllKH() {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            stmt = con.prepareStatement(GET_ALL);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID(rs.getInt("ID"));
                kh.setTen(rs.getString("Ten"));
                kh.setNgaySinh(new Date(rs.getDate("NgaySinh").getTime()));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSĐT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setMatKhau(rs.getString("MatKhau"));
                khachHangs.add(kh);
            }
            
            return khachHangs;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving customers", e);
            return null;
        } finally {
            closeResources(con, stmt, rs);
        }
    }

    // Cập nhật thông tin khách hàng
    public static boolean suaKH(int ID, String Ten, Date NgaySinh, String GioiTinh, String SDT, String Email, String MatKhau) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = getConnection();
            stmt = con.prepareStatement(UPDATE_KH);
            
            stmt.setString(1, Ten);
            stmt.setString(2, new java.sql.Date(NgaySinh.getTime()).toString());
            stmt.setString(3, GioiTinh);
            stmt.setString(4, SDT);
            stmt.setString(5, Email);
            stmt.setString(6, MatKhau);
            stmt.setInt(7, ID);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating customer", e);
            return false;
        } finally {
            closeResources(con, stmt, null);
        }
    }

    // Xóa khách hàng
    public static boolean xoaKH(int ID) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = getConnection();
            stmt = con.prepareStatement(DELETE_KH);
            stmt.setInt(1, ID);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting customer", e);
            return false;
        } finally {
            closeResources(con, stmt, null);
        }
    }

    // Tìm kiếm theo tên (ĐÃ XÓA TÌM THEO TUỔI)
    public static ArrayList<KhachHang> searchKH(String searchText) {
        ArrayList<KhachHang> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            stmt = con.prepareStatement(SEARCH_KH);
            stmt.setString(1, "%" + searchText + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID(rs.getInt("ID"));
                kh.setTen(rs.getString("Ten"));
                kh.setNgaySinh(new Date(rs.getDate("NgaySinh").getTime()));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSĐT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setMatKhau(rs.getString("MatKhau"));
                results.add(kh);
            }
            
            return results;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching customers", e);
            return null;
        } finally {
            closeResources(con, stmt, rs);
        }
    }

    // Lấy khách hàng bằng email (cho chức năng đăng nhập)
    public static KhachHang getByEmail(String email) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            stmt = con.prepareStatement(GET_BY_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID(rs.getInt("ID"));
                kh.setTen(rs.getString("Ten"));

                // Sửa phần xử lý ngày sinh
                String ngaySinhStr = rs.getString("NgaySinh");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ngaySinh = sdf.parse(ngaySinhStr);
                kh.setNgaySinh(ngaySinh);

                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSĐT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setMatKhau(rs.getString("MatKhau"));
                return kh;
            }
            return null;
        } catch (SQLException | ParseException e) { // Thêm ParseException
            LOGGER.log(Level.SEVERE, "Error getting customer by email", e);
            return null;
        } finally {
            closeResources(con, stmt, rs);
        }
    }

    // Kiểm tra email tồn tại
    private static boolean emailExists(String email, Connection con) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(GET_BY_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking email existence", e);
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing resources", e);
            }
        }
    }
}