/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class TonKho {
    private String maTK;
    private String tenSP;
    private double giaN;
    private double giaB;
    private int soLuong;
    private int tonKho;
    private double tongCPN;
    private String tg;
     private String ghiChu;

    public TonKho() {
    }

    public TonKho(String maTK, String tenSP, double giaN, double giaB, int soLuong, int tonKho, double tongCPN, String tg, String ghiChu) {
        this.maTK = maTK;
        this.tenSP = tenSP;
        this.giaN = giaN;
        this.giaB = giaB;
        this.soLuong = soLuong;
        this.tonKho = tonKho;
        this.tongCPN = tongCPN;
        this.tg = tg;
        this.ghiChu = ghiChu;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaN() {
        return giaN;
    }

    public void setGiaN(double giaN) {
        this.giaN = giaN;
    }

    public double getGiaB() {
        return giaB;
    }

    public void setGiaB(double giaB) {
        this.giaB = giaB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public double getTongCPN() {
        return tongCPN;
    }

    public void setTongCPN(double tongCPN) {
        this.tongCPN = tongCPN;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

  
}
