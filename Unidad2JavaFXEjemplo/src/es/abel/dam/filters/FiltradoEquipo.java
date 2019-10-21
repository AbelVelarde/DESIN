package es.abel.dam.filters;

import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FiltradoEquipo {

    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaFiltrada;

    public FiltradoEquipo(ObservableList<Partido> lista){
        //pasamos la lista para abstraerla de la logica. Tambien se puede pasar la lista directamente de logica
        listaPartidos = lista;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Partido> filtrar(String equipoFiltrar){
        //La segunda linea al final para evitar un NullPointerException
        if(equipoFiltrar == null || "".equals(equipoFiltrar)){
            return listaPartidos;
        }
        else{
            //Filtrado
            listaFiltrada.clear();
            for (Partido partido : listaPartidos) {
                if(partido.getLocal().contains(equipoFiltrar) || partido.getVisitante().contains(equipoFiltrar)){
                    listaFiltrada.add(partido);
                }
            }
            return listaFiltrada;
        }
    }

}
