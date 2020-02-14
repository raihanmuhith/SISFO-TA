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
public class TugasAkhir {
    private List<Dosen> Pembimbing;
    private String Judul;
    private List<Dosen> ReqPembimbing;
    private int nPembimbing = 0;
    
    public TugasAkhir(String Judul){
        this.Judul = Judul;
        Pembimbing = new ArrayList(2);
        ReqPembimbing = new ArrayList();
    }
    
    public List<Dosen> getQueue(){
        return ReqPembimbing;
    }
    
    public void setJudul(String Judul){
        this.Judul = Judul;
    }
    
    public String getJudul(){
        return Judul;
    }

    public int getnPembimbing() {
        return nPembimbing;
    }
    
    public void addPembimbing(Dosen dosen){
        Pembimbing.add(dosen);
        nPembimbing++;
        if(nPembimbing == 2){
            ReqPembimbing = new ArrayList();
        }
    }
    
    public void DeleteQueue(){
        ReqPembimbing = new ArrayList();
    }
    
    public void DeleteDosbing(){
        Pembimbing = new ArrayList();
        nPembimbing = 0;
    }
    
    public void addReqPembimbing(Dosen dosen){
        ReqPembimbing.add(dosen);
    }
    
    public Dosen getReqPembimbing(int index){
        return ReqPembimbing.get(index);
    }
    
    public Dosen getPembimbing(int index){
        return Pembimbing.get(index);
    }
    
    public Dosen getQueDosen(int index){
        return ReqPembimbing.get(index);
    }
    
    public void deleteQueDOsen(Dosen d){
        ReqPembimbing.remove(d);
    }
    
    public String[] getAllQueDosen(){
        String[] s = new String[ReqPembimbing.size()];
        for(int idx = 0; idx < ReqPembimbing.size(); idx++){
            s[idx] = ReqPembimbing.get(idx).getNID() + " - " + ReqPembimbing.get(idx).getNama();
        }
        return s;
    }
    
    public boolean isInQueue(Dosen D){
        boolean found = false;
        for(Dosen dsn : ReqPembimbing){
            if(D == dsn){
                found = true;
            }
        }
        return found;
    }
    
    public boolean isPembimbing(Dosen D){
        boolean found = false;
        for(Dosen dsn : Pembimbing){
            if(D == dsn){
                found = true;
            }
        }
        return found;
    }
}
