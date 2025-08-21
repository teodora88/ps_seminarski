/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Clan;
import domen.PotvrdaOIznajmljivanju;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author T440s
 */
public class PotvrdaMT extends AbstractTableModel {

    List<PotvrdaOIznajmljivanju> listaPotvrda;
    String[] kolone = {"potvrdaID", "datumIznajmljivanja", "datumVracanja", "clan", "radnik"};

    public PotvrdaMT(List<PotvrdaOIznajmljivanju> listaPotvrda) {
        this.listaPotvrda = listaPotvrda;
    }

    public List<PotvrdaOIznajmljivanju> getListaPotvrda() {
        return listaPotvrda;
    }

    @Override
    public int getRowCount() {
        return listaPotvrda.size();
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

        PotvrdaOIznajmljivanju potvrda = listaPotvrda.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return potvrda.getPotvrdaID();
            case 1:
                return potvrda.getDatumIznajmljivanja();
            case 2:
                return potvrda.getDatumVracanja();
            case 3:
                return potvrda.getClan();
            case 4:
                return potvrda.getRadnik();
            default:
                return "N/A";
        }

    }

    public void prertaziPotvrdu(Clan c) {

        String ime = c.getIme();
        String prezime = c.getPrezime();

        List<PotvrdaOIznajmljivanju> listaPretrage = listaPotvrda.stream()
                .filter(p -> (ime == null || ime.isEmpty()
                || p.getClan().getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(p -> (prezime == null || prezime.isEmpty()
                || p.getClan().getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .collect(Collectors.toList());
        
        this.listaPotvrda = listaPretrage;

        fireTableDataChanged();

    }

}
