/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.PotvrdaOIznajmljivanju;
import forme.PotvrdaGlavnaForma;
import forme.modeli.ClanMT;
import forme.modeli.PotvrdaMT;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author T440s
 */
public class PotvrdaGlavnaFormaKontroler {
    
    private final PotvrdaGlavnaForma potGlavaForma;

    public PotvrdaGlavnaFormaKontroler(PotvrdaGlavnaForma potGlavaForma) {
        this.potGlavaForma = potGlavaForma;
        dodajOsluskivace();
    }

    public void otvoriPotvrdaGlavnaForma() {
        pripremiFormu();
        potGlavaForma.setVisible(true);
    }

    private void pripremiFormu() {
        
        List<PotvrdaOIznajmljivanju> listaPotvrda = Komunikacija.getInstanca().ucitajListuPotvrda();
        PotvrdaMT potvrdaMT = new PotvrdaMT(listaPotvrda);
        potGlavaForma.getTblListaPotvrda().setModel(potvrdaMT);
        
    }

    private void dodajOsluskivace() {
        //todo
    }
    
}
