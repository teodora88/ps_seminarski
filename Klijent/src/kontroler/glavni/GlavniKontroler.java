/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.glavni;

import domen.Radnik;
import forme.ClanForma;
import forme.ClanGlavnaForma;
import forme.DrustvenaIgraForma;
import forme.DrustvenaIgraGlavnaForma;
import forme.GlavnaForma;
import forme.PotvrdaForma;
import forme.PotvrdaGlavnaForma;
import forme.PrijavaForma;
import java.util.HashMap;
import javax.swing.JFrame;
import kontroler.clan.DodajClanaKontroler;
import kontroler.clan.ClanGlavnaFormaKontroler;
import kontroler.clan.DetaljiClanaKontroler;
import kontroler.igra.DetaljiIgraKontroler;
import kontroler.potvrda.DodajPotvrduKontroler;
import kontroler.igra.DrustvenaIgraGlavnaFormaKontroler;
import kontroler.glavnaforma.GlavnaFormaKontroler;
import kontroler.clan.IzmeniClanaKontroler;
import kontroler.potvrda.IzmeniPotvrduKontroler;
import kontroler.potvrda.PotvrdaGlavnaFormaKontroler;
import kontroler.prijava.PrijavaKontroler;

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
    private DodajClanaKontroler dodajClanaKontroler;
    private IzmeniClanaKontroler izmeniClanaKontroler;
    private HashMap<String, Object> mapa;
    private DodajPotvrduKontroler dodajPotKontroler;
    private IzmeniPotvrduKontroler izmeniPotKon;
    private DetaljiClanaKontroler detaljiClanaKontroler;
    private DetaljiIgraKontroler detIgraKon;

    private GlavniKontroler() {
        mapa = new HashMap<>();
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

    public void otvoriClanFormuDodajNovog(JFrame parent) {
        dodajClanaKontroler = new DodajClanaKontroler(new ClanForma(parent, true));
        dodajClanaKontroler.otvoriClanFormu();
    }

    public void osveziTabeluClanova() {
        clanGlavnaFormaKontroler.pripremiFormu();
    }

    public void otvoriClanFormuZaIzmenu(ClanGlavnaForma clanGlavnaForma) {
        izmeniClanaKontroler = new IzmeniClanaKontroler(new ClanForma(clanGlavnaForma, true));
        izmeniClanaKontroler.otvoriClanFormu();
    }

    public void dodajParametre(String s, Object o){
        mapa.put(s,o);
    }
    
    public Object vratiParametre(String s){
        return mapa.get(s);
    }

    public void otvoriPotvrdaFormuDodaj(PotvrdaGlavnaForma potGlavaForma) {
        dodajPotKontroler = new DodajPotvrduKontroler(new PotvrdaForma(potGlavaForma, true));
        dodajPotKontroler.otvoriPotvrdaFormu();
    }

    public void osveziTabeluPotvrda() {
        potGlavnaFormaKontroler.pripremiFormu();
    }

    public void otvoriPotvrdaFormuZaIzmenu(PotvrdaGlavnaForma potGlavaForma) {
        izmeniPotKon = new IzmeniPotvrduKontroler(new PotvrdaForma(potGlavaForma, true));
        izmeniPotKon.otvoriPotvrdaFormu();
    }

    public void otvoriClanFormuZaDetalje(ClanGlavnaForma clanGlavnaForma) {
        detaljiClanaKontroler = new DetaljiClanaKontroler(new ClanForma(clanGlavnaForma, true));
        detaljiClanaKontroler.otvoriClanFormu();
    }

    public void otvoriIgraFormuZaDetalje(DrustvenaIgraGlavnaForma diGlavnaForma) {
        detIgraKon = new DetaljiIgraKontroler(new DrustvenaIgraForma(diGlavnaForma, true));
        detIgraKon.otvoriIgraFormu();
    }
    
}
