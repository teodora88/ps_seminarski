/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Radnik;
import forme.PrijavaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author T440s
 */
public class PrijavaKontroler {
    
    private final PrijavaForma prijavaForma;

    public PrijavaKontroler(PrijavaForma prijavaForma) {
        this.prijavaForma = prijavaForma;
        dodajOsluskivac();
    }

    private void dodajOsluskivac() {
        // addActionListener()
        
        // dodajemo osluskivac na dugme sa forme i prosledjujemo ActionListener koji mi kreiramo u nastavku
        prijavaForma.dodajOsluskivacPrijave(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                
                String korisnickoIme = prijavaForma.getTxtKorisnickoIme().getText().trim();
                String lozinka = String.valueOf(prijavaForma.getTxtLozinka().getPassword());
                Radnik r = new Radnik(korisnickoIme, lozinka);
                
                Komunikacija.getInstanca().konekcija();
                Radnik ulogovani = Komunikacija.getInstanca().prijava(r);
                
                if(ulogovani == null){
                    JOptionPane.showMessageDialog(prijavaForma, "Neuspesna prijava na sistem", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(prijavaForma, "Prijava na sistem je uspesna!", "uspeh", JOptionPane.INFORMATION_MESSAGE);
                    prijavaForma.dispose();
                }
                
            }
        });
        
    }

    public void otvoriPrijavaFormu() {
        prijavaForma.setVisible(true);
    }
    
}
