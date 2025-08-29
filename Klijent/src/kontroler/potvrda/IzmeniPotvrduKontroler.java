/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.potvrda;

import domen.Clan;
import domen.DrustvenaIgra;
import domen.PotvrdaOIznajmljivanju;
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
import kontroler.glavni.GlavniKontroler;

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

        potForma.setTitle("Izmeni potvrdu o iznajmljivanju");
        popuniComboClan();

        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) GlavniKontroler.getInstanca().vratiParametre("potvrda");
        potForma.getTxtID().setText(String.valueOf(pot.getPotvrdaID()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        potForma.getTxtDatumIznajmljivanja().setText(sdf.format(pot.getDatumIznajmljivanja()));
        potForma.getCmbClan().setSelectedItem(pot.getClan());
        potForma.getLblRadnik().setText("Radnik koji je kreirao potvrdu: " + pot.getRadnik());

        potForma.getTxtID().setEditable(false);
        potForma.getTxtID().setEnabled(false);

        potForma.getTxtDatumIznajmljivanja().setEnabled(false);
        potForma.getTxtDatumIznajmljivanja().setEditable(false);

        potForma.getCmbClan().setEditable(false);
        potForma.getCmbClan().setEnabled(false);

        java.util.Date danas = new java.util.Date();
        potForma.getTxtDatumVracanja().setText(new SimpleDateFormat("dd.MM.yyyy").format(danas));
        potForma.getTxtDatumVracanja().setEditable(true);
        potForma.getTxtDatumVracanja().setEnabled(true);

        potForma.getCmbIgra().setVisible(true);
        potForma.getLblIgra().setVisible(true);

        potForma.getTxtNapomena().setVisible(true);
        potForma.getLblNapomena().setVisible(true);

        potForma.getBtnDodajStavku().setVisible(true);
        potForma.getBtnObrisiStavku().setVisible(true);

        potForma.getBtnIzmeniPotvrdu().setVisible(true);
        potForma.getBtnSacuvajPotvrdu().setVisible(false);

        List<StavkaPotvrdeOIznajmljivanju> listaStavki = pot.getListaStavki();
        StavkaMT stavkaMT = new StavkaMT(listaStavki);
        potForma.getTblStavke().setModel(stavkaMT);

        
        popuniComboIgra();

    }

    private void popuniComboIgra() {

        List<DrustvenaIgra> listaIgara = Komunikacija.getInstanca().ucitajListuIgara();
        potForma.getCmbIgra().removeAllItems();

        for (DrustvenaIgra di : listaIgara) {
            potForma.getCmbIgra().addItem(di);
        }

    }

    private void popuniComboClan() {

        List<Clan> listaClanova = Komunikacija.getInstanca().ucitajListuClanova();
        potForma.getCmbClan().removeAllItems();

        for (Clan c : listaClanova) {
            potForma.getCmbClan().addItem(c);
        }

    }

     private void dodajOsluskivac() {

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
                stavka.setPotvrdaID(Long.valueOf(potForma.getTxtID().getText()));

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

                PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) GlavniKontroler.getInstanca().vratiParametre("potvrda");
                String strDatVrac = potForma.getTxtDatumVracanja().getText().trim();

                if (strDatVrac.isEmpty()) {
                    pot.setDatumVracanja(null);
                } else {
                    java.util.Date utilDatVrac = new SimpleDateFormat("dd.MM.yyyy").parse(strDatVrac);
                    java.sql.Date sqlDatVrac = new java.sql.Date(utilDatVrac.getTime());
                    pot.setDatumVracanja(sqlDatVrac);
                }

                StavkaMT stavkaMT = (StavkaMT) potForma.getTblStavke().getModel();
                List<StavkaPotvrdeOIznajmljivanju> listaStavki = stavkaMT.getListaStavki();

                if (listaStavki.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            potForma,
                            "Sistem ne može da zapamti potvrdu o iznajmljivanju. Potvrda mora da sadrži bar jednu stavku.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pot.setListaStavki(listaStavki);

                try {
                    Komunikacija.getInstanca().izmeniPotvrdu(pot);
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
                            "Sistem ne moze da zapamti potvrdu o iznajmljivanju.",
                            "Greska",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

}
