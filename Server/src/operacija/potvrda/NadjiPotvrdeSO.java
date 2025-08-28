/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.potvrda;

import domen.Clan;
import domen.PotvrdaOIznajmljivanju;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class NadjiPotvrdeSO extends ApstraktnaGenerickaOperacija {
    
    List<PotvrdaOIznajmljivanju> listaPotvrda;

    public List<PotvrdaOIznajmljivanju> getListaPotvrda() {
        return listaPotvrda;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        Clan c = (Clan) param;

        String uslov = " JOIN clan ON potvrdaoiznajmljivanju.clan = clan.clanID "
                + " JOIN radnik ON potvrdaoiznajmljivanju.radnik = radnik.radnikID WHERE 1=1 ";

        if (c.getIme() != null && !c.getIme().isEmpty()) {
            uslov += " AND LOWER(clan.ime) LIKE LOWER('" + c.getIme() + "%')";
        }

        if (c.getPrezime() != null && !c.getPrezime().isEmpty()) {
            uslov += " AND LOWER(clan.prezime) LIKE LOWER('" + c.getPrezime() + "%')";
        }

        uslov += " ORDER BY potvrdaID ASC ";
        
        listaPotvrda = repo.vratiSve(new PotvrdaOIznajmljivanju(), uslov);

    }

}
