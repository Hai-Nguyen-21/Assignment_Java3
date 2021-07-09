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
import org.apache.poi.ss.usermodel.Cell;
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
    public void writeMark(DefaultTableModel table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        File saveFile = fileChooser.getSelectedFile();

        if (saveFile != null) {
            saveFile = new File(saveFile.toString() + ".xlsx");
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            Row rowCol = sheet.createRow(0);
            for (int i = 0; i < table.getColumnCount(); i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(table.getColumnName(i));
            }

            for (int i = 0; i < table.getRowCount(); i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    if (table.getValueAt(i, j) != null) {
                        cell.setCellValue(table.getValueAt(i, j).toString());
                    }
                }
            }

            try {
                FileOutputStream fos = new FileOutputStream(new File(saveFile.toString()));
                fos.close();
                workbook.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "WRITE INTO EXCEL FAILED");
        }
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
}
