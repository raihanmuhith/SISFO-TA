/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author muhith
 */
import GUI.*;
import Model.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class MahasiswaController extends MouseAdapter implements ActionListener{
    private MahasiswaMenu view;
    private Aplikasi model;
    private LoginMenu MenuLogin;
    private Mahasiswa mahasiswa;
    
    public MahasiswaController(Aplikasi model, LoginMenu MenuLogin, Mahasiswa mahasiswa){
        view = new MahasiswaMenu();
        this.MenuLogin = MenuLogin;
        this.model = model;
        this.mahasiswa = mahasiswa;
        
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        view.setTextNamaNIM(mahasiswa.getNama() + " - " + mahasiswa.getNIM());
        
        view.resetView();
        view.setListRegistrasiKelasTA(model.getAllKelasTA());
        TabelDosenKelas();
        TabelMhsKelas();
        TabelDosenPembimbing();
        TabelPembimbing();
        if(mahasiswa.getTugasAkhir()!= null){
            view.setTfJudulTADiHapusTA(mahasiswa.getTugasAkhir().getJudul());
            view.setTfJudulTAAndaDiBuatTA(mahasiswa.getTugasAkhir().getJudul());
            view.setUbahJudulTA(mahasiswa.getTugasAkhir().getJudul());
        }
 
    }
    
    private void TabelPembimbing(){
        if(mahasiswa.getTugasAkhir()!= null){
            if(mahasiswa.getTugasAkhir().getnPembimbing() > 0){
                for(int idx = 0; idx < mahasiswa.getTugasAkhir().getnPembimbing(); idx++){
                    String NID = mahasiswa.getTugasAkhir().getPembimbing(idx).getNID();
                    String Nama =  mahasiswa.getTugasAkhir().getPembimbing(idx).getNama();
                    String Kode =  mahasiswa.getTugasAkhir().getPembimbing(idx).getKodeDosen();
                    String Gender =  mahasiswa.getTugasAkhir().getPembimbing(idx).getGender();
                    String Topik =  mahasiswa.getTugasAkhir().getPembimbing(idx).getTopik();
                    String Email = mahasiswa.getTugasAkhir().getPembimbing(idx).getEmail();   
                    view.setTabelPembimbing(NID, Nama, Kode, Gender, Topik, Email);
                }
            }
        }
    }
    
    private void TabelDosenPembimbing(){
        if(model.getDaftarKelas() != null ){
            for(KelasTugasAkhir KTA : model.getDaftarKelas()){
                if(KTA.getDaftarMahasiswa().contains(mahasiswa)){
                    for(int idx = 0; idx < KTA.getTimDosen().size(); idx++){
                       String NID = KTA.getTimDosen().get(idx).getNID();
                       String Nama = KTA.getTimDosen().get(idx).getNama();
                       String Kode = KTA.getTimDosen().get(idx).getKodeDosen();
                       String Gender = KTA.getTimDosen().get(idx).getGender();
                       String Topik = KTA.getTimDosen().get(idx).getTopik();
                       String Email = KTA.getTimDosen().get(idx).getEmail();   
                       view.setTabelDosenPembimbing(NID, Nama, Kode, Gender, Topik, Email);
                    }
                }
            }
        }
    }
    
    private void TabelDosenKelas(){
        if(model.getDaftarKelas() != null ){
            for(KelasTugasAkhir KTA : model.getDaftarKelas()){
                if(KTA.getDaftarMahasiswa().contains(mahasiswa)){
                    for(int idx = 0; idx < KTA.getTimDosen().size(); idx++){
                       String NID = KTA.getTimDosen().get(idx).getNID();
                       String Nama = KTA.getTimDosen().get(idx).getNama();
                       String Kode = KTA.getTimDosen().get(idx).getKodeDosen();
                       String Gender = KTA.getTimDosen().get(idx).getGender();
                       String Topik = KTA.getTimDosen().get(idx).getTopik();
                       String Email = KTA.getTimDosen().get(idx).getEmail();   
                       view.setTabelDosenKelas(NID, Nama, Kode, Gender, Topik, Email);
                    }
                }
            }
        }
    }
    
    private void TabelMhsKelas(){
        if(model.getDaftarKelas() != null ){
            for(KelasTugasAkhir KTA : model.getDaftarKelas()){
                if(KTA.getDaftarMahasiswa().contains(mahasiswa)){
                    for(int idx = 0; idx < KTA.getDaftarMahasiswa().size(); idx++){
                       String NIM = KTA.getDaftarMahasiswa().get(idx).getNIM();
                       String Nama = KTA.getDaftarMahasiswa().get(idx).getNama();
                       String Gender = KTA.getDaftarMahasiswa().get(idx).getGender();
                       String Topik = KTA.getDaftarMahasiswa().get(idx).getTugasAkhir().getJudul();
                       String Email = KTA.getDaftarMahasiswa().get(idx).getEmail();   
                       view.setTabelMhsKelas(NIM, Nama, Gender, Email, Topik);
                    }
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnLogout())){ // Menu logout
            view.dispose();
            MenuLogin.setVisible(true);
            
        }else if(source.equals(view.getBtnBuatDiMenuBuatTA())){ // Menu Buat TA
            String JudulTA = view.getTfJudulTAdiBuatTA();       
            if((mahasiswa.getTugasAkhir() == null) && (!JudulTA.equals(""))){
                int response = JOptionPane.showConfirmDialog(view,"Buat Tugas Akhir ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    mahasiswa.CreateTA(JudulTA);
                    JOptionPane.showMessageDialog(view,"Tugas Akhir Berhasil Dibuat");
                    view.setTfJudulTADiHapusTA(mahasiswa.getTugasAkhir().getJudul());
                    view.setTfJudulTAAndaDiBuatTA(mahasiswa.getTugasAkhir().getJudul());
                    view.setUbahJudulTA(mahasiswa.getTugasAkhir().getJudul());
                }
            } else if(JudulTA.equals("")){
                JOptionPane.showMessageDialog(view,"Data Kosong");            
            } else{
                JOptionPane.showMessageDialog(view,"Anda Sudah Memiliki Tugas Akhir");
            }
            view.resetView();
            view.setListRegistrasiKelasTA(model.getAllKelasTA());
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();
            TabelPembimbing();
        }else if(source.equals(view.getBtnUbahDiMenuPassword())){ // Menu Ubah Password
            String PasswordLama = view.getTfPasswordLama();
            String PasswordBaru = view.getTfPasswordBaru();
            String KonfirmPassword = view.getTfConfirmPassword();
            
            if((!PasswordLama.equals("")) && (!PasswordBaru.equals("")) && (!KonfirmPassword.equals(""))){
                if(PasswordBaru.equals(KonfirmPassword) && PasswordLama.equals(mahasiswa.getPassword())){
                    int response = JOptionPane.showConfirmDialog(view,"Ubah Password?","Confirm", 
                                   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_OPTION){
                        mahasiswa.setPassword(PasswordBaru);
                        JOptionPane.showMessageDialog(view,"Password Berhasil Diubah");
                    }
                }else{
                    JOptionPane.showMessageDialog(view,"Password Salah!");
                }
            }else{
                JOptionPane.showMessageDialog(view,"Password Kosong!");
            }
            view.resetView();
            view.setListRegistrasiKelasTA(model.getAllKelasTA());
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();
            TabelPembimbing();
        }else if(source.equals(view.getBtnUbahTADiMenuUbahTA())){ // Menu Ubah TA
            String UbahTA = view.getTfUbahJudulTA();
            if(mahasiswa.getTugasAkhir() != null && !UbahTA.equals("")){
                int response = JOptionPane.showConfirmDialog(view,"Ubah Tugas Akhir ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    mahasiswa.getTugasAkhir().setJudul(UbahTA);
                    view.setTfJudulTADiHapusTA(mahasiswa.getTugasAkhir().getJudul());
                    view.setTfJudulTAAndaDiBuatTA(mahasiswa.getTugasAkhir().getJudul());
                    view.setUbahJudulTA(mahasiswa.getTugasAkhir().getJudul());
                    JOptionPane.showMessageDialog(view,"TA Anda Berhasil Diubah");
                }
            }else if(mahasiswa.getTugasAkhir() == null){
                JOptionPane.showMessageDialog(view,"Anda Belum Memiliki TA!");
            } else {
                JOptionPane.showMessageDialog(view,"Judul Tugas Akhir Kosong!");
            }
            view.resetView();
            view.setListRegistrasiKelasTA(model.getAllKelasTA());
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();
            TabelPembimbing();
        }else if(source.equals(view.getBtnHapusTADiMenuHapusTA())){ //Hapus TA
            if(mahasiswa.getTugasAkhir() != null){
                int response = JOptionPane.showConfirmDialog(view,"Hapus Tugas Akhir?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    mahasiswa.DeleteTA();
                    for(KelasTugasAkhir KTA : model.getDaftarKelas()){
                        if (KTA.getDaftarMahasiswa().contains(mahasiswa)){
                            KTA.deleteMahasiswa(mahasiswa);
                        }
                    }
                    mahasiswa.getTugasAkhir().DeleteDosbing();
                    mahasiswa.getTugasAkhir().DeleteQueue();
                    mahasiswa.setCekKelasTA(false);
                    JOptionPane.showMessageDialog(view,"TA Anda Berhasil Dihapus");
                    view.setTfJudulTADiHapusTA("");
                    view.setTfJudulTAAndaDiBuatTA("");
                    view.setUbahJudulTA("");
                }
            } else {
                JOptionPane.showMessageDialog(view,"Anda Belum Memiliki TA!");
            }
            view.resetView();
            view.setListRegistrasiKelasTA(model.getAllKelasTA());
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();
            TabelPembimbing();
        } else if(source.equals(view.getbtnDaftarKelasTA())){ // Registrasi Kelas TA
            if(mahasiswa.getTugasAkhir() != null && view.getIndexListRegistrasiKelasTA() >= 0 && !mahasiswa.isCekKelasTA()){
                KelasTugasAkhir KTA = model.getKelas(view.getIndexListRegistrasiKelasTA());
                int response = JOptionPane.showConfirmDialog(view,"Daftar Kelas TA ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    KTA.addMahasiswa(mahasiswa);
                    mahasiswa.setCekKelasTA(true);
                    JOptionPane.showMessageDialog(view,"Berhasil Daftar Kelas TA");
                }
            } else if (mahasiswa.getTugasAkhir() == null){
                JOptionPane.showMessageDialog(view,"Anda Belum Memiliki TA!");
            } else if (mahasiswa.isCekKelasTA()){
                JOptionPane.showMessageDialog(view,"Anda Sudah Terdaftar Di Kelas TA!");
            }
            view.resetView();
            view.setListRegistrasiKelasTA(model.getAllKelasTA());
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();    
        } else if(source.equals(view.getbtnDaftarPembimbing())){
            if(mahasiswa.getTugasAkhir() != null && mahasiswa.getTugasAkhir().getnPembimbing() < 2 && view.getSelectedRowPembimbing() >= 0){
                Dosen dsn = null;
                for(KelasTugasAkhir KTA : model.getDaftarKelas()){
                    if(KTA.getDaftarMahasiswa().contains(mahasiswa)){
                        dsn = KTA.getTimDosen().get(view.getSelectedRowPembimbing());
                        break;
                    }
                }
                if(!mahasiswa.getTugasAkhir().isInQueue(dsn) && !mahasiswa.getTugasAkhir().isPembimbing(dsn)){
                    int response = JOptionPane.showConfirmDialog(view,"Ajukan Pembimbing TA?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_OPTION){
                        mahasiswa.getTugasAkhir().addReqPembimbing(dsn);
                        JOptionPane.showMessageDialog(view,"Pengajuan Dosen Pembimbing Berhasil");
                        view.setQuePembimbing(mahasiswa.getTugasAkhir().getAllQueDosen());
                    }
                } else {
                    JOptionPane.showMessageDialog(view,"Sudah Melakukan Pengajuan Ke Dosen Yang Dipilih!");
                }
            } else if(mahasiswa.getTugasAkhir() != null && mahasiswa.getTugasAkhir().getnPembimbing() >= 2) {
                JOptionPane.showMessageDialog(view,"Anda Sudah Memiliki 2 Dosen Pembimbing!");
            } else {
                JOptionPane.showMessageDialog(view,"Anda Belum Mempunyai Kelas TA!");
            }
            view.resetView();
            view.setListRegistrasiKelasTA(model.getAllKelasTA());
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();
            TabelPembimbing();
        } else if (source.equals(view.getbtnRegisTA())){
            String[] s = new String[0];
            view.setQuePembimbing(s);
            if(mahasiswa.getTugasAkhir() != null){
                if(mahasiswa.getTugasAkhir().getQueue()!= null){
                    view.setQuePembimbing(mahasiswa.getTugasAkhir().getAllQueDosen());
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source.equals(view.getListRegistrasiKelasTA())){
            view.resetView();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelDosenPembimbing();
            TabelPembimbing();
            try {
                KelasTugasAkhir KTA = model.getKelas(view.getIndexListRegistrasiKelasTA());
                if(KTA.getDaftarMahasiswa()!= null){
                    for(Mahasiswa Mhs : KTA.getDaftarMahasiswa()){
                        view.setTabelMhsRegistrasi(Mhs.getNIM(), Mhs.getNama());
                    }
                }
                if(KTA.getTimDosen() != null){
                    for(Dosen dsn : KTA.getTimDosen()){
                        view.setTabelDosenRegistrasi(dsn.getNID(), dsn.getNama());
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(view, "Belum Ada Kelas TA");
            }
        }
    }
}
