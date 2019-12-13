package es.abel.dam.filters;

import es.abel.dam.models.Via;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Filter {

    private ObservableList<Via> listaVias;
    private ObservableList<Via> listaFiltrada;

    public Filter(ObservableList<Via> lista){
        listaVias = lista;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Via> filtrar(ArrayList<String> dificultades){
            listaFiltrada.clear();
            ObservableList<Via> aux = FXCollections.observableArrayList();
            for(Via via : listaVias){
                for (String dif : dificultades) {
                    if(via.getDificultad().equalsIgnoreCase(dif)){
                        aux.add(via);
                    }
                }
            }
            listaFiltrada = aux;
            return listaFiltrada;
    }
}
