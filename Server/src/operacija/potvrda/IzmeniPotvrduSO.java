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
public class IzmeniPotvrduSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PotvrdaOIznajmljivanju)) {
            throw new Exception("Sistem ne moze da zapamti potvrdu o iznajmljivnaju.");
        }

        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) param;

        if (pot.getListaStavki() == null || pot.getListaStavki().isEmpty()) {
            throw new Exception("Sistem ne može da zapamti potvrdu o iznajmljivanju. Potvrda mora da sadrži bar jednu stavku.");
        }


    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) param;

        

        String uslov = " JOIN drustvenaIgra ON drustvenaigra.igraID = stavkapotvrdeoiznajmljivanju.drustvenaIgra"
                + " WHERE potvrdaOIznajmljivanju = " + pot.getPotvrdaID() + " ";
       

        List<StavkaPotvrdeOIznajmljivanju> stareStavke = repo.vratiSve(new StavkaPotvrdeOIznajmljivanju(), uslov);

        for (StavkaPotvrdeOIznajmljivanju stv : stareStavke) {
            repo.obrisi(stv);
        }

        List<StavkaPotvrdeOIznajmljivanju> novestavke = pot.getListaStavki();
        
        for (StavkaPotvrdeOIznajmljivanju stavka : novestavke) {
            repo.dodaj(stavka);
        }
        
        repo.izmeni(pot);
    }

}
