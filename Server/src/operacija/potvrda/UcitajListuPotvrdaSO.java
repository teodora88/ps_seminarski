/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.potvrda;

import domen.PotvrdaOIznajmljivanju;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class UcitajListuPotvrdaSO extends ApstraktnaGenerickaOperacija {

    List<PotvrdaOIznajmljivanju> listaPotvrda;

    public List<PotvrdaOIznajmljivanju> getListaPotvrda() {
        return listaPotvrda;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        //todo
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaPotvrda = repo.vratiSve(new PotvrdaOIznajmljivanju(),
                " JOIN clan ON potvrdaoiznajmljivanju.clan = clan.clanID "
                + " JOIN radnik ON potvrdaoiznajmljivanju.radnik = radnik.radnikID ");
    }

}
