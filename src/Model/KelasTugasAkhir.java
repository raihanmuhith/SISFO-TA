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
public class KelasTugasAkhir {
    private List<Dosen> timDosen;
    private List<Mahasiswa> DaftarMahasiswa;
    private String Topik;
    
    public KelasTugasAkhir(String Topik){
        this.Topik = Topik;
        timDosen = new ArrayList();
        DaftarMahasiswa = new ArrayList();
    }
    
    public void addMahasiswa(Mahasiswa M){
        DaftarMahasiswa.add(M);
    }
    
    public void addDosen(Dosen D){
        timDosen.add(D);
    }
    
    public void deleteDosen(int index){
        timDosen.remove(index);
    }
    
    public void deleteMhs(int index){
        DaftarMahasiswa.remove(index);
    }
            
    public void deleteMahasiswa(Mahasiswa Mhs){
        DaftarMahasiswa.remove(Mhs);
    }

    public String getTopik() {
        return Topik;
    }

    public List<Dosen> getTimDosen() {
        return timDosen;
    }

    public List<Mahasiswa> getDaftarMahasiswa() {
        return DaftarMahasiswa;
    } 
    
}
