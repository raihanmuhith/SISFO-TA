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
public class AdminController extends MouseAdapter implements ActionListener {
    private AdminMenu view;
    private Aplikasi model;
    private LoginMenu MenuLogin;
    
    public AdminController(Aplikasi model, LoginMenu MenuLogin){
        view = new AdminMenu();
        this.MenuLogin = MenuLogin;
        this.model = model;    

        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        view.ResetView();
        
        view.setListEditDosen(model.getListDosen());
        view.setListDelDosen(model.getListDosen());
        view.setListEditMhs(model.getListMahasiswa());
        view.setListDelMhs(model.getListMahasiswa());
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source.equals(view.getbtnDaftarAddDosen())){ // Add Dosen
            String Nama     = view.getNamaAddDosen();
            String NID      = view.getNIDAddDosen();
            String Kode     = view.getKodeAddDosen();
            String Email    = view.getEmailAddDosen(); 
            String Topik    = view.getTopikAddDosen();
            String Gender   = view.getGenderAddDosen();
            
            if(!model.checkNullDosen(NID, NID, Nama, NID, Kode, Topik, Gender, Email) && model.checkDuplicateNID(NID) == -1){
                int response = JOptionPane.showConfirmDialog(view,"Tambahkan Dosen ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    Dosen dosen = new Dosen(Nama, Kode, NID, Gender, Email, Topik);
                    model.addDosen(dosen);
                    JOptionPane.showMessageDialog(MenuLogin,"Dosen Berhasil Ditambahkan");
                    view.ResetView();
                }
            } else if (model.checkNullDosen(NID, NID, Nama, NID, Kode, Topik, Gender, Email)){
                JOptionPane.showMessageDialog(view,"Data Kosong");
                view.ResetView();
            } else {
                JOptionPane.showMessageDialog(view,"NID Sudah Terdaftar");
                view.ResetView();
            }
            view.setListEditDosen(model.getListDosen());
            view.setListDelDosen(model.getListDosen());
            view.setListEditMhs(model.getListMahasiswa());
            view.setListDelMhs(model.getListMahasiswa());
            
        } else if(source.equals(view.getbtnDaftarAddMhs())){ //Add Mahasiswa
            String Nama     = view.getNamaAddMhs();
            String NIM      = view.getNIMAddMhs();
            String Email    = view.getEmailAddMhs();
            String Gender   = view.getGenderAddMhs();
            
            if(!model.checkNullMhs(NIM, NIM, Nama, NIM, Gender, Email) && model.checkDuplicateNIM(NIM) == -1){
                int response = JOptionPane.showConfirmDialog(view,"Tambahkan Mahasiswa ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    Mahasiswa Mhs = new Mahasiswa(Nama, NIM, Gender, Email);
                    model.addMahasiswa(Mhs);
                    JOptionPane.showMessageDialog(MenuLogin,"Mahasiswa Berhasil Ditambahkan");
                    view.ResetView();
                }
            } else if (model.checkNullMhs(NIM, NIM, Nama, NIM, Gender, Email)){
                JOptionPane.showMessageDialog(view,"Data Kosong");
                view.ResetView();
            } else {
                JOptionPane.showMessageDialog(view,"NIM Sudah Terdaftar");
                view.ResetView();
            }
            view.setListEditDosen(model.getListDosen());
            view.setListDelDosen(model.getListDosen());
            view.setListEditMhs(model.getListMahasiswa());
            view.setListDelMhs(model.getListMahasiswa());
            
        } else if(source.equals(view.getbtnUpdateEditDosen())){ // Edit Dosen
            String Username = view.getUsernameEditDosen();
            String Password = view.getPasswordEditDosen();
            String Nama     = view.getNamaEditDosen();
            String NID      = view.getNIDEditDosen();
            String Kode     = view.getKodeEditDosen();
            String Topik    = view.getTopikEditDosen();
            String Gender   = view.getGenderEditDosen();
            String Email    = view.getEmailEditDosen();
            
            if(!model.checkNullDosen(Username, Password, Nama, NID, Kode, Topik, Gender, Email) &&
              (model.checkDuplicateNID(NID)== -1 || model.checkDuplicateNID(NID) == view.getSelectedIndexListEditDosen())){
                int response = JOptionPane.showConfirmDialog(view,"Update Dosen ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    model.updateDosen(view.getSelectedIndexListEditDosen(), Username, Password, Nama, Kode, Topik, Gender, Email);
                    JOptionPane.showMessageDialog(MenuLogin,"Dosen " + NID + " Berhasil Diupdate");
                    view.ResetView();
                }
            } else if(model.checkNullDosen(Username, Password, Nama, NID, Kode, Topik, Gender, Email)){
                JOptionPane.showMessageDialog(view,"Data Kosong");
                view.ResetView();
            } else {
                JOptionPane.showMessageDialog(view,"NID Sudah Terdaftar");
                view.ResetView();
            }
            view.setListEditDosen(model.getListDosen());
            view.setListDelDosen(model.getListDosen());
            view.setListEditMhs(model.getListMahasiswa());
            view.setListDelMhs(model.getListMahasiswa());
            
        } else if(source.equals(view.getbtnUpdateEditMhs())){ //Edit Mahasiswa
            String Username = view.getUsernameEditMhs();
            String Password = view.getPasswordEditMhs();
            String Nama     = view.getNamaEditMhs();
            String NIM      = view.getNIMEditMhs();
            String Email    = view.getEmailEditMhs();
            String Gender   = view.getGenderEditMhs();
            
            if(!model.checkNullMhs(NIM, NIM, Nama, NIM, Gender, Email) && 
              (model.checkDuplicateNIM(NIM) == -1 || model.checkDuplicateNIM(NIM) == view.getSelectedIndexListEditMhs())){
                int response = JOptionPane.showConfirmDialog(view,"Update Mahasiswa ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    model.updateMahasiswa(view.getSelectedIndexListEditMhs(), Username, Password, Nama, NIM,Gender, Email);
                    JOptionPane.showMessageDialog(MenuLogin,"Mahasiswa " + NIM + " Berhasil Diupdate");
                    view.ResetView();
                }
            } else if (model.checkNullMhs(NIM, NIM, Nama, NIM, Gender, Email)){
                JOptionPane.showMessageDialog(view,"Data Kosong");
                view.ResetView();
            } else {
                JOptionPane.showMessageDialog(view,"NIM Sudah Terdaftar");
                view.ResetView();
            }
            
            view.setListEditDosen(model.getListDosen());
            view.setListDelDosen(model.getListDosen());
            view.setListEditMhs(model.getListMahasiswa());
            view.setListDelMhs(model.getListMahasiswa());
            
        } else if(source.equals(view.getbtnDeleteDelDosen())){ //Delete Dosen
            int response = JOptionPane.showConfirmDialog(view,"Hapus Dosen ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(view,"Dosen " + model.getDosen(view.getSelectedIndexListDelDosen()).getNID() + " Berhasil di Hapus ");
                model.DeleteDosen(view.getSelectedIndexListDelDosen());
                view.ResetView();
            }
            view.setListEditDosen(model.getListDosen());
            view.setListDelDosen(model.getListDosen());
            view.setListEditMhs(model.getListMahasiswa());
            view.setListDelMhs(model.getListMahasiswa());
            
        } else if(source.equals(view.getbtnDeleteDelMhs())){ //Delete Mahasiswa
            int response = JOptionPane.showConfirmDialog(view,"Hapus Mahasiswa ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(view,"Mahasiswa " + model.getMahasiswa(view.getSelectedIndexListDelMhs()).getNIM() + " Berhasil di Hapus ");
                model.DeleteMahasiswa(view.getSelectedIndexListDelMhs());
                view.ResetView();
            }
            view.setListEditDosen(model.getListDosen());
            view.setListDelDosen(model.getListDosen());
            view.setListEditMhs(model.getListMahasiswa());
            view.setListDelMhs(model.getListMahasiswa());
            
        } else if(source.equals(view.getbtnLogOut())){
            view.dispose();
            MenuLogin.setVisible(true);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source.equals(view.getListEditDosen())){
            try {
                Dosen dosen = model.getDosen(view.getSelectedIndexListEditDosen());
                String Username = dosen.getUsername();
                String Password = dosen.getPassword();
                String Nama     = dosen.getNama();
                String NID      = dosen.getNID();
                String Kode     = dosen.getKodeDosen();
                String Topik    = dosen.getTopik();
                String Gender   = dosen.getGender();
                String Email    = dosen.getEmail();
                view.setAllEditDosen(Username, Password, Nama, NID, Kode, Topik, Gender, Email);
            } catch (Exception e){
                JOptionPane.showMessageDialog(view, "Tidak Ada Dosen Yang Terdaftar");
            }
        } else if (source.equals(view.getListDelDosen())){
            try {
                Dosen dosen = model.getDosen(view.getSelectedValueListDelDosen());
                String Username = dosen.getUsername();
                String Password = dosen.getPassword();
                String Nama     = dosen.getNama();
                String NID      = dosen.getNID();
                String Kode     = dosen.getKodeDosen();
                String Topik    = dosen.getTopik();
                String Gender   = dosen.getGender();
                String Email    = dosen.getEmail();
                view.setAllDelDosen(Username, Password, Nama, NID, Kode, Topik, Gender, Email);
            } catch (Exception e){
                JOptionPane.showMessageDialog(view, "Tidak Ada Dosen Yang Terdaftar");
            }
        } else if(source.equals(view.getListEditMahasiswa())){
            try {
                Mahasiswa mhs = model.getMahasiswa(view.getSelectedIndexListEditMhs());
                String Username = mhs.getUsername();
                String Password = mhs.getPassword();
                String Nama     = mhs.getNama();
                String NIM      = mhs.getNIM();
                String Gender   = mhs.getGender();
                String Email    = mhs.getEmail();
                view.setAllEditMhs(Username, Password, Nama, NIM, Gender, Email);
            } catch (Exception e){
                JOptionPane.showMessageDialog(view, "Tidak Ada Mahasiswa Yang Terdaftar");
            }
        } else if(source.equals(view.getListDelMhs())){
            try {
                Mahasiswa mhs = model.getMahasiswa(view.getSelectedIndexListDelMhs());
                String Username = mhs.getUsername();
                String Password = mhs.getPassword();
                String Nama     = mhs.getNama();
                String NIM      = mhs.getNIM();
                String Gender   = mhs.getGender();
                String Email    = mhs.getEmail();
                view.setAllDelMhs(Username, Password, Nama, NIM, Gender, Email);
            } catch (Exception e){
                JOptionPane.showMessageDialog(view, "Tidak Ada Dosen Yang Terdaftar");
            }
        }
    }   
}
