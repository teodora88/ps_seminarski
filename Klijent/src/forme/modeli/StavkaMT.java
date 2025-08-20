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
    String[] kolone = {"redniBroj", "napomena", "drustvenaIgra"};

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
                return stavka.getNapomena();
            case 2:
                return stavka.getDrustvenaIgra();
            default:
                return "N/A";
        }

    }

}
