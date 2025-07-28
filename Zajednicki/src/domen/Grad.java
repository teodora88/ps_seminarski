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
public class Grad implements Serializable{
    
    private Long postanskiBroj;
    private String naziv;

    public Grad() {
    }

    public Grad(Long postanskiBroj, String naziv) {
        this.postanskiBroj = postanskiBroj;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Long postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Grad other = (Grad) obj;
        return Objects.equals(this.postanskiBroj, other.postanskiBroj);
    }
    
    
    
}
