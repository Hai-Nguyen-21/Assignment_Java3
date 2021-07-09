/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

/**
 *
 * @author ocean-tor
 */
public class DiemSinhVien {
    private int idDiem;
    private String idSV, nameSV;
    private double markToan, markVan, markAnh;

    public DiemSinhVien() {
    }

    public DiemSinhVien(int idDiem,String idSV, String nameSV, double markToan, double markVan, double markAnh) {
        this.idDiem = idDiem;
        this.idSV = idSV;
        this.nameSV = nameSV;
        this.markToan = markToan;
        this.markVan = markVan;
        this.markAnh = markAnh;
    }
    
    public int getIdDiem(){
        return idDiem;
    }
    
    public void setIdDiem(int idDiem){
        this.idDiem = idDiem;
    }

    public String getIdSV() {
        return idSV;
    }

    public void setIdSV(String idSV) {
        this.idSV = idSV;
    }

    public String getNameSV() {
        return nameSV;
    }

    public void setNameSV(String nameSV) {
        this.nameSV = nameSV;
    }

    public double getMarkToan() {
        return markToan;
    }

    public void setMarkToan(double markToan) {
        this.markToan = markToan;
    }

    public double getMarkVan() {
        return markVan;
    }

    public void setMarkVan(double markVan) {
        this.markVan = markVan;
    }

    public double getMarkAnh() {
        return markAnh;
    }

    public void setMarkAnh(double markAnh) {
        this.markAnh = markAnh;
    }
    
    public double getAVG(){
        return (getMarkToan() + getMarkVan() + getMarkAnh()) / 3;
    }
    
    public String getRank(){
        String xl = "";
        double tbc = getAVG();
        if (tbc > 9) {
            xl = "Xuất sắc";
        } else if (tbc > 8){
            xl = "Giỏi";
        } else if (tbc > 6){
            xl = "Khá";
        } else if (tbc > 5){
            xl = "Trung bình";
        } else {
            xl = "Yếu";
        }
        return xl;
    }
}
