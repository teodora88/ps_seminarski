/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author T440s
 */
public interface Repozitorijum<T> {
    
    List<T> vratiSve(T param, String uslov) throws Exception;
    void dodaj(T param) throws Exception;
    void izmeni(T param) throws Exception;
    void obrisi(T param) throws Exception;
    int dodajIVratiPK(T param) throws Exception;
}
