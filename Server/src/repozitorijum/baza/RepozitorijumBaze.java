/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.baza;

import repozitorijum.Repozitorijum;

/**
 *
 * @author T440s
 */
public interface RepozitorijumBaze<T> extends Repozitorijum<T>{
    
    // ovo je na vezbama DbRepository
    
    // skladiste sluzi za CRUD operacije, skladisteBazePodataka sluzi za povezivanje baze
    
    default public void uspostaviKonekciju() throws Exception{
        Konekcija.getInstanca().getKonekcija();
    }
    
    default public void raskiniKonekciju() throws Exception{
        Konekcija.getInstanca().getKonekcija().close();
    }
    
    default public void potvrdiTransakciju() throws Exception{
        Konekcija.getInstanca().getKonekcija().commit();
    }
    
    default public void ponistiTransakciju() throws Exception{
        Konekcija.getInstanca().getKonekcija().rollback();
    }
    
}
