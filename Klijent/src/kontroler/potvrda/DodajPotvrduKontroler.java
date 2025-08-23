/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.potvrda;

import domen.Clan;
import domen.DrustvenaIgra;
import domen.PotvrdaOIznajmljivanju;
import domen.Radnik;
import domen.StavkaPotvrdeOIznajmljivanju;
import forme.PotvrdaForma;
import forme.modeli.StavkaMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.glavni.GlavniKontroler;

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

        potForma.setTitle("Dodaj potvrdu o iznajmljivanju");

        ulogovaniRadnik();

        popuniComboClan();
        popuniComboIgra();
        pripremiTabeluStavki();

        potForma.getLblID().setVisible(false);
        potForma.getTxtID().setVisible(false);

        java.util.Date danas = new java.util.Date();
        potForma.getTxtDatumIznajmljivanja().setText(new SimpleDateFormat("dd.MM.yyyy").format(danas));
        potForma.getTxtDatumIznajmljivanja().setEditable(false);
        potForma.getTxtDatumIznajmljivanja().setEnabled(false);

        potForma.getTxtDatumVracanja().setVisible(false);
        potForma.getLblVracanje().setVisible(false);

        potForma.getBtnDodajStavku().setVisible(true);
        potForma.getBtnObrisiStavku().setVisible(true);

        potForma.getBtnIzmeniPotvrdu().setVisible(false);
        potForma.getBtnSacuvajPotvrdu().setVisible(true);

    }

    private void ulogovaniRadnik() {
        Radnik ulogovani = GlavniKontroler.getInstanca().getUlogovani();
        potForma.getLblRadnik().setText("Ulogovani radnik: " + ulogovani);
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
                dodajStavku(e);
            }

            private void dodajStavku(ActionEvent e) {

                DrustvenaIgra di = (DrustvenaIgra) potForma.getCmbIgra().getSelectedItem();
                String napomena = potForma.getTxtNapomena().getText().trim();

                StavkaPotvrdeOIznajmljivanju stavka = new StavkaPotvrdeOIznajmljivanju();
                stavka.setDrustvenaIgra(di);
                stavka.setNapomena(napomena);

                StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();
                stavkaMT.dodajStavkuUTabelu(stavka);

                JOptionPane.showMessageDialog(
                        potForma,
                        "Sistem je zapamito stavku.",
                        "Uspeh",
                        JOptionPane.INFORMATION_MESSAGE);

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
                    JOptionPane.showMessageDialog(
                            potForma,
                            "Sistem ne može da obriše stavku.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();
                    StavkaPotvrdeOIznajmljivanju stavka = stavkaMT.getListaStavki().get(red);
                    stavkaMT.obrisiStavkuIzTabele(stavka);
                    JOptionPane.showMessageDialog(
                            potForma,
                            "Sistem je obrisao stavku.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

        potForma.dodajOsluskivacDodajPotvrdu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dodajPotvrdu(e);

            }

            private void dodajPotvrdu(ActionEvent e) {

                PotvrdaOIznajmljivanju pot = new PotvrdaOIznajmljivanju();

                java.util.Date danas = new java.util.Date();
                java.sql.Date sqlDanas = new java.sql.Date(danas.getTime());
                pot.setDatumIznajmljivanja(sqlDanas);

                Clan izabraniClan = (Clan) potForma.getCmbClan().getSelectedItem();
                pot.setClan(izabraniClan);

                Radnik ulogovani = GlavniKontroler.getInstanca().getUlogovani();
                pot.setRadnik(ulogovani);

                StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();

                List<StavkaPotvrdeOIznajmljivanju> listaStavki = stavkaMT.getListaStavki();
                if (stavkaMT.getListaStavki().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            potForma,
                            "Sistem ne može da zapamti potvrdu o iznajmljivanju. Potvrda mora da sadrži bar jednu stavku.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                pot.setListaStavki(listaStavki);

                try {
                    Komunikacija.getInstanca().dodajPotvrdu(pot);
                    JOptionPane.showMessageDialog(
                            potForma,
                            "Sistem je zapamtio potvrdu o iznajmljivanju.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstanca().osveziTabeluPotvrda();
                    potForma.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            potForma,
                            "Sistem ne može da zapamti potvrdu o iznajmljivanju.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        );

    }

}
