/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DataProvider;
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
public class ThanhToanDAL {

    public static boolean checkMaKH(String maKH) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM KHACHHANG WHERE MaKH = ?";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maKH);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
    
    public static boolean checkSDT(String sdt) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM KHACHHANG WHERE SoDT = ?";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sdt);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
    
    public static int getsdtKH(String sdtKH) throws SQLException, ClassNotFoundException {
        String query = "SELECT MaKH FROM KHACHHANG WHERE SoDT = ?";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sdtKH);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MaKH");
                }
            }
        }
        return 0;
    }
    
    public static boolean checkMaNV(String maNV) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM NHANVIEN WHERE MaNV = ?";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maNV);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
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

    public static double getGiaSanPham(String tenSP) throws SQLException, ClassNotFoundException {
        String query = "SELECT GiaBan FROM SANPHAM WHERE TenSP = ?";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("GiaBan");
                }
            }
        }
        return 0;
    }

    public static String getMaSP(String tenSP) throws SQLException, ClassNotFoundException {
        String query = "SELECT MaSP FROM SANPHAM WHERE TenSP = ?";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MaSP");
                }
            }
        }
        return null;
    }

    public static boolean insert(ThanhToan tt) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO THANHTOAN (MaKH, SDTKH, MaNV, SoLuong, ThoiGian, SPDaMua, MaSP, PhuongThucTT, TongTienTT, TienDaNhan, TienThua) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataProvider.GetDataConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tt.getMaKH());
            stmt.setString(2, tt.getSdtKH());
            stmt.setString(3, tt.getMaNV());
            stmt.setInt(4, tt.getSoLuong());
            stmt.setString(5, tt.getThoiGianMua());
            stmt.setString(6, tt.getSpDaMua());
            stmt.setString(7, tt.getMaSP());
            stmt.setString(8, tt.getPhuongThucTT());
            stmt.setDouble(9, tt.getTongTienTT());
            stmt.setDouble(10, tt.getTienDaNhan());
            stmt.setDouble(11, tt.getTienThua());
            return stmt.executeUpdate() > 0;
        }
    }
    
}
