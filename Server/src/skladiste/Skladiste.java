/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package skladiste;

import java.util.List;

/**
 *
 * @author T440s
 */
public interface Skladiste<T> {
    // na vezbama je ovo Repository 
    
    // ispisujemo CRUD operacije
    
    List<T> vratiSve(); // vracaju se svi objekti iz liste
    List<T> vratiSve(T param) throws Exception; // kada radimo pretragu - vracamo objekte sa uslovom
    void dodaj(T param) throws Exception;
    void izmeni(T param) throws Exception;
    void obrisi(T param) throws Exception;
}
