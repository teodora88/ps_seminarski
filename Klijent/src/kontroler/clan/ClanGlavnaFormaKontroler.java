/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.clan;

import domen.Clan;
import forme.ClanGlavnaForma;
import forme.modeli.ClanMT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ClanGlavnaFormaKontroler {

    private final ClanGlavnaForma clanGlavnaForma;

    public ClanGlavnaFormaKontroler(ClanGlavnaForma clanGlavnaForma) {
        this.clanGlavnaForma = clanGlavnaForma;
        dodajOsluskivace();
    }

    public void otvoriClanGlavnuFormu() {
        pripremiFormu();
        clanGlavnaForma.setVisible(true);
    }

    public void pripremiFormu() {

        List<Clan> listaClanova = Komunikacija.getInstanca().ucitajListuClanova();
        ClanMT clanMT = new ClanMT(listaClanova);
        clanGlavnaForma.getTblClanovi().setModel(clanMT);

    }

    private void dodajOsluskivace() {

        clanGlavnaForma.dodajOsluskivacDodajNovog(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlavniKontroler.getInstanca().otvoriClanFormuDodajNovog(clanGlavnaForma);
            }
        });

        clanGlavnaForma.dodajOsluskivacIzmeni(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = clanGlavnaForma.getTblClanovi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(
                            clanGlavnaForma,
                            "Sistem ne može da učita člana.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    ClanMT clanMT = (ClanMT) clanGlavnaForma.getTblClanovi().getModel();
                    Clan c = clanMT.getListaClanova().get(red);

                    JOptionPane.showMessageDialog(
                            clanGlavnaForma,
                            "Sistem je učitao člana.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    GlavniKontroler.getInstanca().dodajParametre("clan", c);
                    GlavniKontroler.getInstanca().otvoriClanFormuZaIzmenu(clanGlavnaForma);

                }

            }
        });

        clanGlavnaForma.dodajOsluskivacPretrazi(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    pretraga(e);
                } catch (Exception ex) {
                    Logger.getLogger(ClanGlavnaFormaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            private void pretraga(ActionEvent e) throws Exception {

                String ime = clanGlavnaForma.getTxtPretragaIme().getText().trim();
                String prezime = clanGlavnaForma.getTxtPretragaPrezime().getText().trim();

                Clan c = new Clan();
                c.setIme(ime);
                c.setPrezime(prezime);

                List<Clan> nadjeniClanovi = Komunikacija.getInstanca().nadjiClanove(c);

                ClanMT clanMT = (ClanMT) clanGlavnaForma.getTblClanovi().getModel();
                clanMT.prikaziNadjeneClanove(nadjeniClanovi);

                if (!nadjeniClanovi.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            clanGlavnaForma,
                            "Sistem je našao članove po zadatoj vrednosti.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            clanGlavnaForma,
                            "Sistem ne može da nađe članove po zadatoj vrednosti.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }

        }
        );

        clanGlavnaForma.dodajOsluskivacDetalji(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                detalji(e);
            }

            private void detalji(ActionEvent e) {

                int red = clanGlavnaForma.getTblClanovi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(
                            clanGlavnaForma,
                            "Sistem ne može da učita člana.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    ClanMT clanMT = (ClanMT) clanGlavnaForma.getTblClanovi().getModel();
                    Clan c = clanMT.getListaClanova().get(red);

                    JOptionPane.showMessageDialog(
                            clanGlavnaForma,
                            "Sistem je učitao člana.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    GlavniKontroler.getInstanca().dodajParametre("clan", c);
                    GlavniKontroler.getInstanca().otvoriClanFormuZaDetalje(clanGlavnaForma);
                    pripremiFormu();

                }
            }
        }
        );

        clanGlavnaForma.dodajOsluskivacResetuj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

    }

}
