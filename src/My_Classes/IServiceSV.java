/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ocean-tor
 */
public interface IServiceSV {
    public boolean addSV(SinhVien sv) throws SQLException;
    
    public boolean updateSV(SinhVien sv) throws SQLException;
    
    public boolean removeSV(String idSV) throws SQLException;
    
    public SinhVien findSV(String idSV) throws SQLException;
    
    public void fillTable(DefaultTableModel table) throws SQLException;
    
    public boolean validE(String mail);
    
    public boolean validPN(String phoneNum);
    
    public int countData(String tableName);
    
    public int countDataND(String tableName, String vaitro);
    
}
