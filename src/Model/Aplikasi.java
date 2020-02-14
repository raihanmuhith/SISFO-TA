/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Database.Database;
import java.util.*;
/**
 *
 * @author MSI
 */
public class Aplikasi {
    private List<Dosen> daftarDosen;
    private List<Mahasiswa> daftarMahasiswa;
    private List<KelasTugasAkhir> daftarKelas;
    private Database db;
    
    public Aplikasi(){
        daftarDosen = new ArrayList();
        daftarMahasiswa = new ArrayList();
        daftarKelas = new ArrayList();
        db = new Database();
        db.connect();
    }

    public List<Dosen> getDaftarDosen() {
        return daftarDosen;
    }

    public List<Mahasiswa> getDaftarMahasiswa() {
        return daftarMahasiswa;
    }
    
    public List<KelasTugasAkhir> getDaftarKelas(){
        return daftarKelas;
    }
    // KELAS TA
    public void addDaftarKelas(KelasTugasAkhir KTA){
        daftarKelas.add(KTA);
        db.saveKelasTA(KTA);
    }
    
    public void deleteKelasTA(KelasTugasAkhir KTA){
        for(int idx = 0; idx < daftarKelas.size(); idx++){
            if(daftarKelas.get(idx) == KTA){
                daftarKelas.remove(idx);
            }
        }
    }
    
    public KelasTugasAkhir getKelas(int index){
        return daftarKelas.get(index);
    }
    
    public KelasTugasAkhir getIkutKelas(Dosen d, int index){
        int flag = -1;
        KelasTugasAkhir KTA = null;
        for(int idx = 0; idx < daftarKelas.size(); idx++){
            for(int idx2 = 0; idx2 < daftarDosen.size();idx2++){
                if(daftarDosen.get(idx2).getKelasTA() == daftarKelas.get(idx) && daftarKelas.get(idx).getTimDosen().contains(d)){
                    flag++;
                    if(flag == index){
                        KTA = daftarKelas.get(idx);
                        break;
                    }
                }
            }
        }
        return KTA;
    }
    // End of Kelas TA
    
    //Dosen
    public void addDosen(Dosen D){
        daftarDosen.add(D);
        db.saveDosen(D);
    }    
    public void DeleteDosen(int index){
        if(daftarDosen.get(index).getKelasTA() != null){
            deleteKelasTA(daftarDosen.get(index).getKelasTA());
        }
        System.out.println("sukses aplikasi");
        db.deleteDosen(daftarDosen.get(index));
        daftarDosen.remove(index);
    }
    
    public Dosen getDosen(int index){
        return daftarDosen.get(index);
    }
    
    public Dosen getDosen(String index){
        return db.getDosen(index);
    }
    
    public void updateDosen(int index, String Username, String Password, String Nama, String Kode, String Topik, String Gender, String Email){
        daftarDosen.get(index).setUsername(Username);
        daftarDosen.get(index).setPassword(Password);
        daftarDosen.get(index).setNama(Nama);
        daftarDosen.get(index).setTopik(Topik);
        daftarDosen.get(index).setKodeDosen(Kode);
        daftarDosen.get(index).setGender(Gender);
        daftarDosen.get(index).setEmail(Email);
        db.ubahDosen(daftarDosen.get(index));
    }
    
    public int checkDuplicateNID(String NID){
        for(int idx = 0; idx < daftarDosen.size(); idx++){
            if(daftarDosen.get(idx).getNID().equals(NID)){
                return idx;
            }
        }
        return -1;
    }
    
    public String[] getListDosen(){
        String[] listNID = new String[(daftarDosen.size())];
        for (int idx = 0; idx < daftarDosen.size(); idx++) {
            listNID[idx] = daftarDosen.get(idx).getNID();
        }
        return listNID;
    }
    
    public int getNkelasTA(Dosen d){
        int n =0;
        for(int idx = 0; idx < daftarKelas.size(); idx++){
            for(int idx2 = 0; idx2 < daftarDosen.size();idx2++){
                if(daftarDosen.get(idx2).getKelasTA() == daftarKelas.get(idx) && daftarKelas.get(idx).getTimDosen().contains(d)){
                    n++;
                }
            }
        }
        return n;
    }
    
    public void loadAllDosenAplikasi(){
        daftarDosen = db.loadAllDosen();
    }
    
    public String[] loadListAllDosenAplikasi(){
        String[] AllNID = new String[db.loadAllDosen().size()];
        for (int i = 0; i < db.loadAllDosen().size(); i++) {
            AllNID[i] = db.loadAllDosen().get(i).getNID();
        }
        return AllNID;
    }
    
    public void loadAllMhsAplikasi(){
        daftarMahasiswa = db.loadAllMahasiswa();
    }
    
    public ArrayList<String> loadAllNID(){
        return db.loadAllNID();
    }
            
            
    //End of Dosen
    
    //Mahasiswa
    public void addMahasiswa(Mahasiswa M){
        daftarMahasiswa.add(M);
        db.saveMahasiswa(M);
    }
    
    public void DeleteMahasiswa(int index){
        db.deleteMahasiswa(daftarMahasiswa.get(index));
        daftarMahasiswa.remove(index);
    }
    
    public Mahasiswa getMahasiswa(int index){
        return daftarMahasiswa.get(index);
    }
    
    public void updateMahasiswa(int index, String Username, String Password, String Nama, String NIM, String Gender, String Email){
        daftarMahasiswa.get(index).setUsername(Username);
        daftarMahasiswa.get(index).setPassword(Password);
        daftarMahasiswa.get(index).setNama(Nama);
        daftarMahasiswa.get(index).setNIM(NIM);
        daftarMahasiswa.get(index).setGender(Gender);
        daftarMahasiswa.get(index).setEmail(Email);
    }
    public String[] getListMahasiswa(){
        String[] listNIM = new String[(daftarMahasiswa.size())];
        for (int idx = 0; idx < daftarMahasiswa.size(); idx++) {
            listNIM[idx] = daftarMahasiswa.get(idx).getNIM();
        }
        return listNIM;
    }
    
    public int checkDuplicateNIM(String NIM){
        for(int idx = 0; idx < daftarMahasiswa.size(); idx++){
            if(daftarMahasiswa.get(idx).getNIM().equals(NIM)){
                return idx;
            }
        }
        return -1;
    }
    
    public ArrayList<String> loadAllNIM(){
        return db.loadAllNIM();
    }
    
    public String[] getarrAllNID(){
        ArrayList<String> allNID = loadAllNID();
        String[] arrAllNID = new String[allNID.size()];
        for (int i = 0; i < allNID.size(); i++) {
            arrAllNID[i] = allNID.get(i);
        }
        return arrAllNID;
    }
    
    public String[] getarrAllNIM(){
        ArrayList<String> allNIM = loadAllNIM();
        String[] arrAllNIM = new String[allNIM.size()];
        for (int i = 0; i < allNIM.size(); i++) {
            arrAllNIM[i] = allNIM.get(i);
        }
        return arrAllNIM;
    }
    
    // End of Mahasiswa
    
    public Orang SearchUser(String Username, String Password){
        for(int idx = 0; idx < daftarDosen.size(); idx++){
            if(daftarDosen.get(idx).getUsername().equals(Username) && daftarDosen.get(idx).getPassword().equals(Password)){
                return daftarDosen.get(idx);
            }
        }
        
        for(int idx = 0; idx < daftarMahasiswa.size(); idx++){
            if(daftarMahasiswa.get(idx).getUsername().equals(Username) && daftarMahasiswa.get(idx).getPassword().equals(Password)){
                return daftarMahasiswa.get(idx);
            }
        }
        return null;
    }
    
    public String[] getAllKelasTA(){
        String[] s = new String[daftarKelas.size()];
        for(int idx = 0; idx < daftarKelas.size(); idx++){
            for(int idx2 = 0; idx2 < daftarDosen.size();idx2++){
                if(daftarDosen.get(idx2).getKelasTA() == daftarKelas.get(idx)){
                    s[idx] = daftarDosen.get(idx2).getNama() + ", " + daftarKelas.get(idx).getTopik();
                }
            }
        }
        return s;
    }
    
    public String[] getAllKelasTA(Dosen d){
        int flag = 0;
        String[] s = new String[getNkelasTA(d)];
        for(int idx = 0; idx < daftarKelas.size(); idx++){
            for(int idx2 = 0; idx2 < daftarDosen.size();idx2++){
                if(daftarDosen.get(idx2).getKelasTA() == daftarKelas.get(idx) && daftarKelas.get(idx).getTimDosen().contains(d)){
                    s[flag] = daftarDosen.get(idx2).getNama() + ", " + daftarKelas.get(idx).getTopik();
                    flag++;
                }
            }
        }
        return s;
    }
    
    public boolean checkNullDosen(String Username, String Password, String Nama, String NID, String Kode, String Topik, String Gender, String Email){
        if(Username.equals("") || Password.equals("") || Nama.equals("") || NID.equals("") || Kode.equals("") || Topik.equals("") || Gender.equals("") || Email.equals("")){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkNullMhs(String Username, String Password, String Nama, String NIM,String Gender, String Email){
        if(Username.equals("") || Password.equals("") || Nama.equals("") || NIM.equals("") || Gender.equals("") || Email.equals("")){
            return true;
        } else {
            return false;
        }
    }
}
