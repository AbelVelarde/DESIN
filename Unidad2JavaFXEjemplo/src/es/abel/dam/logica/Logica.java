package es.abel.dam.logica;

import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

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
    public void addPartido(Partido partido){
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
     * @param partido Partido nuevo.
     */
    public void setPartido(Partido partido){
        int id = listaPartidos.indexOf(partido);
        listaPartidos.set(id, partido);
    }

    public void guardarLista(File fichero){
        try {
            ArrayList<Partido> listaFichero = new ArrayList<>(listaPartidos);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

            oos.writeObject(listaFichero);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cargarLista(File fichero){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
            listaPartidos = (ObservableList<Partido>) ois.readObject();

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
