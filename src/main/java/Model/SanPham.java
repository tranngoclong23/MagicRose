/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class SanPham {

    String maSP, tenSP, Hang, loaiSP, phanLoai;
    int Size;
    double donGia;
    double giaN;
    int soLuong;
    double giaB;
    double tongCPN;
    
    double chiPhi;
    String tg;
    String moTa;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String Hang, String loaiSP, String phanLoai, int Size, double donGia, double giaN, int soLuong, double giaB, double tongCPN, double chiPhi, String tg, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.Hang = Hang;
        this.loaiSP = loaiSP;
        this.phanLoai = phanLoai;
        this.Size = Size;
        this.donGia = donGia;
        this.giaN = giaN;
        this.soLuong = soLuong;
        this.giaB = giaB;
        this.tongCPN = tongCPN;
        this.chiPhi = chiPhi;
        this.tg = tg;
        this.moTa = moTa;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getGiaN() {
        return giaN;
    }

    public void setGiaN(double giaN) {
        this.giaN = giaN;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaB() {
        return giaB;
    }

    public void setGiaB(double giaB) {
        this.giaB = giaB;
    }

    public double getTongCPN() {
        return tongCPN;
    }

    public void setTongCPN(double tongCPN) {
        this.tongCPN = tongCPN;
    }

    public double getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(double chiPhi) {
        this.chiPhi = chiPhi;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

   
}
