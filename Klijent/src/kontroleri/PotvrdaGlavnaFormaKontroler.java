/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.PotvrdaOIznajmljivanju;
import domen.StavkaPotvrdeOIznajmljivanju;
import forme.PotvrdaGlavnaForma;
import forme.modeli.PotvrdaMT;
import forme.modeli.StavkaMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Komunikacija;
import kontroleri.glavni.GlavniKontroler;

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

    public void pripremiFormu() {
        
        List<PotvrdaOIznajmljivanju> listaPotvrda = Komunikacija.getInstanca().ucitajListuPotvrda();
        PotvrdaMT potvrdaMT = new PotvrdaMT(listaPotvrda);
        potGlavaForma.getTblListaPotvrda().setModel(potvrdaMT);
        
        List<StavkaPotvrdeOIznajmljivanju> listaStavki = new ArrayList<>();
        StavkaMT stavkaMT = new StavkaMT(listaStavki);
        potGlavaForma.getTblStavkeIzabranePotvrde().setModel(stavkaMT);
        
    }

    private void dodajOsluskivace() {
        
        potGlavaForma.getTblListaPotvrda().addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int red = potGlavaForma.getTblListaPotvrda().getSelectedRow();
                
                if(red != -1){
                    PotvrdaMT potMT = (PotvrdaMT) potGlavaForma.getTblListaPotvrda().getModel();
                    PotvrdaOIznajmljivanju pot = potMT.getListaPotvrda().get(red);
                    
                    List<StavkaPotvrdeOIznajmljivanju> listaStavki = Komunikacija.getInstanca().usitajListuStavki(pot.getPotvrdaID());
                    StavkaMT stMT = new StavkaMT(listaStavki);
                    potGlavaForma.getTblStavkeIzabranePotvrde().setModel(stMT);
                }
                
            }
        });
        
        potGlavaForma.dodajOsluskivacDodajNovu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlavniKontroler.getInstanca().otvoriPotvrdaFormuDodaj(potGlavaForma);
            }
        });
        
        
    }
    
}
