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
public class UcitajPotvrduSO extends ApstraktnaGenerickaOperacija {

    PotvrdaOIznajmljivanju pot;

    public PotvrdaOIznajmljivanju getPot() {
        return pot;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null) {
            throw new Exception("ID potvrde ne sme biti null");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Long potID = (Long) param;

        String uslovPotvrda = " JOIN clan ON potvrdaoiznajmljivanju.clan = clan.clanID "
                            + " JOIN radnik ON potvrdaoiznajmljivanju.radnik = radnik.radnikID "
                            + " WHERE potvrdaID = " + potID
                            + " ORDER BY potvrdaID ASC";

        List<PotvrdaOIznajmljivanju> listaPotvrda = repo.vratiSve(new PotvrdaOIznajmljivanju(), uslovPotvrda);

        if (listaPotvrda.isEmpty()) {
            throw new Exception("Potvrda sa odabranim ID ne postoji");
        }

        pot = listaPotvrda.get(0);
        String uslovStavka = " JOIN drustvenaIgra ON drustvenaigra.igraID = stavkapotvrdeoiznajmljivanju.drustvenaIgra"
                + " WHERE potvrdaOIznajmljivanju = " + potID;
        List<StavkaPotvrdeOIznajmljivanju> listaStavki = repo.vratiSve(new StavkaPotvrdeOIznajmljivanju(), uslovStavka);

        pot.setListaStavki(listaStavki);
    }

}
