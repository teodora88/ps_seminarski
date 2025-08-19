/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.DrustvenaIgra;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author T440s
 */
public class IgraMT extends AbstractTableModel {

    List<DrustvenaIgra> listaIgara;
    String[] kolone = {"igraID", "naziv", "tip", "opis"};

    public IgraMT(List<DrustvenaIgra> listaIgara) {
        this.listaIgara = listaIgara;
    }

    @Override
    public int getRowCount() {
        return listaIgara.size();
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

        DrustvenaIgra igra = listaIgara.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return igra.getIgraID();
            case 1:
                return igra.getNaziv();
            case 2:
                return igra.getTip();
            case 3:
                return igra.getOpis();
            default:
                return "N/A";
        }

    }
    
    public void pretrazi(String naziv){
        
        List<DrustvenaIgra> listaPretrage = listaIgara.stream()
                .filter(i -> (naziv == null || naziv.isEmpty() ||
                          i.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
            .collect(Collectors.toList());
        
        listaIgara = listaPretrage;
        fireTableDataChanged();
        
    }

}
