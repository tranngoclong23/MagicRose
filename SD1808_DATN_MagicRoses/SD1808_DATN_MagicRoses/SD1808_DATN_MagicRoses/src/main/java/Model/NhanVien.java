/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class NhanVien {

    String maNV, tenNV, gioiTinh, soDT, email;
    double thang,nam;
    double luong;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String gioiTinh, String soDT, String email, double thang, double nam, double luong) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.thang = thang;
        this.nam = nam;
        this.luong = luong;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getThang() {
        return thang;
    }

    public void setThang(double thang) {
        this.thang = thang;
    }

    public double getNam() {
        return nam;
    }

    public void setNam(double nam) {
        this.nam = nam;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

   
}
