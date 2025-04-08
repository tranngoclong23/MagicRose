/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.DataProvider;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class UserDAL {
    public User checkLogin(String username, String password){
        String sql = "select * from DANGNHAP where TenDangNhap = ? and MatKhau = ?";
        User user = null;
       
        try (Connection con = DataProvider.GetDataConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setUsername(rs.getString("TenDangNhap"));
                user.setPass(rs.getString("MatKhau"));
                  
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn cơ sở dữ liệu"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }
}
