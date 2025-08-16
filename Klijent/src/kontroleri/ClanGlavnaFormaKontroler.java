/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Clan;
import forme.ClanGlavnaForma;
import forme.modeli.ClanMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroleri.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class ClanGlavnaFormaKontroler {

    private final ClanGlavnaForma clanGlavnaForma;

    public ClanGlavnaFormaKontroler(ClanGlavnaForma clanGlavnaForma) {
        this.clanGlavnaForma = clanGlavnaForma;
        dodajOsluskivace();
    }

    public void otvoriClanGlavnuFormu() {
        pripremiFormu(); // metoda za ucitavanje liste clanova 
        clanGlavnaForma.setVisible(true);
    }

    private void pripremiFormu() {

        List<Clan> listaClanova = Komunikacija.getInstanca().ucitajListuClanova();
        ClanMT clanMT = new ClanMT(listaClanova);
        clanGlavnaForma.getTblClanovi().setModel(clanMT);

    }

    private void dodajOsluskivace() {

        clanGlavnaForma.dodajOsluskivacObrisi(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = clanGlavnaForma.getTblClanovi().getSelectedRow();
                // ako je red -1, nismo selektovali clana za brisanje
                if (red == -1) {
                    JOptionPane.showMessageDialog(clanGlavnaForma, "Sistem ne moze da obrise clana.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ClanMT clanMT = (ClanMT) clanGlavnaForma.getTblClanovi().getModel();
                    Clan c = clanMT.getListaClanova().get(red); // izvlacimo clana koji je selektovan 
                    
                    try {
                        Komunikacija.getInstanca().obrisiClana(c);
                        JOptionPane.showMessageDialog(clanGlavnaForma, "Sistem je obrisao clana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu(); // moze i da se napravi metoda osvezi tabelu u modelu tabele
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(clanGlavnaForma, "Sistem ne moze da obrise clana.", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                     
                }

            }
        });
        
        clanGlavnaForma.dodajOsluskivacDodajNovog(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlavniKontroler.getInstanca().otvoriClanFormu(clanGlavnaForma);
            }
        });

    }

}
