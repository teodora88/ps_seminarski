/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.ClanGlavnaForma;

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
        clanGlavnaForma.setVisible(true);
    }
    
    
    
}
