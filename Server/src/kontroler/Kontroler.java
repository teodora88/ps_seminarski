/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import domen.DrustvenaIgra;
import domen.Grad;
import domen.PotvrdaOIznajmljivanju;
import domen.Radnik;
import domen.StavkaPotvrdeOIznajmljivanju;
import java.util.List;
import operacija.clan.DodajClanaSO;
import operacija.clan.IzmeniClanaSO;
import operacija.clan.ObrisiClanaSO;
import operacija.prijava.PrijavaSO;
import operacija.clan.UcitajListuClanovaSO;
import operacija.grad.UcitajListuGradovaSO;
import operacija.igra.UcitajListuIgaraSO;
import operacija.potvrda.DodajPotvrduSO;
import operacija.potvrda.UcitajListuPotvrdaSO;
import operacija.potvrda.UcitajListuStavkiSO;

/**
 *
 * @author T440s
 */
public class Kontroler {
    
    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if(instanca == null){
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Radnik prijava(Radnik r) throws Exception {
        
        PrijavaSO operacija = new PrijavaSO();
        operacija.izvrsi(r, null);
        System.out.println("klasa kontroler" + operacija.getRadnik());
        return operacija.getRadnik();
        
    }

    public List<Clan> ucitajListuClanova() throws Exception {
        
        UcitajListuClanovaSO operacija = new UcitajListuClanovaSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa kontroler" + operacija.getListaClanova());
        return operacija.getListaClanova();
        
    }

    public void obrisiClana(Clan c) throws Exception {
        ObrisiClanaSO operacija = new ObrisiClanaSO();
        operacija.izvrsi(c, null);
    }

    public List<Grad> ucitajListuGradova() throws Exception {
        
        UcitajListuGradovaSO operacija = new UcitajListuGradovaSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa kontroler: " + operacija.getListaGradova());
        return operacija.getListaGradova();
        
    }

    public void dodajClana(Clan c) throws Exception {
        DodajClanaSO operacija = new DodajClanaSO();
        operacija.izvrsi(c, null);
    }

    public void izmeniClana(Clan c) throws Exception {
        IzmeniClanaSO operacija = new IzmeniClanaSO();
        operacija.izvrsi(c, null);
    }

    public List<DrustvenaIgra> ucitajListuIgara() throws Exception {
        
        UcitajListuIgaraSO operacija = new UcitajListuIgaraSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa kontroler" + operacija.getListaIgara());
        return operacija.getListaIgara();
        
    }

    public List<PotvrdaOIznajmljivanju> ucitajListuPotvrda() throws Exception {
        
        UcitajListuPotvrdaSO operacija = new UcitajListuPotvrdaSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa kontroler " + operacija.getListaPotvrda());
        return operacija.getListaPotvrda();
        
    }

    public List<StavkaPotvrdeOIznajmljivanju> ucitajListuStavki(Long potvrdaID) throws Exception {
        UcitajListuStavkiSO operacija = new UcitajListuStavkiSO();
        operacija.izvrsi(potvrdaID, null);
        return operacija.getListaStavki();
    }

    public void dodajPotvrdu(PotvrdaOIznajmljivanju pot)throws Exception{
        DodajPotvrduSO operacija = new DodajPotvrduSO();
        operacija.izvrsi(pot, null);
    }
    
}
