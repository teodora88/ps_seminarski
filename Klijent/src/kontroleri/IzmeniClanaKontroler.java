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
public class IzmeniClanaKontroler {

    private final ClanForma clanForma;

    public IzmeniClanaKontroler(ClanForma clanForma) {
        this.clanForma = clanForma;
        dodajOsluskivac();
    }

    public void otvoriClanFormu() {
                
        pripremiFormuClan();
        clanForma.setVisible(true);
        
    }

    private void pripremiFormuClan() {
        
        popuniComboGrad();
        
        clanForma.getLblID().setVisible(true);
        clanForma.getTxtID().setVisible(true);
        clanForma.getTxtID().setEditable(false);
        clanForma.getTxtID().setEnabled(false);
        clanForma.getBtnSacuvajIzmene().setVisible(true);
        clanForma.getBtnSacuvajNovogClana().setVisible(false);

        Clan c = (Clan) GlavniKontroler.getInstanca().vratiParametre("clan");
        clanForma.getTxtID().setText(String.valueOf(c.getClanID()));
        clanForma.getTxtIme().setText(c.getIme());
        clanForma.getTxtPrezime().setText(c.getPrezime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        clanForma.getTxtDatumRodjenja().setText(sdf.format(c.getDatumRodjenja()));
        clanForma.getTxtDatumUclanjenja().setText(sdf.format(c.getDatumUclanjenja()));
        clanForma.getCmbGrad().setSelectedItem(c.getGrad());
        
    }
    
    private void popuniComboGrad() {
        
        List<Grad> listaGradova = Komunikacija.getInstanca().vratiListuGradova();
        clanForma.getCmbGrad().removeAllItems();

        for (Grad g : listaGradova) {
            clanForma.getCmbGrad().addItem(g);
        }
    }

    private void dodajOsluskivac() {

        clanForma.dodajOsluskivacSacuvajIzmene(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    izmeni(e);
                } catch (ParseException ex) {
                    Logger.getLogger(IzmeniClanaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void izmeni(ActionEvent e) throws ParseException {
                Long id = Long.valueOf(clanForma.getTxtID().getText());
                String ime = clanForma.getTxtIme().getText().trim();
                String prezime = clanForma.getTxtPrezime().getText().trim();
                String strDatRodj = clanForma.getTxtDatumRodjenja().getText().trim();
                java.util.Date utilDatRodj = new SimpleDateFormat("dd.MM.yyyy").parse(strDatRodj);
                java.sql.Date sqlDatRodj = new java.sql.Date(utilDatRodj.getTime());
                String strDatUcl = clanForma.getTxtDatumUclanjenja().getText().trim();
                java.util.Date utilDatUcl = new SimpleDateFormat("dd.MM.yyyy").parse(strDatUcl);
                java.sql.Date sqlDatUcl = new java.sql.Date(utilDatUcl.getTime());
                Grad grad = (Grad) clanForma.getCmbGrad().getSelectedItem();
                
                Clan c = new Clan(id, ime, prezime, sqlDatRodj, sqlDatUcl, grad);
                
                try {
                    Komunikacija.getInstanca().izmeniClana(c);
                    JOptionPane.showMessageDialog(clanForma, "Sistem je izmenio podatke o clanu.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstanca().osveziTabeluClanova();
                    clanForma.dispose();
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(clanForma, "Sistem ne moze da izmeni podatke o clanu.", "Greska", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

    }

}
