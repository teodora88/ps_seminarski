/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.glavni;

import forme.PrijavaForma;
import kontroleri.PrijavaKontroler;

/**
 *
 * @author T440s
 */
public class GlavniKontroler {
    
    private static GlavniKontroler instanca;
    private PrijavaKontroler prijavaKontroler;

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
    
}
