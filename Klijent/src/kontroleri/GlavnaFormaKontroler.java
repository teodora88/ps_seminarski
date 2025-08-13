/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kontroleri.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class GlavnaFormaKontroler {

    private final GlavnaForma glavnaForma;

    public GlavnaFormaKontroler(GlavnaForma glavnaForma) {
        this.glavnaForma = glavnaForma;
        dodajOsluskivace();
    }

    public void otvoriGlavnuFormu() {
        glavnaForma.setVisible(true);
    }

    private void dodajOsluskivace() {

        glavnaForma.dodajOsluskivacClan(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otvoriClanGlavnuFormu();
            }

            private void otvoriClanGlavnuFormu() {
                GlavniKontroler.getInstanca().otvoriClanGlavnuFormu();
            }
        });

        glavnaForma.dodajOsluskivacIgra(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otvoriDrustvenaIgraGlavanForma();
            }

            private void otvoriDrustvenaIgraGlavanForma() {
                GlavniKontroler.getInstanca().otvoriDrustvenaIgraGlavnaForma();
            }
        });
        
        glavnaForma.dodajOsluskivacPotvrda(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otvoriPotvrdaGlavanaForma();
            }

            private void otvoriPotvrdaGlavanaForma() {
                GlavniKontroler.getInstanca().otvoriPotvrdaGlavnaForma();
            }
        });

    }

}
