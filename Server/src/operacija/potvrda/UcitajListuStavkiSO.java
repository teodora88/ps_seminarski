/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.potvrda;

import domen.StavkaPotvrdeOIznajmljivanju;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class UcitajListuStavkiSO extends ApstraktnaGenerickaOperacija{
    
    List<StavkaPotvrdeOIznajmljivanju> listaStavki;

    public List<StavkaPotvrdeOIznajmljivanju> getListaStavki() {
        return listaStavki;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        // todo
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN drustvenaIgra ON drustvenaigra.igraID = stavkapotvrdeoiznajmljivanju.drustvenaIgra" +
                " WHERE potvrdaOIznajmljivanju = " + (Long)param + " ";
        listaStavki = repo.vratiSve(new StavkaPotvrdeOIznajmljivanju(), uslov);
    }
    
}
