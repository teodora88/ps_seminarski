/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Grad;
import forme.ClanForma;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author T440s
 */
public class ClanFormaKontroler {
    
    private final ClanForma clanForma;

    public ClanFormaKontroler(ClanForma clanForma) {
        this.clanForma = clanForma;
    }

    public void otvoriClanFormu() {
        List<Grad> listaGradova = Komunikacija.getInstanca().vratiListuGradova();
        popuniComboGrad(listaGradova);
        
        clanForma.setVisible(true);
    }

    private void popuniComboGrad(List<Grad> listaGradova) {
        clanForma.getCmbGrad().removeAllItems();
        
        for(Grad g : listaGradova){
            clanForma.getCmbGrad().addItem(g.getNaziv());
        }
    }

    
    
    
    
}
