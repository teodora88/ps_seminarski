/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Clan;
import domen.DrustvenaIgra;
import domen.PotvrdaOIznajmljivanju;
import domen.Radnik;
import domen.StavkaPotvrdeOIznajmljivanju;
import forme.PotvrdaForma;
import forme.modeli.StavkaMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroleri.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class IzmeniPotvrduKontroler {

    private final PotvrdaForma potForma;

    public IzmeniPotvrduKontroler(PotvrdaForma potForma) {
        this.potForma = potForma;
        dodajOsluskivac();
    }

    public void otvoriPotvrdaFormu() {
        pripremiFormu();
        potForma.setVisible(true);
    }

    private void pripremiFormu() {

        ulogovaniRadnik();

        popuniComboClan();

        potForma.getTxtID().setEditable(false);
        potForma.getTxtID().setEnabled(false);

        potForma.getTxtDatumIznajmljivanja().setEnabled(false);
        potForma.getTxtDatumIznajmljivanja().setEditable(false);

        potForma.getCmbClan().setEditable(false);
        potForma.getCmbClan().setEnabled(false);

        potForma.getTxtDatumVracanja().setEditable(true);
        potForma.getTxtDatumVracanja().setEnabled(true);

        potForma.getCmbIgra().setVisible(false);
        potForma.getLblIgra().setVisible(false);

        potForma.getTxtNapomena().setVisible(false);
        potForma.getLblNapomena().setVisible(false);

        potForma.getBtnDodajStavku().setVisible(false);
        potForma.getBtnObrisiStavku().setVisible(false);

        potForma.getBtnIzmeniPotvrdu().setVisible(true);
        potForma.getBtnSacuvajPotvrdu().setVisible(false);

        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) GlavniKontroler.getInstanca().vratiParametre("potvrda");
        potForma.getTxtID().setText(String.valueOf(pot.getPotvrdaID()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        potForma.getTxtDatumIznajmljivanja().setText(sdf.format(pot.getDatumIznajmljivanja()));
        potForma.getCmbClan().setSelectedItem(pot.getClan());

        pripremiTabeluStavki(pot);

    }

    private void ulogovaniRadnik() {
        Radnik ulogovani = GlavniKontroler.getInstanca().getUlogovani();
        potForma.getLblRadnik().setText("Radnik: " + ulogovani);
    }

    private void popuniComboClan() {

        List<Clan> listaClanova = Komunikacija.getInstanca().ucitajListuClanova();
        potForma.getCmbClan().removeAllItems();

        for (Clan c : listaClanova) {
            potForma.getCmbClan().addItem(c);
        }

    }

    private void pripremiTabeluStavki(PotvrdaOIznajmljivanju pot) {

        List<StavkaPotvrdeOIznajmljivanju> listaStavki = Komunikacija.getInstanca().usitajListuStavki(pot.getPotvrdaID());;
        StavkaMT stavkaMT = new StavkaMT(listaStavki);
        potForma.getTblStavke().setModel(stavkaMT);

    }

    private void dodajOsluskivac() {

        potForma.dodajOsluskivacIzmeniPotvrdu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    izmeni(e);
                } catch (ParseException ex) {
                    Logger.getLogger(IzmeniPotvrduKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void izmeni(ActionEvent e) throws ParseException {

                String strDatVrac = potForma.getTxtDatumVracanja().getText().trim();
                java.util.Date utilDatVrac = new SimpleDateFormat("dd.MM.yyyy").parse(strDatVrac);
                java.sql.Date sqlDatVrac = new java.sql.Date(utilDatVrac.getTime());

                PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) GlavniKontroler.getInstanca().vratiParametre("potvrda");
                pot.setDatumVracanja(sqlDatVrac);

                try {
                    Komunikacija.getInstanca().izmeniPotvrdu(pot);
                    JOptionPane.showMessageDialog(potForma, "Sistem je zapamtio potvrdu o iznajmljivanju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstanca().osveziTabeluPotvrda();
                    potForma.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(potForma, "Sistem ne moze da zapamti potvrdu o iznajmljivanju.", "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

}
