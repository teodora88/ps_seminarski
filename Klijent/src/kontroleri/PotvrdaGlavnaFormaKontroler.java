/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.PotvrdaGlavnaForma;

/**
 *
 * @author T440s
 */
public class PotvrdaGlavnaFormaKontroler {
    
    private final PotvrdaGlavnaForma potGlavaForma;

    public PotvrdaGlavnaFormaKontroler(PotvrdaGlavnaForma potGlavaForma) {
        this.potGlavaForma = potGlavaForma;
    }

    public void otvoriPotvrdaGlavnaForma() {
        potGlavaForma.setVisible(true);
    }
    
}
