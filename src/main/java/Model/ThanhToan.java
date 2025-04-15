/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ThanhToan {

    private int maHD;
    private int maKH;
    private String sdtKH;
    private String maSP;
    private String spDaMua;
    private int soLuong;
    private String thoiGianMua;
    private String phuongThucTT;
    private double tongTienTT;
    private double tienDaNhan;
    private double tienThua;
    private String maNV;

    public ThanhToan() {
    }

    public ThanhToan(int maHD, int maKH, String sdtKH, String maSP, String spDaMua, int soLuong, String thoiGianMua, String phuongThucTT, double tongTienTT, double tienDaNhan, double tienThua, String maNV) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.sdtKH = sdtKH;
        this.maSP = maSP;
        this.spDaMua = spDaMua;
        this.soLuong = soLuong;
        this.thoiGianMua = thoiGianMua;
        this.phuongThucTT = phuongThucTT;
        this.tongTienTT = tongTienTT;
        this.tienDaNhan = tienDaNhan;
        this.tienThua = tienThua;
        this.maNV = maNV;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getSpDaMua() {
        return spDaMua;
    }

    public void setSpDaMua(String spDaMua) {
        this.spDaMua = spDaMua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThoiGianMua() {
        return thoiGianMua;
    }

    public void setThoiGianMua(String thoiGianMua) {
        this.thoiGianMua = thoiGianMua;
    }

    public String getPhuongThucTT() {
        return phuongThucTT;
    }

    public void setPhuongThucTT(String phuongThucTT) {
        this.phuongThucTT = phuongThucTT;
    }

    public double getTongTienTT() {
        return tongTienTT;
    }

    public void setTongTienTT(double tongTienTT) {
        this.tongTienTT = tongTienTT;
    }

    public double getTienDaNhan() {
        return tienDaNhan;
    }

    public void setTienDaNhan(double tienDaNhan) {
        this.tienDaNhan = tienDaNhan;
    }

    public double getTienThua() {
        return tienThua;
    }

    public void setTienThua(double tienThua) {
        this.tienThua = tienThua;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

   
    
    
}