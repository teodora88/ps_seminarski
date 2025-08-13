/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DrustvenaIgraGlavnaForma;

/**
 *
 * @author T440s
 */
public class DrustvenaIgraGlavnaFormaKontroler {
    
    private final DrustvenaIgraGlavnaForma diGlavnaForma;

    public DrustvenaIgraGlavnaFormaKontroler(DrustvenaIgraGlavnaForma diGlavnaForma) {
        this.diGlavnaForma = diGlavnaForma;
    }

    public void otvoriDrustvenaIgraGlavnaForma() {
        diGlavnaForma.setVisible(true);
    }
    
}
