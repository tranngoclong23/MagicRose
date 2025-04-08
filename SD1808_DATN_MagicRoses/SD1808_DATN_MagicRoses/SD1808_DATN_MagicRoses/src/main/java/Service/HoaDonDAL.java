/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DataProvider;
import Model.HoaDon;
import Model.ThanhToan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonDAL {
    
    private static ThanhToan mapResultSetToThanhToan(ResultSet rs) throws SQLException {
        return new ThanhToan(
                rs.getInt("MaHD"),
                rs.getInt("MaKH"),
                rs.getString("SDTKH"),
                rs.getString("MaSP"),
                rs.getString("SPDaMua"),
                rs.getInt("SoLuong"),
                rs.getString("ThoiGian"),
                rs.getString("PhuongThucTT"),
                rs.getDouble("TongTienTT"),
                rs.getDouble("TienDaNhan"),
                rs.getDouble("TienThua"),
                rs.getString("MaNV")
        );
    }
    

    public static List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String query = "SELECT MaHD, MaKH, SDTKH, MaSP, SPDaMua, SoLuong, ThoiGian, PhuongThucTT, TongTienTT, TienDaNhan, TienThua, MaNV FROM THANHTOAN";

        try (Connection conn = DataProvider.GetDataConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                hoaDonList.add(new HoaDon(
                    rs.getInt("MaHD"),
                    rs.getInt("MaKH"),
                    rs.getString("SDTKH"),
                    rs.getString("MaSP"),
                    rs.getString("SPDaMua"),
                    rs.getInt("SoLuong"),
                    rs.getString("ThoiGian"),
                    rs.getString("PhuongThucTT"),
                    rs.getDouble("TongTienTT"),
                    rs.getDouble("TienDaNhan"),
                    rs.getDouble("TienThua"),
                    rs.getString("MaNV")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

    public static List<ThanhToan> findMaHD(int maHD) throws SQLException, ClassNotFoundException {
        List<ThanhToan> danhSach = new ArrayList<>();
        String query = "SELECT * FROM THANHTOAN WHERE MaHD = ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maHD);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToThanhToan(rs));
                }
            }
        }
        return danhSach;
    }
    
    public static List<ThanhToan> findSDT(String sdtKH) throws SQLException, ClassNotFoundException {
        List<ThanhToan> danhSach = new ArrayList<>();
        String query = "SELECT * FROM THANHTOAN WHERE SDTKH = ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sdtKH);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToThanhToan(rs));
                }
            }
        }
        return danhSach;
    }

    public static List<ThanhToan> findMaKH(String maKH) throws SQLException, ClassNotFoundException {
        List<ThanhToan> danhSach = new ArrayList<>();
        String query = "SELECT * FROM THANHTOAN WHERE MaKH = ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maKH);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToThanhToan(rs));
                }
            }
        }
        return danhSach;
    }

    public static List<ThanhToan> findThoiGian(String ngay) throws SQLException, ClassNotFoundException {
        List<ThanhToan> danhSach = new ArrayList<>();
        String query = "SELECT * FROM THANHTOAN WHERE CAST(ThoiGian AS DATE) = ?";

        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ngay);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapResultSetToThanhToan(rs));
                }
            }
        }
        return danhSach;
    }

    public static int demSoDonBanRa(String thang, String nam){
        int soDon = 0;
        try (Connection conn9 = DataProvider.GetDataConnection()) {
            String sql = "select COUNT(*) from THANHTOAN where MONTH(ThoiGian) = ? and YEAR(ThoiGian)=?";
            PreparedStatement ps = conn9.prepareStatement(sql);
            ps.setString(1, thang);
            ps.setString(2, nam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soDon = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soDon;
    }
    public static double tinhTongDTtheothang(String thang, String nam){
        double tongDT = 0;
        try {
            String sql = "select SUM(TongTienTT) from THANHTOAN where MONTH(ThoiGian) = ? and YEAR(ThoiGian)=?";
            Connection conn10 = DataProvider.GetDataConnection();
            PreparedStatement ps = conn10.prepareStatement(sql);
            ps.setString(1, thang);
            ps.setString(2, nam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongDT = rs.getDouble(1);
            }
            rs.close();
            ps.close();
            conn10.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDT;
    }
}
