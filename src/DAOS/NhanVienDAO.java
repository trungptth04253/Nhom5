package DAOS;
import Models.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Data Access Object for NhanVien (Employee) table
 * @author Admin
 */
public class NhanVienDAO {
   static String connectionString = "jdbc:sqlserver://LAPTOP-4K0UOUJE\\MSSQLSERVER02;databaseName=QuanLyDatPhongKhachSan;user=sa;password=123;trustServerCertificate=true";
    static String getAll = "select * from NhanVien";
    static String createNV = "insert into NhanVien (ID, Ten, CaLam, SDT, Email, ChucVu) values (?, ?, ?, ?, ?, ?)";
    static String updateKH = "UPDATE NhanVien SET Ten = ?, CaLam = ?, SDT = ?, Email = ?, ChucVu = ?, ID = ?";
    static String deleteKH = "delete from NhanVien where ID = ? ";
    static String searchKH = "select * from NhanVien where Ten like ? or ChucVu like ?";

    public static ArrayList<NhanVien> getAllNV() {
        ArrayList<NhanVien> nhanviens = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement stm = con.prepareStatement(getAll);
            ResultSet rs = stm.executeQuery();
            // Tu rs tra ve ArrayList
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setID(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setCaLam(rs.getString(3));
                nv.setSDT(rs.getInt(4));
                nv.setEmail(rs.getString(5));
                nv.setChucVu(rs.getString(6));
                nhanviens.add(nv);
            }
            return nhanviens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Trong KhachHangDAO.java, sửa phương thức addKH:
    public static boolean addNV(String Ten, String CaLam, int SDT, String Email, String ChucVu) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            // Bỏ ID vì đó là trường tự động tăng
            String sql = "insert into KhachHang (Ten, CaLam, SDT, Email, ChucVu) values (?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, Ten);
            stm.setString(2, CaLam); // Chuyển đổi đúng cách
            stm.setInt(6, SDT);
            stm.setString(7, Email);
            stm.setString(8, ChucVu);

            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
            return false;
        }
    }

    public static boolean suaNV(int ID, String Ten, String CaLam, int SDT, String Email, String ChucVu) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            String sql = "UPDATE NhanVien SET Ten = ?, CaLam = ?, SDT = ?, Email = ?, ChucVu = ? WHERE ID = ?";
            PreparedStatement stm = con.prepareStatement(sql);

            // Thêm dữ liệu vào câu lệnh SQL
            stm.setString(1, Ten);
            stm.setString(2, CaLam);
            stm.setInt(3, SDT);
            stm.setString(4, Email);
            stm.setString(5, ChucVu);
            stm.setInt(6, ID);

            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaNV(int ID) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement stm = con.prepareStatement(deleteKH);
            //Dien du lieu cho statement
            stm.setInt(1, ID);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
        }
        return false;
    }

}
