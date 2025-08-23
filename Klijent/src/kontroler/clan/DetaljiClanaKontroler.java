/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.clan;

import domen.Clan;
import domen.Grad;
import forme.ClanForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class DetaljiClanaKontroler {

    private final ClanForma clanForma;

    public DetaljiClanaKontroler(ClanForma clanForma) {
        this.clanForma = clanForma;
        dodajOsluskivac();
    }

    public void otvoriClanFormu() {
        pripremiFormuClan();
        clanForma.setVisible(true);
    }

    private void pripremiFormuClan() {

        popuniComboGrad();
        clanForma.getLblID().setVisible(true);
        clanForma.getTxtID().setVisible(true);
        clanForma.getTxtID().setEditable(false);
        clanForma.getTxtID().setEnabled(false);
        clanForma.getTxtIme().setEditable(false);
        clanForma.getTxtIme().setEnabled(false);
        clanForma.getTxtPrezime().setEditable(false);
        clanForma.getTxtPrezime().setEnabled(false);
        clanForma.getTxtDatumRodjenja().setEditable(false);
        clanForma.getTxtDatumRodjenja().setEnabled(false);
        clanForma.getTxtDatumUclanjenja().setEditable(false);
        clanForma.getTxtDatumUclanjenja().setEnabled(false);
        clanForma.getCmbGrad().setEnabled(false);
        clanForma.getCmbGrad().setEditable(false);
        clanForma.getBtnSacuvajIzmene().setVisible(false);
        clanForma.getBtnSacuvajNovogClana().setVisible(false);
        clanForma.getBtnObrisi().setVisible(true);

        Clan c = (Clan) GlavniKontroler.getInstanca().vratiParametre("clan");
        clanForma.getTxtID().setText(String.valueOf(c.getClanID()));
        clanForma.getTxtIme().setText(c.getIme());
        clanForma.getTxtPrezime().setText(c.getPrezime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        clanForma.getTxtDatumRodjenja().setText(sdf.format(c.getDatumRodjenja()));
        clanForma.getTxtDatumUclanjenja().setText(sdf.format(c.getDatumUclanjenja()));
        clanForma.getCmbGrad().setSelectedItem(c.getGrad());

    }

    private void popuniComboGrad() {

        List<Grad> listaGradova = Komunikacija.getInstanca().vratiListuGradova();
        clanForma.getCmbGrad().removeAllItems();

        for (Grad g : listaGradova) {
            clanForma.getCmbGrad().addItem(g);
        }
    }

    private void dodajOsluskivac() {

        clanForma.dodajOsluskivacObrisi(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                
                Clan c = (Clan) GlavniKontroler.getInstanca().vratiParametre("clan");

                try {
                    
                    Komunikacija.getInstanca().obrisiClana(c);
                    JOptionPane.showMessageDialog(
                            clanForma, 
                            "Sistem je obrisao člana.", 
                            "Uspeh", 
                            JOptionPane.INFORMATION_MESSAGE);
                            
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            clanForma, 
                            "Sistem ne može da obriše člana.", 
                            "Greška", 
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

}
