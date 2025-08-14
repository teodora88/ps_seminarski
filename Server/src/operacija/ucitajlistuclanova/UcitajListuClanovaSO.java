/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.ucitajlistuclanova;

import domen.Clan;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class UcitajListuClanovaSO extends ApstraktnaGenerickaOperacija{
    
    List<Clan> listaClanova; // posto je izvrsiOp void metoda, a mi treba da vratimo listu, stavljamo je u atribute

    @Override
    protected void preduslovi(Object param) throws Exception {
        // todo
    }

    @Override
    protected void izvrsiOperaciju(Object param, String uslov) throws Exception {
        listaClanova = repo.vratiSve(new Clan(), "");
    }

    public List<Clan> getListaClanova() {
        return listaClanova;
    }
    
}
