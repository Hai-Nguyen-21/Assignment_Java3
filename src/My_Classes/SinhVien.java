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
public class SinhVien {
    private String idSV, nameSV, mailSV, phoneSV, genderSV, addressSV, imageSV;

    public SinhVien() {
    }

    public SinhVien(String idSV, String nameSV, String mailSV, String phoneSV, String genderSV, String addressSV, String imageSV) {
        this.idSV = idSV;
        this.nameSV = nameSV;
        this.mailSV = mailSV;
        this.phoneSV = phoneSV;
        this.genderSV = genderSV;
        this.addressSV = addressSV;
        this.imageSV = imageSV;
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

    public String getMailSV() {
        return mailSV;
    }

    public void setMailSV(String mailSV) {
        this.mailSV = mailSV;
    }

    public String getPhoneSV() {
        return phoneSV;
    }

    public void setPhoneSV(String phoneSV) {
        this.phoneSV = phoneSV;
    }

    public String getGenderSV() {
        return genderSV;
    }

    public void setGenderSV(String genderSV) {
        this.genderSV = genderSV;
    }

    public String getAddressSV() {
        return addressSV;
    }

    public void setAddressSV(String addressSV) {
        this.addressSV = addressSV;
    }

    public String getImageSV() {
        return imageSV;
    }

    public void setImageSV(String imageSV) {
        this.imageSV = imageSV;
    }
    
    
}
