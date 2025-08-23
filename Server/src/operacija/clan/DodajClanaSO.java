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
public class DodajClanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if (param == null || !(param instanceof Clan)) {
            throw new Exception("Sistem ne može da zapamti člana.");
        }

        Clan c = (Clan) param;

        if (c.getIme() == null || c.getIme().isEmpty()) {
            throw new Exception("Sistem ne može da zapamti člana. Ime člana je obavezno.");
        }

        if (c.getPrezime() == null || c.getPrezime().isEmpty()) {
            throw new Exception("Sistem ne može da zapamti člana. Prezime člana je obavezno.");
        }

        if (c.getDatumRodjenja() == null) {
            throw new Exception("Sistem ne može da zapamti člana. Datum rođenja je obavezan.");
        }

        if (c.getDatumUclanjenja() == null) {
            throw new Exception("Sistem ne može da zapamti člana. Datum učlanjenja je obavezan.");
        }

        if (c.getDatumRodjenja().after(c.getDatumUclanjenja())) {
            throw new Exception("Sistem ne može da zapamti člana. Datum učlanjenja ne može biti pre datuma rođenja.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        repo.dodaj((Clan) param);
    }

}
