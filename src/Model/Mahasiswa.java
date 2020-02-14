/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author MSI
 */
public class Mahasiswa extends Orang {
    private String NIM;
    private String Gender;
    private String Email;
    private TugasAkhir tugasAkhir;
    private boolean cekKelasTA;
    
    public Mahasiswa(String Nama, String NIM, String Gender, String Email){
        super(Nama);
        this.NIM = NIM;
        this.Gender = Gender;
        this.Email = Email;
        super.setUsername(NIM);
        super.setPassword(NIM);
    }
    
    public boolean isCekKelasTA() {
        return cekKelasTA;
    }

    public void setCekKelasTA(boolean cekKelasTA) {
        this.cekKelasTA = cekKelasTA;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public void CreateTA(String Topik){
        tugasAkhir = new TugasAkhir(Topik);
    }
    
    public TugasAkhir getTugasAkhir(){
        return tugasAkhir;
    }
    
    public void DeleteTA(){
        tugasAkhir = null;
    }
}
