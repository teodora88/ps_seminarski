/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author T440s
 */
public class StavkaPotvrdeOIznajmljivanju implements Serializable{
    
    private Long redniBroj;
    private String napomena;
    private DrustvenaIgra drustvenaIgra;

    public StavkaPotvrdeOIznajmljivanju() {
    }

    public StavkaPotvrdeOIznajmljivanju(Long redniBroj, String napomena, DrustvenaIgra drustvenaIgra) {
        this.redniBroj = redniBroj;
        this.napomena = napomena;
        this.drustvenaIgra = drustvenaIgra;
    }

    public DrustvenaIgra getDrustvenaIgra() {
        return drustvenaIgra;
    }

    public void setDrustvenaIgra(DrustvenaIgra drustvenaIgra) {
        this.drustvenaIgra = drustvenaIgra;
    }

    public Long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "StavkaPotvrdeOIznajmljivanju{" + "redniBroj=" + redniBroj + ", drustvenaIgra=" + drustvenaIgra + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaPotvrdeOIznajmljivanju other = (StavkaPotvrdeOIznajmljivanju) obj;
        if (!Objects.equals(this.redniBroj, other.redniBroj)) {
            return false;
        }
        return Objects.equals(this.drustvenaIgra, other.drustvenaIgra);
    }
    
    
    
}
