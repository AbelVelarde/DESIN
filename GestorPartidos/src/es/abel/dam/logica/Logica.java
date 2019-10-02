package es.abel.dam.logica;

import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Partido> listaPartidos;

    private Logica(){
        listaPartidos = FXCollections.observableArrayList();
    }

    public static Logica getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public ObservableList getListaPartidos(){
        return listaPartidos;
    }

    public void añadirPartido(Partido partido){
        listaPartidos.add(partido);
    }



}
