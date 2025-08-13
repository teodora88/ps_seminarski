/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.glavni;

import forme.ClanGlavnaForma;
import forme.DrustvenaIgraGlavnaForma;
import forme.GlavnaForma;
import forme.PotvrdaGlavnaForma;
import forme.PrijavaForma;
import kontroleri.ClanGlavnaFormaKontroler;
import kontroleri.DrustvenaIgraGlavnaFormaKontroler;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.PotvrdaGlavnaFormaKontroler;
import kontroleri.PrijavaKontroler;

/**
 *
 * @author T440s
 */
public class GlavniKontroler {
    
    private static GlavniKontroler instanca;
    private PrijavaKontroler prijavaKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private ClanGlavnaFormaKontroler clanGlavnaFormaKontroler;
    private DrustvenaIgraGlavnaFormaKontroler diGlavnaFormaKontroler;
    private PotvrdaGlavnaFormaKontroler potGlavnaFormaKontroler;

    private GlavniKontroler() {
    }

    public static GlavniKontroler getInstanca() {
        if(instanca == null){
            instanca = new GlavniKontroler();
        }
        return instanca;
    }

    public void otvoriPrijavaForma() {
        prijavaKontroler = new PrijavaKontroler(new PrijavaForma());
        prijavaKontroler.otvoriPrijavaFormu();
    }
    
    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriGlavnuFormu();
    }

    public void otvoriClanGlavnuFormu() {
        clanGlavnaFormaKontroler = new ClanGlavnaFormaKontroler(new ClanGlavnaForma());
        clanGlavnaFormaKontroler.otvoriClanGlavnuFormu();
    }

    public void otvoriDrustvenaIgraGlavnaForma() {
        diGlavnaFormaKontroler = new DrustvenaIgraGlavnaFormaKontroler(new DrustvenaIgraGlavnaForma());
        diGlavnaFormaKontroler.otvoriDrustvenaIgraGlavnaForma();
    }

    public void otvoriPotvrdaGlavnaForma() {
        potGlavnaFormaKontroler = new PotvrdaGlavnaFormaKontroler(new PotvrdaGlavnaForma());
        potGlavnaFormaKontroler.otvoriPotvrdaGlavnaForma();
    }

    
    
}
