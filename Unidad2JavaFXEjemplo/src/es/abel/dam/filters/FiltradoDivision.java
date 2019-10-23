package es.abel.dam.filters;

import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FiltradoDivision {

    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaFiltrada;

    public FiltradoDivision(ObservableList<Partido> lista){
        //pasamos la lista para abstraerla de la logica. Tambien se puede pasar la lista directamente de logica
        listaPartidos = lista;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Partido> filtrar(String divisionFiltrar){
        if(divisionFiltrar.equalsIgnoreCase("Todas")){
            return listaPartidos;
        }
        else{
            //Filtrado
            listaFiltrada.clear();
            for (Partido partido : listaPartidos) {
                if(partido.getDivision().toString().equalsIgnoreCase(divisionFiltrar)){
                    listaFiltrada.add(partido);
                }
            }
            return listaFiltrada;
        }
    }

}
