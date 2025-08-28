/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.igra;

import domen.DrustvenaIgra;
import forme.DrustvenaIgraGlavnaForma;
import forme.modeli.IgraMT;
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
public class DrustvenaIgraGlavnaFormaKontroler {

    private final DrustvenaIgraGlavnaForma diGlavnaForma;

    public DrustvenaIgraGlavnaFormaKontroler(DrustvenaIgraGlavnaForma diGlavnaForma) {
        this.diGlavnaForma = diGlavnaForma;
        dodajOsluskivace();
    }

    public void otvoriDrustvenaIgraGlavnaForma() {
        pripremiFormu();
        diGlavnaForma.setVisible(true);
    }

    private void pripremiFormu() {

        List<DrustvenaIgra> listaIgara = Komunikacija.getInstanca().ucitajListuIgara();
        IgraMT igraMT = new IgraMT(listaIgara);
        diGlavnaForma.getTblDrusteveIgre().setModel(igraMT);

    }

    private void dodajOsluskivace() {

        diGlavnaForma.dodajOsluskivacPretraga(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pretrazi(e);
                } catch (Exception ex) {
                    Logger.getLogger(DrustvenaIgraGlavnaFormaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void pretrazi(ActionEvent e) throws Exception {
                String naziv = diGlavnaForma.getTxtPretraga().getText().trim();

                DrustvenaIgra di = new DrustvenaIgra();
                di.setNaziv(naziv);

                List<DrustvenaIgra> nadjeneIgre = Komunikacija.getInstanca().nadjiDrustveneIgre(di);

                IgraMT igraMT = (IgraMT) diGlavnaForma.getTblDrusteveIgre().getModel();
                igraMT.prikaziNadjeneIgre(nadjeneIgre);

                if (!nadjeneIgre.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            diGlavnaForma,
                            "Sistem je našao društvene igre po zadatoj vrednosti.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            diGlavnaForma,
                            "Sistem ne može da nađe društvene igre po zadatoj vrednosti.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }

        });

        diGlavnaForma.dodajOsluskivacResetuj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

        diGlavnaForma.dodajOsluskivacDetalji(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detalji(e);
            }

            private void detalji(ActionEvent e) {

                int red = diGlavnaForma.getTblDrusteveIgre().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(
                            diGlavnaForma,
                            "Sistem ne može da učita društvenu igru.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    IgraMT igraMT = (IgraMT) diGlavnaForma.getTblDrusteveIgre().getModel();
                    DrustvenaIgra di = igraMT.getListaIgara().get(red);

                    JOptionPane.showMessageDialog(
                            diGlavnaForma,
                            "Sistem je učitao društvenu igru.",
                            "Uspeh",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    GlavniKontroler.getInstanca().dodajParametre("igra", di);
                    GlavniKontroler.getInstanca().otvoriIgraFormuZaDetalje(diGlavnaForma);

                }

            }
        });

    }

}
