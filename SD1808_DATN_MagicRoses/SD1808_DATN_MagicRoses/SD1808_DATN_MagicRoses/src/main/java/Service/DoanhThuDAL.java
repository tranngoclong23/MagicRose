/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DoanhThu;
import Model.KhachHang;
import static Service.KhachHangDAL.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class DoanhThuDAL {
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall;user=sa;password=123";
       public static boolean insert(DoanhThu dt) {
        String sql = "insert into DOANHTHU values (?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dt.getMaDT());
            ps.setDouble(2, dt.getChiPhi());
            ps.setInt(3, dt.getSoDon());
            ps.setDouble(4, dt.getDT());
            ps.setDouble(5, dt.getLoiNhuan());
            ps.setString(6, dt.getThang());
            ps.setString(7, dt.getNam());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
       public static List<DoanhThu> findAll(){
        String sql = "select * from DOANHTHU";
        try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(sql)) {
            List<DoanhThu> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                DoanhThu dt = new DoanhThu();
                dt.setMaDT(rs.getString("MaDT"));
                dt.setChiPhi(rs.getDouble("ChiPhiNhapHang"));
                dt.setSoDon(rs.getInt("SoDonBanRa"));
                dt.setDT(rs.getDouble("DoanhThu"));
                dt.setLoiNhuan(rs.getDouble("LoiNhuan"));
                dt.setThang(rs.getString("Thang"));
                dt.setNam(rs.getString("Nam"));
                list.add(dt);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static DoanhThu findByID(String ma){
        String sql = "select * from DOANHTHU where MaDT = ?";
        DoanhThu dt = new DoanhThu();
        try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                dt.setMaDT(rs.getString("MaDT"));
                dt.setChiPhi(rs.getDouble("ChiPhiNhapHang"));
                dt.setSoDon(rs.getInt("SoDonBanRa"));
                dt.setDT(rs.getDouble("DoanhThu"));
                dt.setLoiNhuan(rs.getDouble("LoiNhuan"));
                dt.setThang(rs.getString("Thang"));
                dt.setNam(rs.getString("Nam"));
            }
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean update(DoanhThu dt){
        String sql = "update DOANHTHU set ChiPhiNhapHang=?, SoDonBanRa=?, DoanhThu=?, LoiNhuan=?, Thang=?, Nam=? where MaDT=?";
        try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, dt.getChiPhi());
            ps.setInt(2, dt.getSoDon());
            ps.setDouble(3, dt.getDT());
            ps.setDouble(4, dt.getLoiNhuan());
            ps.setString(5, dt.getThang());
            ps.setString(6, dt.getNam());
            ps.setString(7, dt.getMaDT());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<DoanhThu> findKH(String maDT){
        String sql = "select * from DOANHTHU where MaDT = ?";
        try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maDT);
            List<DoanhThu> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                DoanhThu dt = new DoanhThu();
                dt.setMaDT(rs.getString("MaDT"));
                dt.setChiPhi(rs.getDouble("ChiPhiNhapHang"));
                dt.setSoDon(rs.getInt("SoDonBanRa"));
                dt.setDT(rs.getDouble("DoanhThu"));
                dt.setLoiNhuan(rs.getDouble("LoiNhuan"));
                dt.setThang(rs.getString("Thang"));
                dt.setNam(rs.getString("Nam"));
                list.add(dt);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
