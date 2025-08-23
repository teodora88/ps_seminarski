/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.potvrda;

import domen.PotvrdaOIznajmljivanju;
import domen.StavkaPotvrdeOIznajmljivanju;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class DodajPotvrduSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {

        if (param == null || !(param instanceof PotvrdaOIznajmljivanju)) {
            throw new Exception("Sistem ne može da zapamti potvrdu o iznajmljivanju.");
        }

        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) param;
      
        if (pot.getClan() == null || pot.getClan().getClanID() == null) {
            throw new Exception("Sistem ne može da zapamti potvrdu o iznajmljivanju. Član nije ispravan.");
        }
        
        if (pot.getRadnik() == null || pot.getRadnik().getRadnikID() == null) {
            throw new Exception("Sistem ne može da zapamti potvrdu o iznajmljivanju. Radnik nije ispravan.");
        }
        
        if (pot.getListaStavki() == null || pot.getListaStavki().isEmpty()) {
            throw new Exception("Sistem ne može da zapamti potvrdu o iznajmljivanju. Potvrda mora da sadrži bar jednu stavku.");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) param;
        Long potID = Long.valueOf(repo.dodajIVratiPK(pot));

        List<StavkaPotvrdeOIznajmljivanju> listaStavki = pot.getListaStavki();
        for (StavkaPotvrdeOIznajmljivanju stavka : listaStavki) {
            stavka.setPotvrdaID(potID);
            repo.dodaj(stavka);
        }

    }

}
