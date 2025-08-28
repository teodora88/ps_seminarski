/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Clan;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author T440s
 */
public class ClanMT extends AbstractTableModel {

    List<Clan> listaClanova;
    String[] kolone = {"ID", "Ime", "Prezime", "Datum rođenja", "Datum učlanjenja", "Grad"};

    public ClanMT(List<Clan> listaClanova) {
        this.listaClanova = listaClanova;
    }

    @Override
    public int getRowCount() {
        return listaClanova.size();
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

        Clan c = listaClanova.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getClanID();
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getDatumRodjenja() != null ? new SimpleDateFormat("dd.MM.yyyy").format(c.getDatumRodjenja()) : "";
            case 4:
                return c.getDatumUclanjenja() != null ? new SimpleDateFormat("dd.MM.yyyy").format(c.getDatumUclanjenja()) : "";
            case 5:
                return c.getGrad().getNaziv();
            default:
                return "N/A";
        }

    }

    public List<Clan> getListaClanova() {
        return listaClanova;
    }

    public boolean pretrazi(String ime, String prezime) {

        List<Clan> listaPretrage = listaClanova.stream()
                .filter(c -> (ime == null || ime.isEmpty() || c.getIme().equalsIgnoreCase(ime)))
                .filter(c -> (prezime == null || prezime.isEmpty() || c.getPrezime().equalsIgnoreCase(prezime)))
                .collect(Collectors.toList());

        listaClanova = listaPretrage;
        fireTableDataChanged();

        return !listaPretrage.isEmpty();

    }

    public void prikaziNadjeneClanove(List<Clan> nadjeniClanovi) {
        this.listaClanova = nadjeniClanovi;
        fireTableDataChanged();
    }

}
