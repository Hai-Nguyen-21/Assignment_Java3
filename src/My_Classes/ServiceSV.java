/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import My_DBO.DBConnection;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ocean-tor
 */
public class ServiceSV implements IServiceSV{

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private List<SinhVien> list = new ArrayList<SinhVien>();
    private SinhVien sv = new SinhVien();
    String imgPath = null;
    public ServiceSV() {
    }
    
    @Override
    public boolean addSV(SinhVien sv) throws SQLException{
        String query = "INSERT INTO SINHVIEN(MASV, HOTEN, MAIL, SDT, GIOITINH, DCHI, HINH) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, sv.getIdSV());
            ps.setString(2, sv.getNameSV());
            ps.setString(3, sv.getMailSV());
            ps.setString(4, sv.getPhoneSV());
            ps.setString(5, sv.getGenderSV());
            ps.setString(6, sv.getAddressSV());
            ps.setString(7, sv.getImageSV());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally{
            ps.close();
            conn.close();
        }
        return false;
    }
    
    @Override
    public boolean updateSV(SinhVien sv) throws SQLException{
        String query = "UPDATE SINHVIEN SET HOTEN = ?, MAIL = ?, SDT = ?, GIOITINH = ?, DCHI = ?, HINH = ? WHERE MASV = ?";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, sv.getNameSV());
            ps.setString(2, sv.getMailSV());
            ps.setString(3, sv.getPhoneSV());
            ps.setString(4, sv.getGenderSV());
            ps.setString(5, sv.getAddressSV());
            ps.setString(6, sv.getImageSV());
            ps.setString(7, sv.getIdSV());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally{
            ps.close();
            conn.close();
        }
        return false;
    }
    
    @Override
    public boolean removeSV(String idSV) throws SQLException{
        String query = "DELETE FROM SINHVIEN WHERE MASV = ?";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, idSV);
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally{
            ps.close();
            conn.close();
        }
        return false;
    }
    
    @Override
    public SinhVien findSV(String idSV) throws SQLException{
        String query = "SELECT * FROM SINHVIEN WHERE MASV = ?";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, idSV);
            
            rs = ps.executeQuery();
            while (rs.next()) {                
                sv = new SinhVien();
                sv.setIdSV(rs.getString("MASV"));
                sv.setNameSV(rs.getString("HOTEN"));
                sv.setMailSV(rs.getString("MAIL"));
                sv.setPhoneSV(rs.getString("SDT"));
                sv.setGenderSV(rs.getString("GIOITINH"));
                sv.setAddressSV(rs.getString("DCHI"));
                sv.setImageSV(rs.getString("HINH"));
            }
            return sv;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally{
            rs.close();
            ps.close();
            conn.close();
        }
        return null;
    }
    
    @Override
    public void fillTable(DefaultTableModel table) throws SQLException{
        String query = "SELECT * FROM SINHVIEN";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            table.setRowCount(0);
            while (rs.next()) {                
                table.addRow(new Object[]{
                    rs.getString("MASV"),
                    rs.getString("HOTEN"),
                    rs.getString("MAIL"),
                    rs.getString("SDT"),
                    rs.getString("GIOITINH"),
                    rs.getString("DCHI"),
                    rs.getString("HINH")
                });
            }
            table.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally{
            rs.close();
            ps.close();
            conn.close();
        }
    }
    
    @Override
    public boolean validE(String mail){
        String regex = "^(.+)@(.+)$";
        Pattern mailPat = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = mailPat.matcher(mail);
        return matcher.find();
    }
    
    @Override
    public boolean validPN(String phoneNum){
        String regex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        Pattern phonePat = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = phonePat.matcher(phoneNum);
        return matcher.find();
    }
    
    @Override
    public int countData(String tableName){
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM " + tableName + " ";
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                total = rs.getInt("TOTAL");
            }
        } catch (Exception e) {
        }
        return total;
    }
    
    @Override
    public int countDataND(String tableName, String vaitro){
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM " + tableName + " WHERE VAITRO = N'" + vaitro + " '" ;
        
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                total = rs.getInt("TOTAL");
            }
        } catch (Exception e) {
        }
        return total;
    }
}
