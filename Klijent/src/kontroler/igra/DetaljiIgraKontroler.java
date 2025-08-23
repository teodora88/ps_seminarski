/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.igra;

import domen.DrustvenaIgra;
import forme.DrustvenaIgraForma;
import kontroler.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class DetaljiIgraKontroler {
    
    private final DrustvenaIgraForma diForma;

    public DetaljiIgraKontroler(DrustvenaIgraForma diForma) {
        this.diForma = diForma;
    }

    public void otvoriIgraFormu() {
        pripremiFormu();
        diForma.setVisible(true);
    }

    private void pripremiFormu() {
        
        DrustvenaIgra di = (DrustvenaIgra) GlavniKontroler.getInstanca().vratiParametre("igra");
        diForma.getTxtID().setText(String.valueOf(di.getIgraID()));
        diForma.getTxtID().setEditable(false);
        diForma.getTxtID().setEnabled(false);
        diForma.getTxtNaziv().setText(di.getNaziv());
        diForma.getTxtNaziv().setEditable(false);
        diForma.getTxtNaziv().setEnabled(false);
        diForma.getTxtTip().setText(di.getTip());
        diForma.getTxtTip().setEditable(false);
        diForma.getTxtTip().setEnabled(false);
        diForma.getTxtOpis().setText(di.getOpis());
        diForma.getTxtOpis().setEditable(false);
        diForma.getTxtOpis().setEnabled(false);
        
    }
    
    
    
}
