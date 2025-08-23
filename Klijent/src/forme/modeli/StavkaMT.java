/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.StavkaPotvrdeOIznajmljivanju;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author T440s
 */
public class StavkaMT extends AbstractTableModel {

    List<StavkaPotvrdeOIznajmljivanju> listaStavki;
    String[] kolone = {"Redni broj", "Društvena igra", "Napomena"};

    public StavkaMT(List<StavkaPotvrdeOIznajmljivanju> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public List<StavkaPotvrdeOIznajmljivanju> getListaStavki() {
        return listaStavki;
    }

    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        StavkaPotvrdeOIznajmljivanju stavka = listaStavki.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stavka.getRedniBroj();
            case 1:
                return stavka.getDrustvenaIgra();
            case 2:
                return stavka.getNapomena();
            default:
                return "N/A";
        }

    }

    public void dodajStavkuUTabelu(StavkaPotvrdeOIznajmljivanju stavka) {
        Long redniBroj = Long.valueOf(listaStavki.size()+1);
        stavka.setRedniBroj(redniBroj);
        listaStavki.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavkuIzTabele(StavkaPotvrdeOIznajmljivanju stavka) {
        listaStavki.remove(stavka);
        fireTableDataChanged();
    }

}
