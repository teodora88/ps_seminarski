/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Clan;
import forme.ClanGlavnaForma;
import forme.modeli.ClanMT;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author T440s
 */
public class ClanGlavnaFormaKontroler {
    
    private final ClanGlavnaForma clanGlavnaForma;

    public ClanGlavnaFormaKontroler(ClanGlavnaForma clanGlavnaForma) {
        this.clanGlavnaForma = clanGlavnaForma;
    }

    public void otvoriClanGlavnuFormu() {
        pripremiFormu(); // metoda za ucitavanje liste clanova 
        clanGlavnaForma.setVisible(true);
    }

    private void pripremiFormu() {
        
        List<Clan> listaClanova = Komunikacija.getInstanca().ucitajListuClanova();
        ClanMT clanMT = new ClanMT(listaClanova);
        clanGlavnaForma.getTblClanovi().setModel(clanMT);
        
    }
    
    
    
}
