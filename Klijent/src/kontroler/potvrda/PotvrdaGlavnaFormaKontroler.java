/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.potvrda;

import domen.Clan;
import domen.PotvrdaOIznajmljivanju;
import domen.StavkaPotvrdeOIznajmljivanju;
import forme.PotvrdaGlavnaForma;
import forme.modeli.PotvrdaMT;
import forme.modeli.StavkaMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
public class PotvrdaGlavnaFormaKontroler {

    private final PotvrdaGlavnaForma potGlavaForma;

    public PotvrdaGlavnaFormaKontroler(PotvrdaGlavnaForma potGlavaForma) {
        this.potGlavaForma = potGlavaForma;
        dodajOsluskivace();
    }

    public void otvoriPotvrdaGlavnaForma() {
        pripremiFormu();
        potGlavaForma.setVisible(true);
    }

    public void pripremiFormu() {

        List<PotvrdaOIznajmljivanju> listaPotvrda = Komunikacija.getInstanca().ucitajListuPotvrda();
        PotvrdaMT potvrdaMT = new PotvrdaMT(listaPotvrda);
        potGlavaForma.getTblListaPotvrda().setModel(potvrdaMT);

        List<StavkaPotvrdeOIznajmljivanju> listaStavki = new ArrayList<>();
        StavkaMT stavkaMT = new StavkaMT(listaStavki);
        potGlavaForma.getTblStavkeIzabranePotvrde().setModel(stavkaMT);

    }

    private void dodajOsluskivace() {

        potGlavaForma.getTblListaPotvrda().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int red = potGlavaForma.getTblListaPotvrda().getSelectedRow();

                if (red != -1) {
                    PotvrdaMT potMT = (PotvrdaMT) potGlavaForma.getTblListaPotvrda().getModel();
                    PotvrdaOIznajmljivanju pot = potMT.getListaPotvrda().get(red);
                    
                    
                    PotvrdaOIznajmljivanju potSaStavkama = Komunikacija.getInstanca().ucitajPotvrdu(pot.getPotvrdaID());

                    List<StavkaPotvrdeOIznajmljivanju> listaStavki = potSaStavkama.getListaStavki();
                    StavkaMT stMT = new StavkaMT(listaStavki);
                    potGlavaForma.getTblStavkeIzabranePotvrde().setModel(stMT);
                }

            }
        });

        potGlavaForma.dodajOsluskivacDodajNovu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlavniKontroler.getInstanca().otvoriPotvrdaFormuDodaj(potGlavaForma);
            }
        });

        potGlavaForma.dodajOsluskivacPretrazi(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pretraga(e);
                } catch (Exception ex) {
                    Logger.getLogger(PotvrdaGlavnaFormaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void pretraga(ActionEvent e) throws Exception {

                String ime = potGlavaForma.getTxtPretragaIme().getText().trim();
                String prezime = potGlavaForma.getTxtPretragaPrezime().getText().trim();

                Clan c = new Clan();
                c.setIme(ime);
                c.setPrezime(prezime);

                List<PotvrdaOIznajmljivanju> nadjenePotvrde = Komunikacija.getInstanca().nadjiPotvrde(c);

                PotvrdaMT potMT = (PotvrdaMT) potGlavaForma.getTblListaPotvrda().getModel();
                potMT.prikaziNadjenePotvrde(nadjenePotvrde);

                if (!nadjenePotvrde.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            potGlavaForma,
                            "Sistem je našao potvrde po zadatoj vrednosti.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            potGlavaForma,
                            "Sistem ne može da nađe potvrde po zadatoj vrednosti.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }

        });

        potGlavaForma.dodajOsluskivacResetuj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

        potGlavaForma.dodajOsluskivacIzmeniPotvrdu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = potGlavaForma.getTblListaPotvrda().getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(
                            potGlavaForma,
                            "Sistem ne može da učita potvrdu o iznajmljivanju.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    PotvrdaMT potMT = (PotvrdaMT) potGlavaForma.getTblListaPotvrda().getModel();
                    PotvrdaOIznajmljivanju pot = potMT.getListaPotvrda().get(red);
                    
                    PotvrdaOIznajmljivanju potSaStavkama = Komunikacija.getInstanca().ucitajPotvrdu(pot.getPotvrdaID());


                    if (pot.getDatumVracanja() != null) {
                        JOptionPane.showMessageDialog(
                                potGlavaForma,
                                "Potvrda već ima datum vraćanja i nije je moguće menjati.",
                                "Obaveštenje",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        return;
                    }

                    JOptionPane.showMessageDialog(
                            potGlavaForma,
                            "Sistem je učitao potvrdu o iznajmljivanju.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    GlavniKontroler.getInstanca().dodajParametre("potvrda", potSaStavkama);
                    GlavniKontroler.getInstanca().otvoriPotvrdaFormuZaIzmenu(potGlavaForma);

                }

            }
        });

    }

}
