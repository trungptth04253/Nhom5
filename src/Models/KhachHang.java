package Models;

import java.util.Date;

public class KhachHang {
    private int ID;
    private String Ten;
    private Date NgaySinh;
    private String GioiTinh;
    private String SDT;
    private String Email;
    private String MatKhau;
    
    public KhachHang() {
    }
    
    // Constructor đã xóa Tuoi và CCCD
    public KhachHang(int ID, String Ten, Date NgaySinh, String GioiTinh, 
                   String SDT, String Email, String MatKhau) {
        this.ID = ID;
        this.Ten = Ten;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.MatKhau = MatKhau;
    }

    // Các getter/setter cho Tuoi và CCCD ĐÃ ĐƯỢC XÓA
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

    public String getSĐT() {
        return SDT;
    }

    public void setSĐT(String SDT) {
        this.SDT = SDT;
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
    
    @Override
    public String toString() {
        return "KhachHang{" + 
               "ID=" + ID + 
               ", Ten=" + Ten + 
               ", NgaySinh=" + NgaySinh + 
               ", GioiTinh=" + GioiTinh + 
               ", SDT=" + SDT + 
               ", Email=" + Email + '}';
    }
}