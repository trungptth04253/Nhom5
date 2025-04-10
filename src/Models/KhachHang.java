/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHang {
    int ID;
    String Ten;
    Date NgaySinh;
    String GioiTinh;
    int Tuoi;
    int CCCD;
    int SĐT;
    String Email;
    String MatKhau;

    public KhachHang() {
    }

    public KhachHang(int ID, String Ten, Date NgaySinh, String GioiTinh, int Tuoi, int CCCD, int SĐT, String Email, String MatKhau) {
        this.ID = ID;
        this.Ten = Ten;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Tuoi = Tuoi;
        this.CCCD = CCCD;
        this.SĐT = SĐT;
        this.Email = Email;
        this.MatKhau = MatKhau;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int Tuoi) {
        this.Tuoi = Tuoi;
    }

    public int getCCCD() {
        return CCCD;
    }

    public void setCCCD(int CCCD) {
        this.CCCD = CCCD;
    }

    public int getSĐT() {
        return SĐT;
    }

    public void setSĐT(int SĐT) {
        this.SĐT = SĐT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

   
}
