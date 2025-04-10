/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Models.KhachHang;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHangDAO {

    static String connectionString = "jdbc:sqlserver://LAPTOP-4K0UOUJE\\MSSQLSERVER02;databaseName=QuanLyDatPhongKhachSan;user=sa;password=123;trustServerCertificate=true";
    static String getAll = "select * from KhachHang";
    static String createKH = "insert into KhachHang (ID, Ten, NgaySinh, GioiTinh, Tuoi, CCCD, SDT, Email, MatKhau) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    static String updateKH = "UPDATE KhachHang SET Ten = ?, NgaySinh = ?, GioiTinh = ?, Tuoi = ?, CCCD = ?, SDT = ?, Email = ?, MatKhau = ? WHERE ID = ?";
    static String deleteKH = "delete from KhachHang where ID = ? ";
    static String searchKH = "select * from KhachHang where Ten like ? or Tuoi like ?";

    public static ArrayList<KhachHang> getAllKH() {
        ArrayList<KhachHang> khachhangs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement stm = con.prepareStatement(getAll);
            ResultSet rs = stm.executeQuery();
            // Tu rs tra ve ArrayList
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setNgaySinh(rs.getDate(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setTuoi(rs.getInt(5));
                kh.setCCCD(rs.getInt(6));
                kh.setSĐT(rs.getInt(7));
                kh.setEmail(rs.getString(8));
                kh.setMatKhau(rs.getString(9));
                khachhangs.add(kh);
            }
            return khachhangs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Trong KhachHangDAO.java, sửa phương thức addKH:
    public static boolean addKH(String Ten, Date NgaySinh, String GioiTinh, int Tuoi, int CCCD, int SDT, String Email, String MatKhau) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            // Bỏ ID vì đó là trường tự động tăng
            String sql = "insert into KhachHang (Ten, NgaySinh, GioiTinh, Tuoi, CCCD, SDT, Email, MatKhau) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, Ten);
            stm.setDate(2, new java.sql.Date(NgaySinh.getTime())); // Chuyển đổi đúng cách
            stm.setString(3, GioiTinh);
            stm.setInt(4, Tuoi);
            stm.setInt(5, CCCD);
            stm.setInt(6, SDT);
            stm.setString(7, Email);
            stm.setString(8, MatKhau);

            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
            return false;
        }
    }

    public static boolean suaKH(int ID, String Ten, Date NgaySinh, String GioiTinh, int Tuoi, int CCCD, int SDT, String Email, String MatKhau) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            String sql = "UPDATE KhachHang SET Ten = ?, NgaySinh = ?, GioiTinh = ?, Tuoi = ?, CCCD = ?, SDT = ?, Email = ?, MatKhau = ? WHERE ID = ?";
            PreparedStatement stm = con.prepareStatement(sql);

            // Thêm dữ liệu vào câu lệnh SQL
            stm.setString(1, Ten);
            stm.setDate(2, new java.sql.Date(NgaySinh.getTime()));
            stm.setString(3, GioiTinh);
            stm.setInt(4, Tuoi);
            stm.setInt(5, CCCD);
            stm.setInt(6, SDT);
            stm.setString(7, Email);
            stm.setString(8, MatKhau);
            stm.setInt(9, ID);

            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaSP(int ID) {
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
