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
import java.util.ArrayList;
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
public class DodajPotvrduKontroler {

    private final PotvrdaForma potForma;

    public DodajPotvrduKontroler(PotvrdaForma potForma) {
        this.potForma = potForma;
        dodajOsluskivace();
    }

    public void otvoriPotvrdaFormu() {
        pripremiFormu();
        potForma.setVisible(true);
    }

    private void pripremiFormu() {

        ulogovaniRadnik();

        popuniComboClan();
        popuniComboIgra();
        pripremiTabeluStavki();

        potForma.getTxtID().setEditable(false);
        potForma.getTxtID().setEnabled(false);

        potForma.getTxtDatumVracanja().setEditable(false);
        potForma.getTxtDatumVracanja().setEnabled(false);

        potForma.getBtnDodajStavku().setVisible(true);
        potForma.getBtnObrisiStavku().setVisible(true);

        potForma.getBtnIzmeniPotvrdu().setVisible(false);
        potForma.getBtnSacuvajPotvrdu().setVisible(true);

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

    private void popuniComboIgra() {

        List<DrustvenaIgra> listaIgara = Komunikacija.getInstanca().ucitajListuIgara();
        potForma.getCmbIgra().removeAllItems();

        for (DrustvenaIgra di : listaIgara) {
            potForma.getCmbIgra().addItem(di);
        }

    }

    private void pripremiTabeluStavki() {

        List<StavkaPotvrdeOIznajmljivanju> listaStavki = new ArrayList<>();
        StavkaMT stavkaMT = new StavkaMT(listaStavki);
        potForma.getTblStavke().setModel(stavkaMT);

    }

    private void dodajOsluskivace() {

        potForma.dodajOsluskivacDodajStavku(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // moze se dodati validacija
                dodajStavku(e);
            }

            private void dodajStavku(ActionEvent e) {

                DrustvenaIgra di = (DrustvenaIgra) potForma.getCmbIgra().getSelectedItem();
                String napomena = potForma.getTxtNapomena().getText().trim();
                // redni broj je autoincrement 

                StavkaPotvrdeOIznajmljivanju stavka = new StavkaPotvrdeOIznajmljivanju();
                stavka.setDrustvenaIgra(di);
                stavka.setNapomena(napomena);

                StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();
                stavkaMT.dodajStavkuUTabelu(stavka);

            }
        });

        potForma.dodajOsluskivasObrisiStavku(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku(e);
            }

            private void obrisiStavku(ActionEvent e) {

                int red = potForma.getTblStavke().getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(potForma, "Sistem ne moze da obrise stavku.", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();
                    StavkaPotvrdeOIznajmljivanju stavka = stavkaMT.getListaStavki().get(red);
                    stavkaMT.obrisiStavkuIzTabele(stavka);
                    JOptionPane.showMessageDialog(potForma, "Sistem je obrisao stavku.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

        potForma.dodajOsluskivacDodajPotvrdu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodajPotvrdu(e);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajPotvrduKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void dodajPotvrdu(ActionEvent e) throws ParseException {

                PotvrdaOIznajmljivanju pot = new PotvrdaOIznajmljivanju();

                String strDatIznaj = potForma.getTxtDatumIznajmljivanja().getText().trim();
                java.util.Date utilDatIznaj = new SimpleDateFormat("dd.MM.yyyy").parse(strDatIznaj);
                java.sql.Date sqlDatIznaj = new java.sql.Date(utilDatIznaj.getTime());
                pot.setDatumIznajmljivanja(sqlDatIznaj);

                Clan izabraniClan = (Clan) potForma.getCmbClan().getSelectedItem();
                pot.setClan(izabraniClan);

                Radnik ulogovani = GlavniKontroler.getInstanca().getUlogovani();
                pot.setRadnik(ulogovani);

                StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();
                List<StavkaPotvrdeOIznajmljivanju> listaStavki = stavkaMT.getListaStavki();
                pot.setListaStavki(listaStavki);

                try {
                    Komunikacija.getInstanca().dodajPotvrdu(pot);
                    JOptionPane.showMessageDialog(potForma, "Sistem je kreirao novu potvrdu o iznajmljivanju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstanca().osveziTabeluPotvrda();
                    potForma.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(DodajPotvrduKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

}
