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
public class UcitajListuIgaraSO extends ApstraktnaGenerickaOperacija{
    
    List<DrustvenaIgra> listaIgara;

    public List<DrustvenaIgra> getListaIgara() {
        return listaIgara;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        // todo
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaIgara = repo.vratiSve(new DrustvenaIgra(), "");
    }
    
}
