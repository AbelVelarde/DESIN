package es.abel.dam.logica;

import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Partido> listaPartidos;

    private Logica(){
        listaPartidos = FXCollections.observableArrayList();
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
    public ObservableList getListaPartidos(){
        return listaPartidos;
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
     * @param id posicion del partido a borrar.
     */
    public void borrarPartido(int id){
        listaPartidos.remove(id);
    }

    /**
     * Sutituye en la lista un partido.
     * @param partido Partido nuevo.
     * @param id Posicion a introducir el patido.
     */
    public void editarPartido(Partido partido, int id){
        listaPartidos.set(id, partido);
    }


    public void filtrarPorDivision(String div){
        ObservableList<Partido> listaApoyo = FXCollections.observableArrayList();
        ObservableList<Partido> listaFiltrada = FXCollections.observableArrayList();
        for (Partido partido: listaPartidos) {
            listaApoyo.add(partido);
            if(partido.getDivision().toString().equalsIgnoreCase(div)){
                listaFiltrada.add(partido);
            }
        }
        listaPartidos = listaFiltrada;
    }

}
