/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DataProvider;
import Model.SanPham;
import Model.ThanhToan;
import Model.TonKho;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class TonKhoDAL {

    public boolean insertTK(TonKho tk) {
        String sql = "insert into TONKHO( TenSP, TonKho, GhiChu) values( ?, ?, ?) ";
        try {
            Connection conn1 = DataProvider.GetDataConnection();
            PreparedStatement preStm1 = conn1.prepareStatement(sql);

            preStm1.setString(1, tk.getTenSP());
            preStm1.setInt(2, tk.getTonKho());
            preStm1.setString(3, tk.getGhiChu());

            return preStm1.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public boolean updateTK(TonKho tk) {
//        String sql = "Update TONKHO set TenSP=?, GiaNhap=?, GiaBan=?, SoLuong=?, TonKho=?, TongCPNH=?,ThoiGian=?, GhiChu=? Where MaTK=?";
//        try {
//            Connection conn2 = DataProvider.GetDataConnection();
//            PreparedStatement preStm2 = conn2.prepareStatement(sql);
//            preStm2.setString(1, tk.getTenSP());
//            preStm2.setDouble(2, tk.getGiaN());
//            preStm2.setDouble(3, tk.getGiaB());
//            preStm2.setInt(4, tk.getSoLuong());
//            preStm2.setInt(5, tk.getTonKho());
//            preStm2.setDouble(6, tk.getTongCPN());
//            preStm2.setString(7, tk.getTg());
//            preStm2.setString(8, tk.getGhiChu());
//            preStm2.setString(9, tk.getMaTK());
//            return preStm2.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public boolean deleteTK(String MaTK) {
//        String sql = "Delete from TONKHO where MaTK = ?";
//        try {
//            Connection conn3 = DataProvider.GetDataConnection();
//            PreparedStatement preStm3 = conn3.prepareStatement(sql);
//            preStm3.setString(1, MaTK);
//
//            return preStm3.executeUpdate() > 0;
//        } catch (Exception e) {
//        }
//        return false;
//    }
    public static List<TonKho> searchTen(String maTK) {
        String sql = "Select * from TONKHO where TenSP like ?";
        try {
            Connection conn4 = DataProvider.GetDataConnection();
            PreparedStatement preStm4 = conn4.prepareStatement(sql);
            preStm4.setString(1, maTK);
            List<TonKho> list = new ArrayList<>();
            ResultSet rs = preStm4.executeQuery();
            if (rs.next()) {
                TonKho tk = new TonKho();
                tk.setMaTK(rs.getString("MaTK"));
                tk.setTenSP(rs.getString("TenSP"));
//                    tk.setGiaN(Double.parseDouble(rs.getString("GiaNhap")));
//                    tk.setGiaB(Double.parseDouble(rs.getString("GiaBan")));
//                    tk.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                tk.setTonKho(Integer.parseInt(rs.getString("TonKho")));
//                    tk.setTongCPN(Double.parseDouble(rs.getString("TongCPNH")));
//                    tk.setTg(rs.getString("ThoiGian"));
                tk.setGhiChu(rs.getString("GhiChu"));

                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<TonKho> findAll() {
        String sql = "select * from TONKHO";
        try {
            Connection con = DataProvider.GetDataConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            List<TonKho> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TonKho tk = new TonKho();
                tk.setMaTK(rs.getString("MaTK"));
                tk.setTenSP(rs.getString("TenSP"));
//                tk.setGiaN(Double.parseDouble(rs.getString("GiaNhap")));
//                tk.setGiaB(Double.parseDouble(rs.getString("GiaBan")));
//                tk.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                tk.setTonKho(Integer.parseInt(rs.getString("TonKho")));
//                tk.setTongCPN(Double.parseDouble(rs.getString("TongCPNH")));
//                tk.setTg(rs.getString("ThoiGian"));
                tk.setGhiChu(rs.getString("GhiChu"));

                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TonKho findByID(String ma) {
        String sql = "select * from TONKHO where MaTK = ?";
        TonKho tk = new TonKho();
        try {
            Connection con = DataProvider.GetDataConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            List<TonKho> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                tk.setMaTK(rs.getString("MaTK"));
                tk.setTenSP(rs.getString("TenSP"));
//                tk.setGiaN(Double.parseDouble(rs.getString("GiaNhap")));
//                tk.setGiaB(Double.parseDouble(rs.getString("GiaBan")));
//                tk.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
                tk.setTonKho(Integer.parseInt(rs.getString("TonKho")));
//                tk.setTongCPN(Double.parseDouble(rs.getString("TongCPNH")));
//                tk.setTg(rs.getString("ThoiGian"));
                tk.setGhiChu(rs.getString("GhiChu"));

            }
            return tk;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall;user=sa;password=123";

    public BigDecimal getTongchiphinhap(String thang, String nam) {
        BigDecimal tcp = BigDecimal.ZERO;
        String sql = "select SUM(TongCPNH) as Tongchiphinhap from TONKHO where MONTH(ThoiGian) = ? and YEAR(ThoiGian) = ?";
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

    public static List<String> getSanPhamList() throws SQLException, ClassNotFoundException {
        List<String> sanPhamList = new ArrayList<>();
        String query = "SELECT TenSP FROM SANPHAM";
        try (Connection conn = DataProvider.GetDataConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                sanPhamList.add(rs.getString("TenSP"));
            }
        }
        return sanPhamList;
    }

    public static List<TonKho> findMaTK(int maTK) throws SQLException, ClassNotFoundException {
        List<TonKho> danhSach = new ArrayList<>();
        String query = "SELECT * FROM TONKHO WHERE MaTK = ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maTK);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToTonKho(rs));
                }
            }
        }
        return danhSach;
    }

    public static List<TonKho> findThoiGian(String ngay) throws SQLException, ClassNotFoundException {
        List<TonKho> danhSach = new ArrayList<>();
        String query = "SELECT * FROM TONKHO WHERE CAST(ThoiGian AS DATE) = ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ngay);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToTonKho(rs));
                }
            }
        }
        return danhSach;
    }

    public static List<TonKho> findTenSP(String tenSP) throws SQLException, ClassNotFoundException {
        List<TonKho> danhSach = new ArrayList<>();
        String query = "SELECT * FROM TONKHO WHERE TenSP like ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenSP);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToTonKho(rs));
                }
            }
        }
        return danhSach;
    }

    private static TonKho mapResultSetToTonKho(ResultSet rs) throws SQLException {
        return new TonKho(
                rs.getString("MaTK"),
                rs.getString("TenSP"),               
                Integer.parseInt(rs.getString("TonKho")),
                rs.getString("GhiChu")
        );
    }
}
