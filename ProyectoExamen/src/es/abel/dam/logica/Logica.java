package es.abel.dam.logica;

import es.abel.dam.models.Via;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Via> listaVias;
    private ObservableList<String> listaDificultades;
    private ObservableList<String> listaDificultadesDos;

    public static Logica getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica(){
        listaVias = FXCollections.observableArrayList();

        listaDificultades = FXCollections.observableArrayList();
        listaDificultadesDos = FXCollections.observableArrayList();

        listaDificultades.add("6a");
        listaDificultades.add("6b");
        listaDificultades.add("6c");
        listaDificultades.add("7a");
        listaDificultades.add("7b");
        listaDificultades.add("7c");

    }

    public ObservableList<Via> getListaVias() {
        return listaVias;
    }

    public ObservableList<String> getListaDificultades(){
        return listaDificultades;
    }

    public void añadirVia(Via via) {
        listaVias.add(via);
    }

    public ObservableList<String> getListaDificultadesDos(String dificultad) {
        listaDificultadesDos.clear();

        int indice = listaDificultades.indexOf(dificultad);

        for (int i = indice; i < listaDificultades.size(); i++) {
            listaDificultadesDos.add(listaDificultades.get(i));
        }
        return listaDificultadesDos;
    }

    public ArrayList<String> getDificultades(String difMenor, String difMayor) {
        ArrayList<String> lista = new ArrayList<>();

        int indiceMenor = listaDificultades.indexOf(difMenor);

        int indiceMayor = listaDificultades.indexOf(difMayor);

        for (int i = indiceMenor; i <= indiceMayor; i++) {
            lista.add(listaDificultades.get(i));
        }

        return lista;
    }
}
