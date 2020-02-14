/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Model.*;
import GUI.*;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Hafidh Raditya
 */
public class Database {
    private Connection con;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Aplikasi model = null;
    
    public void connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/tubes_ta_sisfo"; ///nama database
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Connect Success");
        }catch(SQLException e){
            System.out.println("Connection Error");
            System.out.println(e);
        }
    }
    
    public void saveDosen(Dosen d){
        try{
            String query = "insert into Dosen values('";
                query += d.getUsername()+"','";
                query += d.getPassword()+"','";
                query += d.getNama()+"','";
                query += d.getNID()+"','";
                query += d.getKodeDosen()+"','";
                query += d.getEmail()+"','";
                query += d.getTopik()+"','";
                query += d.getGender()+"')";
                
            Statement s = con.createStatement();
            s.execute(query);
            System.out.println("Saving Success");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"NID tidak boleh sama");
            System.out.println(e);
        }
    }
    
    public void deleteDosen(Dosen d){
        try{
            String query = "delete from Dosen where NID = '"+d.getNID()+"'";
            Statement s = con.createStatement();
            System.out.println("database sukse");
            s.execute(query);
            System.out.println("Delete Success");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"NID tidak ditemukan");
            System.out.println(e);
        }
    }
    
    public Dosen loadDosen(String NID){
        try{
            
            String query = "select * from Dosen where NID = '"+NID+"'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            
            Dosen d = null;
            while (rs.next()){                 
                String Nama =rs.getString(1); 
                String KodeDosen = rs.getString(3);
                String Email = rs.getString(4);
                String Topik = rs.getString(5);
                String Gender= rs.getString(6);
                d = new Dosen(Nama,KodeDosen,NID,Gender,Email,Topik);             
            }             
            return d;
        }catch(SQLException e){
            return null;
        }
    }
    
    public ArrayList<Dosen> loadAllDosen(){
        try{             
            ArrayList<Dosen> AllDosen =new ArrayList();             
            String query="select * from Dosen" ;             
            Statement s=con.createStatement();             
            ResultSet rs=s.executeQuery(query);             
            Dosen d;             
            while (rs.next()){ 
                String Username = rs.getString(1);
                String password = rs.getString(2);
                String Nama=rs.getString(3);                 
                String NID=rs.getString(4);                 
                String KodeDosen=rs.getString(5);
                String Email = rs.getString(6);
                String Topik = rs.getString(7);
                String Gender = rs.getString(8);
                d = new Dosen(Nama,KodeDosen,NID,Gender,Email,Topik);      
                AllDosen.add(d);          
            }             
            return AllDosen;         
        }catch(SQLException se){             
            return null;         
        } 
    }
    
    public void ubahDosen(Dosen d){
        try{
            String query = "UPDATE dosen SET ";
                    query +=" Username = '"+d.getUsername()+"',";
                    query +=" Password = '"+d.getPassword()+"',";
                    query +=" Nama = '"+d.getNama()+"',";
                    query +=" Kode Dosen = '"+d.getKodeDosen()+"',";
                    query +=" Email = '"+d.getEmail()+"',";
                    query +=" Topik = '"+ d.getTopik()+"',";
                    query +=" Gender = '"+ d.getGender()+"'";
                    query +=" WHERE NID = '"+ d.getNID()+"'";
            Statement s = con.createStatement();
            System.out.println(query);
            s.execute(query);
            System.out.println("Saving Success");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"NID tidak ditemukan");
            System.out.println(e);
        }
    }
    
    public void saveMahasiswa(Mahasiswa m){
        try{
            String query = "insert into Mahasiswa values('";
                    query += m.getUsername()+"','";
                    query += m.getPassword()+"','";
                    query += m.getNama()+"','";
                    query += m.getNIM()+"','";
                    query += m.getGender()+"','";
                    query += m.getEmail()+"')";
            Statement s = con.createStatement();
            s.execute(query);
            System.out.println("Saving Success");
        }catch(SQLException e){
            System.out.println("x");
            JOptionPane.showMessageDialog(null,"NIM tidak boleh sama");
            System.out.println(e);
        }
    }
    
    public void deleteMahasiswa(Mahasiswa m){
        try{
            String query = "delete from Mahasiswa where NIM = '"+m.getNIM()+"'";
            Statement s = con.createStatement();
            s.execute(query);
            System.out.println("Delete Success");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"NIM tidak ditemukan");
            System.out.println(e);
        }
    }

    public ArrayList<String> loadAllNID(){
        try{
            ArrayList<String> AllNID = new ArrayList();             
            String query="select NID from Dosen" ;             
            Statement s=con.createStatement();             
            ResultSet rs=s.executeQuery(query);             
            while (rs.next()){                 
                String NID =rs.getString(1);                 
                               
                AllNID.add(NID);
            }
            return AllNID;
        }catch(SQLException e){
            return null;
        }
    }
    
    public ArrayList<String> loadAllNIM(){
        try{
            ArrayList<String> AllNIM = new ArrayList();             
            String query="select NIM from Mahasiswa" ;             
            Statement s=con.createStatement();             
            ResultSet rs=s.executeQuery(query);             
            while (rs.next()){                 
                String NIM =rs.getString(1);                 
                               
                AllNIM.add(NIM);
            }
            return AllNIM;
        }catch(SQLException e){
            return null;
        }
    }
    
    public ArrayList<Mahasiswa> loadAllMahasiswa(){
        try{             
            ArrayList<Mahasiswa> AllMahasiswa =new ArrayList();             
            String query="select * from Mahsiswa" ;             
            Statement s=con.createStatement();             
            ResultSet rs=s.executeQuery(query);             
            Mahasiswa m;             
            while (rs.next()){                 
                String Nama=rs.getString(1);                 
                String NIM=rs.getString(2);                 
                String Gender=rs.getString(3);
                String Email = rs.getString(4);
                m = new Mahasiswa(Nama, NIM,Gender, Email);                
                AllMahasiswa.add(m);             
            }             
            return AllMahasiswa;         
        }catch(SQLException se){             
            return null;         
        } 
    }
    
    public void saveKelasTA(KelasTugasAkhir TA){
        try{
            String query = "insert into 'kelas tugas akhir' values('";
                    query += "','";
                    query += "','";
                    query += TA.getTopik()+"')";
            Statement s = con.createStatement();
            s.execute(query);
            System.out.println("Saving Success");
        }catch(SQLException e){
            System.out.println("x");
            JOptionPane.showMessageDialog(null,"Topik tidak boleh sama");
            System.out.println(e);
        }
    }
    
    public Dosen getDosen(String index){
        try{                          
            String query="select * from Dosen where NID = '"+index+"'" ;             
            Statement s=con.createStatement();             
            ResultSet rs=s.executeQuery(query);             
            Dosen d = null;             
            while (rs.next()){ 
                String Username = rs.getString(1);
                String password = rs.getString(2);
                String Nama=rs.getString(3);                 
                String NID=rs.getString(4);                 
                String KodeDosen=rs.getString(5);
                String Email = rs.getString(6);
                String Topik = rs.getString(7);
                String Gender = rs.getString(8);
                d = new Dosen(Nama,KodeDosen,NID,Gender,Email,Topik);              
            }             
            return d;         
        }catch(SQLException se){             
            return null;         
        } 
    }
}




