/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.*;
/**
 *
 * @author MSI
 */
public class Dosen extends Orang {
    private KelasTugasAkhir KelasTA;
    private String KodeDosen;
    private String NID;
    private String Email;
    private String Topik;
    private String Gender;
    private List<Mahasiswa> Bimbingan;
    
    public Dosen(String Nama, String KodeDosen, String NID, String Gender, String Email, String Topik){
        super(Nama);
        this.KodeDosen = KodeDosen;
        this.NID = NID;
        this.Email = Email;
        this.Topik = Topik;
        this.Gender = Gender;
        super.setUsername(NID);
        super.setPassword(NID);
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getKodeDosen() {
        return KodeDosen;
    }

    public void setKodeDosen(String KodeDosen) {
        this.KodeDosen = KodeDosen;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTopik() {
        return Topik;
    }

    public void setTopik(String Topik) {
        this.Topik = Topik;
    }
    
    public KelasTugasAkhir getKelasTA(){
        return KelasTA;
    }
    
    public void CreateKelasTA(String Topik){
        KelasTA = new KelasTugasAkhir(Topik);
    }
    
    public void DeleteKelasTA(){
        KelasTA = null;
    }
    
    public void addBimbingan(Mahasiswa M){
        Bimbingan.add(M);
    }
    
}
