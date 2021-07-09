/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import My_DBO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author ocean-tor
 */
public class ServiceND {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public NguoiDung checkLogIn(String username, String password){
        String query = "SELECT TAIKHOAN, MATKHAU, VAITRO FROM NGUOIDUNG WHERE TAIKHOAN = ? AND MATKHAU = ?";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            
            rs = ps.executeQuery();
            while (rs.next()) {                
                NguoiDung nd = new NguoiDung();
                nd.setUserName(username);
                nd.setUserType(rs.getString("VAITRO"));
                return nd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
