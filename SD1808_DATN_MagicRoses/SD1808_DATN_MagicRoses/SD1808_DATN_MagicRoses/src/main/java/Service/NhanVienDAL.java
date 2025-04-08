/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DataProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.NhanVien;
import Model.SanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAL {

    public boolean themNV(NhanVien nv) {
        String sql = "Insert into NHANVIEN (MaNV, TenNV, GioiTinh, SoDT, Email, Thang, Nam, Luong) values(?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            Connection conn1 = DataProvider.GetDataConnection();
            PreparedStatement preStm1 = conn1.prepareStatement(sql);
            preStm1.setString(1, nv.getMaNV());
            preStm1.setString(2, nv.getTenNV());
            preStm1.setString(3, nv.getGioiTinh());
            preStm1.setString(4, nv.getSoDT());
            preStm1.setString(5, nv.getEmail());
            preStm1.setDouble(6, nv.getThang());
            preStm1.setDouble(7, nv.getNam());
            preStm1.setDouble(8, nv.getLuong());
            return preStm1.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaNV(NhanVien nv) {
        String sql = "update NHANVIEN set TenNV = ?, GioiTinh = ?, SoDT = ?, Email = ?,Thang = ?, Nam = ?, Luong = ? where MaNV = ?";
        try {
            Connection conn2 = DataProvider.GetDataConnection();
            PreparedStatement preStm2 = conn2.prepareStatement(sql);
            preStm2.setString(1, nv.getTenNV());
            preStm2.setString(2, nv.getGioiTinh());
            preStm2.setString(3, nv.getSoDT());
            preStm2.setString(4, nv.getEmail());
            preStm2.setDouble(5, nv.getThang());
            preStm2.setDouble(6, nv.getNam());
            preStm2.setDouble(7, nv.getLuong());
            preStm2.setString(8, nv.getMaNV());
            return preStm2.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaNV(String maNV) {
        String sql = "Delete from NHANVIEN where MaNV = ?";
        try {
            Connection conn3 = DataProvider.GetDataConnection();
            PreparedStatement preStm3 = conn3.prepareStatement(sql);
            preStm3.setString(1, maNV);

            return preStm3.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public List<NhanVien> timNV(String maNV) {
        String sql = "Select * from NHANVIEN where MaNV = ?";
        try {
            Connection conn4 = DataProvider.GetDataConnection();
            PreparedStatement preStm4 = conn4.prepareStatement(sql);
            preStm4.setString(1, maNV);
            List<NhanVien> nvList = new ArrayList<>();
            ResultSet rs = preStm4.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSoDT(rs.getString("SoDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setThang(Double.parseDouble(rs.getString("Thang")));
                nv.setNam(Double.parseDouble(rs.getString("Nam")));
                nv.setLuong(Double.parseDouble(rs.getString("Luong")));
                nvList.add(nv);
            }
            return nvList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public static double tinhLuongNVtheothang(String maNV, String thang, String nam){
        double Luong = 0;
        try {
            String sql = "select (6000000+(SUM(TongTienTT)*0.04)) from THANHTOAN where MaNV= ? and MONTH(ThoiGian) = ? and YEAR(ThoiGian)=? ";
            Connection conn10 = DataProvider.GetDataConnection();
            PreparedStatement ps = conn10.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, thang);
            ps.setString(3, nam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Luong = rs.getDouble(1);
            }
            rs.close();
            ps.close();
            conn10.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Luong;
    }
    public static List<NhanVien> findAll() {
        String sql = "select * from NHANVIEN";
        try {
            Connection con = DataProvider.GetDataConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            List<NhanVien> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSoDT(rs.getString("SoDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setThang(Double.parseDouble(rs.getString("Thang")));
                nv.setNam(Double.parseDouble(rs.getString("Nam")));
                nv.setLuong(Double.parseDouble(rs.getString("Luong")));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NhanVien findByID(String ma) {
        String sql = "select * from NHANVIEN where MaNV = ?";
        NhanVien nv = new NhanVien();
        try {
            Connection con = DataProvider.GetDataConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            List<NhanVien> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSoDT(rs.getString("SoDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setThang(Double.parseDouble(rs.getString("Thang")));
                nv.setNam(Double.parseDouble(rs.getString("Nam")));
                nv.setLuong(Double.parseDouble(rs.getString("Luong")));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
}
