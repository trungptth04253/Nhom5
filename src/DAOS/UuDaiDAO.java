/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Models.UuDai;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class UuDaiDAO {

    static String connectionString = "jdbc:sqlserver://LAPTOP-4K0UOUJE\\MSSQLSERVER02;databaseName=QuanLyDatPhongKhachSan;user=sa;password=123;trustServerCertificate=true";
    static String getAll = "select * from UuDai";
    static String createUD = "INSERT INTO UuDai (ID, ten, loai, giaUuDai, moTa) VALUES (?, ?, ?, ?, ?)";
    static String updateUD = "UPDATE UuDai SET ten = ?, loai = ?, giaUuDai = ?, moTa = ? WHERE ID = ?";
    static String deleteUD = "DELETE FROM UuDai WHERE ID = ?";

    public static ArrayList<UuDai> getAllUD() {
        ArrayList<UuDai> uudais = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement stm = con.prepareStatement(getAll);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UuDai ud = new UuDai();
                ud.setID(rs.getInt(1));
                ud.setTen(rs.getString(2));
                ud.setLoai(rs.getString(3));
                ud.setGiaPhong(rs.getInt(4));
                ud.setMota(rs.getString(5));
                uudais.add(ud);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addUD(String ten, String loai, int giaPhong, String mota) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            // Bỏ ID vì đó là trường tự động tăng
            String sql = "UPDATE UuDai SET ten = ?, loai = ?, giaPhong = ?, mota = ? WHERE ID = ?";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, ten);
            stm.setString(2, loai);
            stm.setInt(3, giaPhong);
            stm.setString(4, mota);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
            return false;
        }
    }

    public static boolean suaUD(String ten, String loai, int giaPhong, String mota) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            String sql = "UPDATE UuDai SET ten = ?, loai = ?, giaPhong = ?, mota = ? WHERE ID = ?";
            PreparedStatement stm = con.prepareStatement(sql);

            // Thêm dữ liệu vào câu lệnh SQL
            stm.setString(1, ten);
            stm.setString(2, loai);
            stm.setInt(3, giaPhong);
            stm.setString(4, mota);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaUD(int ID) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement stm = con.prepareStatement(deleteUD);
            //Dien du lieu cho statement
            stm.setInt(1, ID);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
        }
        return false;
    }

}

