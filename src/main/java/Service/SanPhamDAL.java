/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.SanPham;
import Model.DataProvider;
import Model.KhachHang;
import static Service.KhachHangDAL.url;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class SanPhamDAL {

    public boolean insertSP(SanPham sp) {
        String sql = "insert into SANPHAM(TenSP, Hang, LoaiSP, PhanLoai, Size, GiaNhap, SoLuong, GiaBan, TongCPNH, ThoiGian, MoTa) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            Connection conn1 = DataProvider.GetDataConnection();
            PreparedStatement preStm1 = conn1.prepareStatement(sql);

            preStm1.setString(1, sp.getTenSP());
            preStm1.setString(2, sp.getHang());
            preStm1.setString(3, sp.getLoaiSP());
            preStm1.setString(4, sp.getPhanLoai());
            preStm1.setInt(5, sp.getSize());
            preStm1.setDouble(6, sp.getGiaN());
            preStm1.setInt(7, sp.getSoLuong());
            preStm1.setDouble(8, sp.getGiaB());
            preStm1.setDouble(9, sp.getTongCPN());
            preStm1.setString(10, sp.getTg());
            preStm1.setString(11, sp.getMoTa());
            return preStm1.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSP(SanPham sp) {
        String sql = "Update SANPHAM set TenSP=?, Hang=?, LoaiSP=?, PhanLoai=?, Size=?, GiaNhap=?,  SoLuong=?, GiaBan=?, TongCPNH=?, ThoiGian=?, MoTa=? where MaSP=?";
        try {
            Connection conn2 = DataProvider.GetDataConnection();
            PreparedStatement preStm2 = conn2.prepareStatement(sql);
            preStm2.setString(1, sp.getTenSP());
            preStm2.setString(2, sp.getHang());
            preStm2.setString(3, sp.getLoaiSP());
            preStm2.setString(4, sp.getPhanLoai());
            preStm2.setInt(5, sp.getSize());
            preStm2.setDouble(6, sp.getGiaN());
             preStm2.setInt(7, sp.getSoLuong());
            preStm2.setDouble(8, sp.getGiaB());
            preStm2.setDouble(9, sp.getTongCPN());
            preStm2.setString(10, sp.getTg());
            preStm2.setString(11, sp.getMoTa());
            preStm2.setString(12, sp.getMaSP());
            return preStm2.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSP(String maSP) {
        String sql = "Delete from SANPHAM where MaSP = ?";
        try {
            Connection conn3 = DataProvider.GetDataConnection();
            PreparedStatement preStm3 = conn3.prepareStatement(sql);
            preStm3.setString(1, maSP);

            return preStm3.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public static List<SanPham> searchSp(String maSP) {
        String sql = "Select * from SANPHAM where MaSP=?";
        try {
            Connection conn4 = DataProvider.GetDataConnection();
            PreparedStatement preStm4 = conn4.prepareStatement(sql);
            preStm4.setString(1, maSP);
            List<SanPham> list = new ArrayList<>();
            ResultSet rs = preStm4.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setHang(rs.getString("Hang"));
                sp.setLoaiSP(rs.getString("LoaiSP"));
                sp.setPhanLoai(rs.getString("PhanLoai"));
                sp.setSize(Integer.parseInt(rs.getString("Size")));
                sp.setGiaN(Double.parseDouble(rs.getString("GiaNhap")));
                sp.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                sp.setGiaB(Double.parseDouble(rs.getString("GiaBan")));
                sp.setTongCPN(Double.parseDouble(rs.getString("TongCPNH")));
                sp.setTg(rs.getString("ThoiGian"));
                sp.setMoTa(rs.getString("MoTa"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SanPham> findAll() {
        String sql = "select * from SANPHAM";
        try {
            Connection con = DataProvider.GetDataConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            List<SanPham> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setHang(rs.getString("Hang"));
                sp.setLoaiSP(rs.getString("LoaiSP"));
                sp.setPhanLoai(rs.getString("PhanLoai"));
                sp.setSize(Integer.parseInt(rs.getString("Size")));
                sp.setGiaN(Double.parseDouble(rs.getString("GiaNhap")));
                sp.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                sp.setGiaB(Double.parseDouble(rs.getString("GiaBan")));
                sp.setTongCPN(Double.parseDouble(rs.getString("TongCPNH")));
                sp.setTg(rs.getString("ThoiGian"));
                sp.setMoTa(rs.getString("MoTa"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SanPham findByID(String ma) {
        String sql = "select * from SANPHAM where MaSP = ?";
        SanPham sp = new SanPham();
        try {
            Connection con = DataProvider.GetDataConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            List<SanPham> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setHang(rs.getString("Hang"));
                sp.setLoaiSP(rs.getString("LoaiSP"));
                sp.setPhanLoai(rs.getString("PhanLoai"));
                sp.setSize(Integer.parseInt(rs.getString("Size")));
                sp.setGiaN(Double.parseDouble(rs.getString("GiaNhap")));
                sp.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                sp.setGiaB(Double.parseDouble(rs.getString("GiaBan")));
                sp.setTongCPN(Double.parseDouble(rs.getString("TongCPNH")));
                sp.setTg(rs.getString("ThoiGian"));

                sp.setMoTa(rs.getString("MoTa"));

            }
            return sp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall;user=sa;password=123";

    public BigDecimal getTongchiphinhap(String thang, String nam) {
        BigDecimal tcp = BigDecimal.ZERO;
        String sql = "select SUM(TongCPNH) as Tongchiphinhap from SANPHAM where MONTH(ThoiGian) = ? and YEAR(ThoiGian) = ?";
        try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, thang);
            ps.setString(2, nam);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tcp = rs.getBigDecimal("Tongchiphinhap");
                    if (tcp == null) {
                        tcp = BigDecimal.ZERO;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tcp;
    }

    public static List<String> getAllTenSanPham() throws SQLException, ClassNotFoundException {
        List<String> tenSanPhamList = new ArrayList<>();
        String sql = "SELECT TenSP FROM SANPHAM";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataProvider.GetDataConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tenSanPhamList.add(rs.getString("TenSP"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return tenSanPhamList;
    }
    
    public static List<String> getTenSPKhongTrung() throws SQLException, ClassNotFoundException {
        List<String> tenSPList = new ArrayList<>();
        String sql = "SELECT DISTINCT TenSP FROM SANPHAM";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataProvider.GetDataConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {                
                tenSPList.add(rs.getString("TenSP"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return tenSPList;
    }
    
    public static List<SanPham> findTenSP(String tenSP) throws SQLException, ClassNotFoundException {
        List<SanPham> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SANPHAM WHERE TenSP like ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataProvider.GetDataConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenSP);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setHang(rs.getString("Hang"));
                sp.setLoaiSP(rs.getString("LoaiSP"));
                sp.setPhanLoai(rs.getString("PhanLoai"));
                sp.setSize(rs.getInt("Size"));
                sp.setGiaN(rs.getDouble("GiaNhap"));
                sp.setGiaB(rs.getDouble("GiaBan"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setTongCPN(rs.getDouble("TongCPNH"));
                sp.setTg(rs.getString("ThoiGian"));
                sp.setMoTa(rs.getString("MoTa"));
                danhSach.add(sp);
            }
            return danhSach;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
