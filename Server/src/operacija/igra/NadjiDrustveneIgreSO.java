/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.igra;

import domen.DrustvenaIgra;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class NadjiDrustveneIgreSO extends ApstraktnaGenerickaOperacija {

    List<DrustvenaIgra> listaIgara;

    public List<DrustvenaIgra> getListaIgara() {
        return listaIgara;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof DrustvenaIgra)) {
            throw new Exception("Parametar mora biti instanca klase DrustvenaIgra!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        DrustvenaIgra di = (DrustvenaIgra) param;

        String uslov = " WHERE 1=1";

        if (di.getNaziv() != null && !di.getNaziv().isEmpty()) {
            uslov += " AND LOWER(naziv) LIKE LOWER('" + di.getNaziv() + "%')";
        }
        
        listaIgara = repo.vratiSve(new DrustvenaIgra(), uslov);

    }

}
