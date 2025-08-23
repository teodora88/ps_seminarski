/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler.prijava;

import domen.Radnik;
import forme.PrijavaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.glavni.GlavniKontroler;

/**
 *
 * @author T440s
 */
public class PrijavaKontroler {

    private final PrijavaForma prijavaForma;

    public PrijavaKontroler(PrijavaForma prijavaForma) {
        this.prijavaForma = prijavaForma;
        dodajOsluskivac();
    }

    public void otvoriPrijavaFormu() {
        prijavaForma.setVisible(true);
    }
    
    private void dodajOsluskivac() {

        prijavaForma.dodajOsluskivacPrijave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {

                String korisnickoIme = prijavaForma.getTxtKorisnickoIme().getText().trim();
                String lozinka = String.valueOf(prijavaForma.getTxtLozinka().getPassword());

                if (korisnickoIme.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            prijavaForma,
                            "Prijava nije uspela. Korisničko ime je obavezno.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                if (lozinka.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            prijavaForma,
                            "Prijava nije uspela. Lozinka je obavezna.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                Radnik r = new Radnik(korisnickoIme, lozinka);

                try {
                    Komunikacija.getInstanca().konekcija();
                    Radnik ulogovani = Komunikacija.getInstanca().prijava(r);

                    if (ulogovani == null) {
                        JOptionPane.showMessageDialog(
                                prijavaForma,
                                "Prijava nije uspela. Sistem nije pronašao radnika na osnovu unetih podataka.",
                                "Greška",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        GlavniKontroler.getInstanca().setUlogovani(ulogovani);
                        JOptionPane.showMessageDialog(
                                prijavaForma,
                                "Uspešno ste se prijavili na sistem!",
                                "Uspeh",
                                JOptionPane.INFORMATION_MESSAGE);
                        GlavniKontroler.getInstanca().otvoriGlavnuFormu();
                        prijavaForma.dispose();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            prijavaForma,
                            "Došlo je do greške u komunikaciji sa serverom.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
        }
        );

    }
}
