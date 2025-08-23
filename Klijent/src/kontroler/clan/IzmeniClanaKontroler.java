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
public class IzmeniClanaKontroler {

    private final ClanForma clanForma;

    public IzmeniClanaKontroler(ClanForma clanForma) {
        this.clanForma = clanForma;
        dodajOsluskivac();
    }

    public void otvoriClanFormu() {

        pripremiFormuClan();
        clanForma.setVisible(true);

    }

    private void pripremiFormuClan() {

        clanForma.setTitle("Izmena člana");
        popuniComboGrad();
        clanForma.getLblID().setVisible(true);
        clanForma.getTxtID().setVisible(true);
        clanForma.getTxtID().setEditable(false);
        clanForma.getTxtID().setEnabled(false);
        clanForma.getBtnSacuvajIzmene().setVisible(true);
        clanForma.getBtnSacuvajNovogClana().setVisible(false);
        clanForma.getBtnObrisi().setVisible(false);

        Clan c = (Clan) GlavniKontroler.getInstanca().vratiParametre("clan");
        clanForma.getTxtID().setText(String.valueOf(c.getClanID()));
        clanForma.getTxtIme().setText(c.getIme());
        clanForma.getTxtPrezime().setText(c.getPrezime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        clanForma.getTxtDatumRodjenja().setText(sdf.format(c.getDatumRodjenja()));
        clanForma.getTxtDatumUclanjenja().setText(sdf.format(c.getDatumUclanjenja()));
        clanForma.getTxtDatumUclanjenja().setEditable(false);
        clanForma.getTxtDatumUclanjenja().setEnabled(false);
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

        clanForma.dodajOsluskivacSacuvajIzmene(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {

                Long id = Long.valueOf(clanForma.getTxtID().getText());
                String ime = clanForma.getTxtIme().getText().trim();
                String prezime = clanForma.getTxtPrezime().getText().trim();
                String strDatRodj = clanForma.getTxtDatumRodjenja().getText().trim();
                String strDatUcl = clanForma.getTxtDatumUclanjenja().getText().trim();
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
                java.sql.Date sqlDatUcl;

                try {
                    java.util.Date utilDatRodj = new SimpleDateFormat("dd.MM.yyyy").parse(strDatRodj);
                    sqlDatRodj = new java.sql.Date(utilDatRodj.getTime());

                    java.util.Date utilDatUcl = new SimpleDateFormat("dd.MM.yyyy").parse(strDatUcl);
                    sqlDatUcl = new java.sql.Date(utilDatUcl.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(
                            clanForma, "Datum mora biti u formatu dd.MM.yyyy",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Clan c = new Clan(id, ime, prezime, sqlDatRodj, sqlDatUcl, grad);

                try {
                    Komunikacija.getInstanca().izmeniClana(c);
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
