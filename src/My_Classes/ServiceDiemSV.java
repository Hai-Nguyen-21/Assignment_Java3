/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import My_DBO.DBConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ocean-tor
 */
public class ServiceDiemSV implements IServiceDiem {

    private DiemSinhVien diem = new DiemSinhVien();
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private List<DiemSinhVien> list = new ArrayList<>();

    public ServiceDiemSV() {
    }

    @Override
    public boolean addMark(DiemSinhVien diem) throws SQLException {

        String query = "INSERT INTO DIEM (MADIEM, HOTEN, MASV, DTOAN, DVAN, DANH) VALUES ( ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, diem.getIdDiem());
            ps.setString(2, diem.getNameSV());
            ps.setString(3, diem.getIdSV());
            ps.setDouble(4, diem.getMarkToan());
            ps.setDouble(5, diem.getMarkVan());
            ps.setDouble(6, diem.getMarkAnh());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        return false;
    }

    @Override
    public boolean updateMark(DiemSinhVien diem) throws SQLException {
        String query = "UPDATE DIEM SET DTOAN = ?, DVAN = ?, DANH = ? WHERE MASV = ?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);

            ps.setDouble(1, diem.getMarkToan());
            ps.setDouble(2, diem.getMarkVan());
            ps.setDouble(3, diem.getMarkAnh());
            ps.setString(4, diem.getIdSV());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        return false;
    }

    @Override
    public boolean removeMark(String idSV) throws SQLException {
        String query = "DELETE FROM DIEM WHERE MASV = ?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, idSV);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
        return false;
    }

    @Override
    public DiemSinhVien findMark(String idSV) throws SQLException {
        String query = "SELECT * FROM DIEM WHERE MASV = ?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, idSV);

            rs = ps.executeQuery();
            while (rs.next()) {
                diem = new DiemSinhVien();
                diem.setIdDiem(rs.getInt("MADIEM"));
                diem.setIdSV(rs.getString("MASV"));
                diem.setNameSV(rs.getString("HOTEN"));
                diem.setMarkToan(rs.getDouble("DTOAN"));
                diem.setMarkVan(rs.getDouble("DVAN"));
                diem.setMarkAnh(rs.getDouble("DANH"));
            }
            return diem;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return null;
    }
    
    @Override
    public List<DiemSinhVien> findTop5() {
        String query = "SELECT TOP 5*, (DTOAN + DVAN + DANH)/3 AS DTB \n"
                + "FROM DIEM\n"
                + "ORDER BY DTB DESC";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                diem = new DiemSinhVien();
                diem.setIdDiem(rs.getInt("MADIEM"));
                diem.setNameSV(rs.getString("HOTEN"));
                diem.setIdSV(rs.getString("MASV"));
                diem.setMarkToan(rs.getDouble("DTOAN"));
                diem.setMarkVan(rs.getDouble("DVAN"));
                diem.setMarkAnh(rs.getDouble("DANH"));
                list.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DiemSinhVien> getAllDiemSinhVien() {
        String query = "SELECT A.HOTEN, B.MASV, B.DTOAN, B.DVAN, B.DANH \n"
                + "FROM SINHVIEN A JOIN DIEM B ON A.MASV = B.MASV";
        list = new ArrayList<>();

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                diem = new DiemSinhVien();
                diem.setNameSV(rs.getString(1)); // lấy họ tên
                diem.setIdSV(rs.getString(2));
                diem.setMarkToan(rs.getDouble(3));
                diem.setMarkVan(rs.getDouble(4));
                diem.setMarkAnh(rs.getDouble(5));

                list.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    
    @Override
    public void writeMark(List<DiemSinhVien> list, String excelPath){
        try {
            Workbook workbook = getWorkBook(excelPath);
            Sheet sheet = workbook.createSheet("List Điểm");
            
            int rowNum = 0;
            Cell cell;
            Row row;
            
            CellStyle cellStyle = createCellStyleForTitle((XSSFWorkbook) workbook);
            
            row = sheet.createRow(rowNum);
            
            // idStu
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("ID");
            cell.setCellStyle(cellStyle);
            
            // nameStu
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("NAME");
            cell.setCellStyle(cellStyle);
            
            // mathsStu
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("MATHS");
            cell.setCellStyle(cellStyle);
            
            // litStu
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("LIT");
            cell.setCellStyle(cellStyle);
            
            // engStu
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("ENG");
            cell.setCellStyle(cellStyle);
            
            // avgStu
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("AVG");
            cell.setCellStyle(cellStyle);
            
            // rankStu
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("RANK");
            cell.setCellStyle(cellStyle);
            
            // data 
            for (DiemSinhVien o : list) {
                rowNum++;
                row = sheet.createRow(rowNum);
                
                // id
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(o.getIdSV());
                
                // name
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(o.getNameSV());
                
                // maths
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(o.getMarkToan());
                
                // lit
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(o.getMarkVan());
                
                // eng
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(o.getMarkAnh());
                
                // avg
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(o.getAVG());
                
                // rank
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(o.getRank());
            }
            
            File file = new File(excelPath);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CellStyle createCellStyleForTitle(XSSFWorkbook workbook){
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }
    
    @Override
    public Workbook getWorkBook(String excelPath) {
        Workbook workbook = null;
        if (excelPath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelPath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalAccessError();
        }
        return workbook;
    }

}
