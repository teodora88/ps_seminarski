/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prijava;

import domen.Radnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class PrijavaSO extends ApstraktnaGenerickaOperacija{
    // PrijavaSO - prijava sistemska operacija 
    
    private Radnik radnik;

    public Radnik getRadnik() {
        return radnik;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Radnik)){
            throw new Exception("Sistem ne moze da prijavi radnika kluba po zadatim vrednostima.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        List<Radnik> sviRadnici = repo.vratiSve((Radnik) param, null);
        System.out.println("PrijavaSO" + sviRadnici);
        
        for(Radnik r : sviRadnici){
            if(r.equals((Radnik)param)){
                radnik = r;
                return;
            }
        }
        
        radnik  = null;
        
    }
    
}
