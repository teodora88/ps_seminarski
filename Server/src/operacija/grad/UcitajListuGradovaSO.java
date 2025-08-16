/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.grad;

import domen.Grad;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class UcitajListuGradovaSO extends ApstraktnaGenerickaOperacija{
    
    List<Grad> listaGradova;

    public List<Grad> getListaGradova() {
        return listaGradova;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaGradova = repo.vratiSve(new Grad(), null);
    }
    
}
