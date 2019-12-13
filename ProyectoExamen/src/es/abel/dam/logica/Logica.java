package es.abel.dam.logica;

import es.abel.dam.models.Via;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Via> listaVias;
    private ObservableList<String> listaDificultades;

    public static Logica getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica(){
        listaVias = FXCollections.observableArrayList();

        listaDificultades = FXCollections.observableArrayList();
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
}
