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
    private int tonKho;
    private String ghiChu;

    public TonKho() {
    }

    public TonKho(String maTK, String tenSP, int tonKho, String ghiChu) {
        this.maTK = maTK;
        this.tenSP = tenSP;
        this.tonKho = tonKho;
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

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    

  
}
