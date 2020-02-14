/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.*;
import Model.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
/**
 *
 * @author MSI
 */
public class DosenController extends MouseAdapter implements ActionListener{
    private DosenMenu view;
    private Aplikasi model;
    private LoginMenu MenuLogin;
    private Dosen dosen;
    
    public DosenController(Aplikasi model, LoginMenu MenuLogin, Dosen dosen){
        view = new DosenMenu();
        this.MenuLogin = MenuLogin;
        this.model = model; 
        this.dosen = dosen;
        
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        view.ResetView();
        view.setTextNIDDosen(dosen.getNID());
        view.setTextNamaDosen(dosen.getNama());
        
        view.setTopikKelasTA(dosen.getTopik());
        view.setListBuatTA(model.getAllKelasTA());
        view.setListIkutKelas(model.getAllKelasTA(dosen));
        TabelTambahDosen();
        TabelHapusDosen();
        TabelHapusMhs();
        TabelDosenKelas();
        TabelMhsKelas();
        TabelKonfirmasi();
    }
    
    private void TabelKonfirmasi(){
        for(int idx = 0; idx < model.getDaftarMahasiswa().size(); idx++){
            if(model.getDaftarMahasiswa().get(idx).getTugasAkhir() != null && model.getDaftarMahasiswa().get(idx).getTugasAkhir().getQueue() != null ){
                if(model.getDaftarMahasiswa().get(idx).getTugasAkhir().getQueue().contains(dosen)){
                    String NIM = model.getDaftarMahasiswa().get(idx).getNIM();
                    String Nama = model.getDaftarMahasiswa().get(idx).getNama();
                    String Gender = model.getDaftarMahasiswa().get(idx).getGender();
                    String Email = model.getDaftarMahasiswa().get(idx).getEmail();
                    String Topik = model.getDaftarMahasiswa().get(idx).getTugasAkhir().getJudul();
                    view.setTabelKonfirmasi(NIM, Nama, Gender, Email, Topik);
                }
            }
        }
    }
    
    private void TabelTambahDosen(){
        for(int idx = 0; idx < model.getDaftarDosen().size(); idx++){
            String NID = model.getDosen(idx).getNID();
            String Nama = model.getDosen(idx).getNama();
            String Kode = model.getDosen(idx).getKodeDosen();
            String Gender = model.getDosen(idx).getGender();
            String Topik = model.getDosen(idx).getTopik();
            String Email = model.getDosen(idx).getEmail();
            view.setTabelTambahDosen(NID, Nama, Kode, Gender, Topik, Email);
        }
    }
    
    private void TabelHapusDosen(){
        if(dosen.getKelasTA()!= null){
            for(int idx = 0; idx < dosen.getKelasTA().getTimDosen().size(); idx++){
                String NID = dosen.getKelasTA().getTimDosen().get(idx).getNID();
                String Nama = dosen.getKelasTA().getTimDosen().get(idx).getNama();
                String Kode = dosen.getKelasTA().getTimDosen().get(idx).getKodeDosen();
                String Gender = dosen.getKelasTA().getTimDosen().get(idx).getGender();;
                String Topik = dosen.getKelasTA().getTimDosen().get(idx).getTopik();
                String Email = dosen.getKelasTA().getTimDosen().get(idx).getEmail();
                view.setTabelHapusDosen(NID, Nama, Kode, Gender, Topik, Email);
            }
        }
    }
    
    private void TabelDosenKelas(){
        if(dosen.getKelasTA()!= null){
            for(int idx = 0; idx < dosen.getKelasTA().getTimDosen().size(); idx++){
                String NID = dosen.getKelasTA().getTimDosen().get(idx).getNID();
                String Nama = dosen.getKelasTA().getTimDosen().get(idx).getNama();
                String Kode = dosen.getKelasTA().getTimDosen().get(idx).getKodeDosen();
                String Gender = dosen.getKelasTA().getTimDosen().get(idx).getGender();;
                String Topik = dosen.getKelasTA().getTimDosen().get(idx).getTopik();
                String Email = dosen.getKelasTA().getTimDosen().get(idx).getEmail();
                view.setTabelDosenKelasAnda(NID, Nama, Kode, Gender, Topik, Email);
            }
        }
    }  
    
    private void TabelMhsKelas(){
        if(dosen.getKelasTA() != null){
            for(int idx = 0; idx < dosen.getKelasTA().getDaftarMahasiswa().size(); idx++){
                String NIM = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getNIM();
                String Nama = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getNama();
                String Gender = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getGender();
                String Email = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getEmail();
                String Topik = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getTugasAkhir().getJudul();
                view.setTabelMhsKelasAnda(NIM, Nama, Gender, Email, Topik);
            }
        }
    }
    
    private void TabelHapusMhs(){
        if(dosen.getKelasTA() != null){
            for(int idx = 0; idx < dosen.getKelasTA().getDaftarMahasiswa().size(); idx++){
                String NIM = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getNIM();
                String Nama = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getNama();
                String Gender = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getGender();
                String Email = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getEmail();
                String Topik = dosen.getKelasTA().getDaftarMahasiswa().get(idx).getTugasAkhir().getJudul();
                view.setTabelHapusMhs(NIM, Nama, Gender, Email, Topik);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getbtnBuatKelas())){ // MENU Buat Kelas TA
            if(dosen.getKelasTA() == null){
                int response = JOptionPane.showConfirmDialog(view,"Buat Kelas TA ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(view,"Kelas TA Berhasil Dibuat");
                    dosen.CreateKelasTA(dosen.getTopik());
                    model.addDaftarKelas(dosen.getKelasTA());
                    dosen.getKelasTA().addDosen(dosen);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Anda Sudah Membuat Kelas TA");
            }
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        } else if (source.equals(view.getbtnTambahDosen())){ // Menu Tambah Dosen Kelas TA
            try {
                Dosen dsn = model.getDosen(view.getIndexTambahDosen());
                if(dosen.getKelasTA()!= null && !dosen.getKelasTA().getTimDosen().contains(dsn)){
                    int response = JOptionPane.showConfirmDialog(view,"Tambahkan Dosen ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(view,"Dosen Berhasil Ditambahkan");
                        dosen.getKelasTA().addDosen(dsn);
                    }
                } else if(dosen.getKelasTA() == null){
                    JOptionPane.showMessageDialog(view,"Anda Belum Membuat Kelas TA");
                } else {
                    JOptionPane.showMessageDialog(view,"Dosen Sudah Terdaftar!");
                }
                view.ResetView();
                view.setListIkutKelas(model.getAllKelasTA(dosen));
                view.setListBuatTA(model.getAllKelasTA());
                TabelTambahDosen();
                TabelHapusDosen();
                TabelHapusMhs();
                TabelDosenKelas();
                TabelMhsKelas();
                TabelKonfirmasi();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Belum Ada Kelas TA");
            }
            
        } else if (source.equals(view.getbtnHapusDosen())){ // Menu Hapus Dosen Kelas TA
            if(dosen.getKelasTA() != null && view.getIndexHapusDosen() >= 0 && dosen != dosen.getKelasTA().getTimDosen().get(view.getIndexHapusDosen())){
                int response = JOptionPane.showConfirmDialog(view,"Hapus Dosen dari Kelas TA?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    dosen.getKelasTA().deleteDosen(view.getIndexHapusDosen());
                    JOptionPane.showMessageDialog(view,"Dosen Berhasil Dihapus");
                }
            } else if(dosen == dosen.getKelasTA().getTimDosen().get(view.getIndexHapusDosen())){
                JOptionPane.showMessageDialog(view,"Tidak Bisa Menghapus Pemilik Kelas");
            } else {
                JOptionPane.showMessageDialog(view,"Anda Belum Membuat Kelas TA");
            }
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        } else if (source.equals(view.getbtnHapusKelasTA())){ //Menu Hapus Kelas TA
            if(dosen.getKelasTA() != null){
                int response = JOptionPane.showConfirmDialog(view,"Hapus Kelas TA?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    int idx = 0;
                    for(KelasTugasAkhir KTA : model.getDaftarKelas()){
                        if(KTA == dosen.getKelasTA()){
                            break;
                        } else {
                            idx++;
                        }
                    }
                    model.getDaftarKelas().remove(model.getKelas(idx));
                    dosen.DeleteKelasTA();
                    JOptionPane.showMessageDialog(view,"Kelas TA Berhasil Dihapus");
                }
            } else {
                JOptionPane.showMessageDialog(view,"Anda Belum Membuat Kelas TA");
            }
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        }else if(source.equals(view.getbtnUbahPass())){ // Menu Ubah Password
            if(!view.getPasswordBaru().equals("") && view.getPasswordLama().equals(dosen.getPassword()) && view.getKonfirmPassword().equals(view.getPasswordBaru())){
                int response = JOptionPane.showConfirmDialog(view,"Ubah Password ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    dosen.setPassword(view.getPasswordBaru());
                    JOptionPane.showMessageDialog(view,"Password Berhasil Diubah");
                }
            } else if (view.getPasswordBaru().equals("")){
                JOptionPane.showMessageDialog(view,"Password Tidak Boleh Kosong");
            } else{
                JOptionPane.showMessageDialog(view,"Password Salah");
            }
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        }else if(source.equals(view.getbtnTerimaKonfirmasi())){ // Menu Terima Konfirmasi
            if(view.getIndexKonfirmasi() >= 0 && model.getDaftarMahasiswa() != null){
                int flag = -1;
                Mahasiswa mhs = null;
                for(int idx = 0; idx < model.getDaftarMahasiswa().size(); idx++){
                    if(model.getDaftarMahasiswa().get(idx).getTugasAkhir() != null && model.getDaftarMahasiswa().get(idx).getTugasAkhir().getQueue() != null){
                        if(model.getDaftarMahasiswa().get(idx).getTugasAkhir().getQueue().contains(dosen)){
                            flag++;
                            if(flag == view.getIndexKonfirmasi()){
                                mhs = model.getDaftarMahasiswa().get(idx);
                                break;
                            }
                        }
                    }
                }
                int response = JOptionPane.showConfirmDialog(view,"Terima Pengajuan Dosen Pembimbing ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    mhs.getTugasAkhir().deleteQueDOsen(dosen);
                    mhs.getTugasAkhir().addPembimbing(dosen);
                    JOptionPane.showMessageDialog(view,"Pengajuan Dosen Pembimbing Diterima");
                }
            } else {
                JOptionPane.showMessageDialog(view,"Tidak Ada Pengajuan Dosen Pembimbing!");
            }
            
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        }else if(source.equals(view.getbtnTolakKonfirmasi())){ // Menu tolak konfirmasi
            if(view.getIndexKonfirmasi() >= 0 && model.getDaftarMahasiswa() != null){
                int flag = -1;
                Mahasiswa mhs = null;
                for(int idx = 0; idx < model.getDaftarMahasiswa().size(); idx++){
                    if(model.getDaftarMahasiswa().get(idx).getTugasAkhir() != null && model.getDaftarMahasiswa().get(idx).getTugasAkhir().getQueue() != null){
                        if(model.getDaftarMahasiswa().get(idx).getTugasAkhir().getQueue().contains(dosen)){
                            flag++;
                            if(flag == view.getIndexKonfirmasi()){
                                mhs = model.getDaftarMahasiswa().get(idx);
                                break;
                            }
                        }
                    }
                }
                int response = JOptionPane.showConfirmDialog(view,"Tolak Pengajuan Dosen Pembimbing ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    mhs.getTugasAkhir().deleteQueDOsen(dosen);
                    JOptionPane.showMessageDialog(view,"Pengajuan Dosen Pembimbing Ditolak!");
                }
            } else {
                JOptionPane.showMessageDialog(view,"Tidak Ada Pengajuan Dosen Pembimbing!");
            }
            
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        }else if(source.equals(view.getbtnHapusMhs())){ // Menu Hapus Mahasiswa
            if(dosen.getKelasTA() != null && view.getIndexHapusMhs() >= 0 ){
                int response = JOptionPane.showConfirmDialog(view,"Hapus Mahasiswa dari Kelas TA?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    dosen.getKelasTA().getDaftarMahasiswa().get(view.getIndexHapusMhs()).getTugasAkhir().DeleteQueue();
                    dosen.getKelasTA().getDaftarMahasiswa().get(view.getIndexHapusMhs()).getTugasAkhir().DeleteDosbing();
                    dosen.getKelasTA().getDaftarMahasiswa().get(view.getIndexHapusMhs()).setCekKelasTA(false);
                    dosen.getKelasTA().deleteMhs(view.getIndexHapusMhs());
                    JOptionPane.showMessageDialog(view,"Mahasiswa Berhasil Dihapus");
                }
            } else if(dosen.getKelasTA() != null){
                JOptionPane.showMessageDialog(view,"Tidak Ada Mahasiswa Yang Terdaftar!");
            } else {
                JOptionPane.showMessageDialog(view,"Anda Belum Membuat Kelas TA!");
            }
            view.ResetView();
            view.setListIkutKelas(model.getAllKelasTA(dosen));
            view.setListBuatTA(model.getAllKelasTA());
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
        }else if(source.equals(view.getbtnLogOut())){ // Menu LogOut
            view.dispose();
            MenuLogin.setVisible(true);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source.equals(view.getListKelasBuatKelas())){
            view.ResetView();
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            TabelKonfirmasi();
            try {
                KelasTugasAkhir KTA = model.getKelas(view.getIndexListBuatKelas());
                if(KTA.getDaftarMahasiswa()!= null){
                    for(Mahasiswa Mhs : KTA.getDaftarMahasiswa()){
                        view.setTabelMhsBuatKelas(Mhs.getNIM(), Mhs.getNama());
                    }
                }
                if(KTA.getTimDosen() != null){
                    for(Dosen dsn : KTA.getTimDosen()){
                        view.setTabelDosenBuatKelas(dsn.getNID(), dsn.getNama());
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(view, "Belum Ada Kelas TA");
            }
        } else if (source.equals(view.getListIkutKelas())){
            view.ResetView();
            TabelTambahDosen();
            TabelHapusDosen();
            TabelHapusMhs();
            TabelDosenKelas();
            TabelMhsKelas();
            try {
                KelasTugasAkhir KTA = model.getIkutKelas(dosen,view.getIndexListIkutKelas());
                if(KTA.getDaftarMahasiswa()!= null){
                    for(Mahasiswa Mhs : KTA.getDaftarMahasiswa()){
                        view.setTabelMhsKelasIkut(Mhs.getNIM(), Mhs.getNama());
                    }
                }
                if(KTA.getTimDosen() != null){
                    for(Dosen dsn : KTA.getTimDosen()){
                        view.setTabelDosenKelasIkut(dsn.getNID(), dsn.getNama());
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(view, "Belum Ada Kelas TA");
            }
        }
    }
    
}
