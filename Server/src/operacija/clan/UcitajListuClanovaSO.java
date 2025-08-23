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
public class UcitajListuClanovaSO extends ApstraktnaGenerickaOperacija{
    
    List<Clan> listaClanova; 

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String uslov) throws Exception {
        listaClanova = repo.vratiSve(new Clan(), " JOIN grad ON clan.grad = grad.postanskiBroj ORDER BY clan.clanId ASC");
    }

    public List<Clan> getListaClanova() {
        return listaClanova;
    }
    
}
