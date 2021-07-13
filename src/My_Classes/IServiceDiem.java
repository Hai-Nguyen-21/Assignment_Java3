/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ocean-tor
 */
public interface IServiceDiem {
    public boolean addMark(DiemSinhVien diem) throws SQLException;
    
    public boolean updateMark(DiemSinhVien diem) throws SQLException;
    
    public boolean removeMark(String idSV) throws SQLException;
    
    public DiemSinhVien findMark(String idSV) throws SQLException;
    
    public List<DiemSinhVien> findTop5();
    
    public List<DiemSinhVien> getAllDiemSinhVien();
    
    public void writeMark(List<DiemSinhVien> list, String excelPath);
    
    public CellStyle createCellStyleForTitle(XSSFWorkbook workbook);
    
    public Workbook getWorkBook(String excelPath); 
}
