/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class DoanhThu {

    private String maDT;
    private double DT, ChiPhi, LoiNhuan;
    private int SoDon;
    private String thang, nam;

    public DoanhThu() {
    }

    public DoanhThu(String maDT, double DT, double ChiPhi, double LoiNhuan, int SoDon, String thang, String nam) {
        this.maDT = maDT;
        this.DT = DT;
        this.ChiPhi = ChiPhi;
        this.LoiNhuan = LoiNhuan;
        this.SoDon = SoDon;
        this.thang = thang;
        this.nam = nam;
    }

    

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public double getDT() {
        return DT;
    }

    public void setDT(double DT) {
        this.DT = DT;
    }

    public double getChiPhi() {
        return ChiPhi;
    }

    public void setChiPhi(double ChiPhi) {
        this.ChiPhi = ChiPhi;
    }

    public double getLoiNhuan() {
        return LoiNhuan;
    }

    public void setLoiNhuan(double LoiNhuan) {
        this.LoiNhuan = LoiNhuan;
    }

    public int getSoDon() {
        return SoDon;
    }

    public void setSoDon(int SoDon) {
        this.SoDon = SoDon;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    

}
