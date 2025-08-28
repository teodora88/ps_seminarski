/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import repozitorijum.Repozitorijum;
import repozitorijum.baza.implementacija.GenerickiRepozitorijumBaze;
import repozitorijum.baza.RepozitorijumBaze;

/**
 *
 * @author T440s
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repozitorijum repo;

    public ApstraktnaGenerickaOperacija() {
        this.repo = new GenerickiRepozitorijumBaze();
    }
    
    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            //uspostaviKonekciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally{
            //raskiniKonekciju();
        }
    }

    protected abstract void preduslovi(Object param)throws Exception;

    private void uspostaviKonekciju() throws Exception {
        ((RepozitorijumBaze) repo).uspostaviKonekciju();
    }

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void potvrdiTransakciju() throws Exception {
        ((RepozitorijumBaze) repo).potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws Exception{
        ((RepozitorijumBaze) repo).ponistiTransakciju();
    }

    private void raskiniKonekciju() throws Exception {
        ((RepozitorijumBaze) repo).raskiniKonekciju();
    }
    
}
