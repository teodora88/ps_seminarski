/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.PrijavaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author T440s
 */
public class PrijavaKontroler {
    
    private final PrijavaForma prijavaForma;

    public PrijavaKontroler(PrijavaForma prijavaForma) {
        this.prijavaForma = prijavaForma;
        dodajAkcioniOsluskivac();
    }

    private void dodajAkcioniOsluskivac() {
        // addActionListener()
        
        prijavaForma.dodajOsluskivacPrijave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
    }
    
}
