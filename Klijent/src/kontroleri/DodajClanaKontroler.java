/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Clan;
import domen.Grad;
import forme.ClanForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DodajClanaKontroler {
    
    private final ClanForma clanForma;

    public DodajClanaKontroler(ClanForma clanForma) {
        this.clanForma = clanForma;
        dodajOsluskivac();
    }

    public void otvoriClanFormu() {
        List<Grad> listaGradova = Komunikacija.getInstanca().vratiListuGradova();
        popuniComboGrad(listaGradova);
        pripremiFormuClan();
        
        clanForma.setVisible(true);
    }

    private void popuniComboGrad(List<Grad> listaGradova) {
        clanForma.getCmbGrad().removeAllItems();
        
        for(Grad g : listaGradova){
            clanForma.getCmbGrad().addItem(g);
        }
    }
    
    private void pripremiFormuClan() {
        clanForma.getLblID().setVisible(false);
        clanForma.getTxtID().setVisible(false);
    }

    private void dodajOsluskivac() {
        clanForma.dodajOsluskivacSacuvaj(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodaj(e);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(clanForma, "Morate uneti datum u formatu dd.MM.yyyy","Neispravan datum",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(DodajClanaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void dodaj(ActionEvent e) throws ParseException {
                String ime = clanForma.getTxtIme().getText().trim();
                String prezime = clanForma.getTxtPrezime().getText().trim();
                String strDatRodj = clanForma.getTxtDatumRodjenja().getText().trim();
                java.util.Date utilDatRodj = new SimpleDateFormat("dd.MM.yyyy").parse(strDatRodj);
                java.sql.Date sqlDatRodj = new java.sql.Date(utilDatRodj.getTime());
                String strDatUcl = clanForma.getTxtDatumUclanjenja().getText().trim();
                java.util.Date utilDatUcl = new SimpleDateFormat("dd.MM.yyyy").parse(strDatUcl);
                java.sql.Date sqlDatUcl = new java.sql.Date(utilDatUcl.getTime());
                Grad grad = (Grad) clanForma.getCmbGrad().getSelectedItem();
                
                Clan c = new Clan(-1L, ime, prezime, sqlDatRodj, sqlDatUcl, grad);
                
                try {
                    Komunikacija.getInstanca().dodajClana(c);
                    JOptionPane.showMessageDialog(clanForma, "Sistem je kreirao novog clana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstanca().osveziTabeluClanova();
                    clanForma.dispose();
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(clanForma, "Sistem ne moze da kreira novog clana.", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    

    
    
    
    
}
