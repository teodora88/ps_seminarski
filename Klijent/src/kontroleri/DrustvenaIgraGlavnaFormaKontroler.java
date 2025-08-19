/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.DrustvenaIgra;
import forme.DrustvenaIgraGlavnaForma;
import forme.modeli.IgraMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author T440s
 */
public class DrustvenaIgraGlavnaFormaKontroler {
    
    private final DrustvenaIgraGlavnaForma diGlavnaForma;

    public DrustvenaIgraGlavnaFormaKontroler(DrustvenaIgraGlavnaForma diGlavnaForma) {
        this.diGlavnaForma = diGlavnaForma;
        dodajOsluskivace();
    }

    public void otvoriDrustvenaIgraGlavnaForma() {
        pripremiFormu();
        diGlavnaForma.setVisible(true);
    }
    
    private void pripremiFormu() {
        
        List<DrustvenaIgra> listaIgara = Komunikacija.getInstanca().ucitajListuIgara();
        IgraMT igraMT = new IgraMT(listaIgara);
        diGlavnaForma.getTblDrusteveIgre().setModel(igraMT);
        
    }
    
    private void dodajOsluskivace() {
        
        diGlavnaForma.dodajOsluskivacPretraga(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String naziv = diGlavnaForma.getTxtPretraga().getText().trim();
                
                IgraMT igraMT = (IgraMT) diGlavnaForma.getTblDrusteveIgre().getModel(); 
                igraMT.pretrazi(naziv);
                
            }
        });
        
        diGlavnaForma.dodajOsluskivacResetuj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
        
    }
    
}
