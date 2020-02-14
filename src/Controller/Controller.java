/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.awt.event.*;
import GUI.*;
import Model.*;
import Database.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author MSI
 */
public class Controller extends MouseAdapter implements ActionListener  {
    private LoginMenu MenuLogin;
    private Aplikasi Model;
    private Database db;
    
    public Controller(){
        MenuLogin = new LoginMenu();
        Model = new Aplikasi();
        
        Model.loadAllDosenAplikasi();
       
        MenuLogin.addActionListener(this);
        MenuLogin.setVisible(true);
        MenuLogin.ResetView();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(MenuLogin.getLogin())) {
            String Username = MenuLogin.getUsername();
            String Password = MenuLogin.getPassword();
            MenuLogin.ResetView();
            if(Username.equals("Admin") && Password.equals("Admin123")){
                MenuLogin.setVisible(false);
                new AdminController(Model,MenuLogin);
            } else if (Model.SearchUser(Username,Password) instanceof Dosen){
                Dosen d = (Dosen) Model.SearchUser(Username,Password);
                MenuLogin.setVisible(false);
                new DosenController(Model,MenuLogin, d);
            } else if (Model.SearchUser(Username,Password) instanceof Mahasiswa){
                Mahasiswa Mhs = (Mahasiswa) Model.SearchUser(Username, Password);
                MenuLogin.dispose();
                new MahasiswaController(Model, MenuLogin, Mhs);
            } else {
                JOptionPane.showMessageDialog(MenuLogin,"Invalid Username or Password");
            }
        }
    }
}
