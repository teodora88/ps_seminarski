/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clan;

import domen.Clan;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class NadjiClanoveSO extends ApstraktnaGenerickaOperacija {

    List<Clan> listaClanova;

    public List<Clan> getListaClanova() {
        return listaClanova;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {

        if (param == null || !(param instanceof Clan)) {
            throw new Exception("Parametar mora biti instanca klase Clan!");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Clan c = (Clan) param;

        String uslov = " JOIN grad ON clan.grad = grad.postanskiBroj WHERE 1=1";

        if (c.getIme() != null && !c.getIme().isEmpty()) {
            uslov += " AND LOWER(clan.ime) LIKE LOWER('" + c.getIme() + "%')";
        }
        if (c.getPrezime() != null && !c.getPrezime().isEmpty()) {
            uslov += " AND clan.prezime LIKE '%" + c.getPrezime() + "%'";
        }

        uslov += " ORDER BY clan.clanID ASC";

        listaClanova = repo.vratiSve(new Clan(), uslov);

    }

}
