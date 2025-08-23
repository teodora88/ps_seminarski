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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class DodajClanaKontroler {

    private final ClanForma clanForma;

    public DodajClanaKontroler(ClanForma clanForma) {
        this.clanForma = clanForma;
        dodajOsluskivac();
    }

    public void otvoriClanFormu() {
        pripremiFormuClan();
        clanForma.setVisible(true);
    }

    private void pripremiFormuClan() {

        clanForma.setTitle("Kreiranje člana");
        clanForma.getLblID().setVisible(false);
        clanForma.getTxtID().setVisible(false);
        java.util.Date danas = new java.util.Date();
        clanForma.getTxtDatumUclanjenja().setText(new SimpleDateFormat("dd.MM.yyyy").format(danas));
        clanForma.getTxtDatumUclanjenja().setEditable(false);
        clanForma.getTxtDatumUclanjenja().setEnabled(false);
        clanForma.getBtnSacuvajIzmene().setVisible(false);
        clanForma.getBtnSacuvajNovogClana().setVisible(true);
        clanForma.getBtnObrisi().setVisible(false);
        popuniComboGrad();

    }

    private void popuniComboGrad() {

        List<Grad> listaGradova = Komunikacija.getInstanca().vratiListuGradova();
        clanForma.getCmbGrad().removeAllItems();

        for (Grad g : listaGradova) {
            clanForma.getCmbGrad().addItem(g);
        }

    }

    private void dodajOsluskivac() {
        clanForma.dodajOsluskivacSacuvajNovog(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajClana(e);
            }

            private void dodajClana(ActionEvent e) {

                String ime = clanForma.getTxtIme().getText().trim();
                String prezime = clanForma.getTxtPrezime().getText().trim();
                String strDatRodj = clanForma.getTxtDatumRodjenja().getText().trim();
                Grad grad = (Grad) clanForma.getCmbGrad().getSelectedItem();

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            clanForma, "Sistem ne može da zapamti člana. Ime je obavezno.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            clanForma, "Sistem ne može da zapamti člana. Prezime je obavezno.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (strDatRodj.isEmpty()) {
                    JOptionPane.showMessageDialog(clanForma,
                            "Sistem ne može da zapamti člana. Datum rođenja je obavezan.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                java.sql.Date sqlDatRodj;
                
                try {
                    java.util.Date utilDatRodj = new SimpleDateFormat("dd.MM.yyyy").parse(strDatRodj);
                    sqlDatRodj = new java.sql.Date(utilDatRodj.getTime());

                    
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(
                            clanForma, "Datum mora biti u formatu dd.MM.yyyy",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Clan c = new Clan();

                c.setClanID(-1L);
                c.setIme(ime);
                c.setPrezime(prezime);
                c.setDatumRodjenja(sqlDatRodj);
                
                java.util.Date danas = new java.util.Date();
                java.sql.Date sqlDanas = new java.sql.Date(danas.getTime());
                c.setDatumUclanjenja(sqlDanas);
                
                c.setGrad(grad);
                
                try {
                    Komunikacija.getInstanca().dodajClana(c);
                    JOptionPane.showMessageDialog(
                            clanForma,
                            "Sistem je zapamtio člana.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstanca().osveziTabeluClanova();
                    clanForma.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            clanForma,
                            "Sistem ne može da zapamti člana.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
