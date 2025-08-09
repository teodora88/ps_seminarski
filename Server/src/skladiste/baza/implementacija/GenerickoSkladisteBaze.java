/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skladiste.baza.implementacija;

import domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import skladiste.baza.SkladisteBazePodataka;

/**
 *
 * @author T440s
 */
public class GenerickoSkladisteBaze implements SkladisteBazePodataka <ApstraktniDomenskiObjekat>{

    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve() {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        
        return lista;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        String upit = "SELECT FROM " + param.vratiNazivTabele();
        
        return lista;
    }

    @Override
    public void dodaj(ApstraktniDomenskiObjekat param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void izmeni(ApstraktniDomenskiObjekat param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // na vezbama se zove DbRepositoryGeneric
    
    
    
}
