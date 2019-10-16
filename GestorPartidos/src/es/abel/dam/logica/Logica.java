package es.abel.dam.logica;

import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Partido> listaPartidos;

    private Logica(){

    }

    /**
     * Crea la instancia de logica, o la devuelve en caso de que ya este creada
     * @return instancia de logica
     */
    public static Logica getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    /**
     * Devuelve la lista de partidos
     * @return lista de partidos
     */
    public ObservableList<Partido> getListaPartidos(){
        return listaPartidos;
    }

    /**
     * Crea la lista de partidos en formato ObservableList
     * @param listaInput lista leida de fichero
     */
    public void setListaPartidos(ArrayList listaInput){
        if(listaInput == null){
            listaPartidos = FXCollections.observableArrayList();
        }
        else{
            listaPartidos = FXCollections.observableArrayList(listaInput);
        }
    }

    /**
     * Añade un partido a la lista.
     * @param partido partido a añadir.
     */
    public void añadirPartido(Partido partido){
        listaPartidos.add(partido);
    }

    /**
     * Borra un partido de la lista.
     * @param partido partido a borrar.
     */
    public void borrarPartido(Partido partido){
        listaPartidos.remove(partido);
    }

    /**
     * Sutituye en la lista un partido.
     * @param partido Partido vamos a editar.
     * @param partidoEditado Nuevo Partido.
     */
    public void editarPartido(Partido partidoEditado, Partido partido){
        int id = listaPartidos.indexOf(partido);
        listaPartidos.set(id, partidoEditado
        );
    }
}
