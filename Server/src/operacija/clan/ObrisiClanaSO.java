/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clan;

import domen.Clan;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author T440s
 */
public class ObrisiClanaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Clan)){
            throw new Exception("Sistem ne može da obriše člana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        repo.obrisi((Clan)param);
    }
    
}
