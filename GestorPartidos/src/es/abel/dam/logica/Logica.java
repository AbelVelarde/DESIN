package es.abel.dam.logica;

import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Logica {

    //TODO: Refactorizar codigo

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

    public void borrarPartido(int id){
        listaPartidos.remove(id);
    }

    public void editarPartido(Partido partido, int id){
        listaPartidos.set(id, partido);
    }

}
