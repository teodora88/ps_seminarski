/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.potvrda;

import domen.Clan;
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
        potForma.getTxtDatumVracanja().setEditable(false);
        potForma.getTxtDatumVracanja().setEnabled(false);

        potForma.getCmbIgra().setVisible(false);
        potForma.getLblIgra().setVisible(false);

        potForma.getTxtNapomena().setVisible(false);
        potForma.getLblNapomena().setVisible(false);

        potForma.getBtnDodajStavku().setVisible(false);
        potForma.getBtnObrisiStavku().setVisible(false);

        potForma.getBtnIzmeniPotvrdu().setVisible(true);
        potForma.getBtnSacuvajPotvrdu().setVisible(false);

        pripremiTabeluStavki(pot);

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
