/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.glavni;

import domen.Radnik;
import forme.ClanForma;
import forme.ClanGlavnaForma;
import forme.DrustvenaIgraGlavnaForma;
import forme.GlavnaForma;
import forme.PotvrdaGlavnaForma;
import forme.PrijavaForma;
import javax.swing.JFrame;
import kontroleri.ClanFormaKontroler;
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
    private Radnik ulogovani;
    private PrijavaKontroler prijavaKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private ClanGlavnaFormaKontroler clanGlavnaFormaKontroler;
    private DrustvenaIgraGlavnaFormaKontroler diGlavnaFormaKontroler;
    private PotvrdaGlavnaFormaKontroler potGlavnaFormaKontroler;
    private ClanFormaKontroler clanFormaKontroler;

    private GlavniKontroler() {
    }

    public static GlavniKontroler getInstanca() {
        if(instanca == null){
            instanca = new GlavniKontroler();
        }
        return instanca;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Radnik getUlogovani() {
        return ulogovani;
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

    public void otvoriClanFormu(JFrame parent) {
        clanFormaKontroler = new ClanFormaKontroler(new ClanForma(parent, true));
        clanFormaKontroler.otvoriClanFormu();
    }

    
    
}
