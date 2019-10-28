package es.abel.dam.filters;

import es.abel.dam.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filtrado {

    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaFiltrada;

    public Filtrado(ObservableList<Partido> lista){
        //pasamos la lista para abstraerla de la logica. Tambien se puede pasar la lista directamente de logica
        listaPartidos = lista;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Partido> filtrar(String divisionFiltrar, String equipoFiltrar){
        if(divisionFiltrar.equalsIgnoreCase("Todas") && (equipoFiltrar == null || "".equals(equipoFiltrar))){
            return listaPartidos;
        }
        else if(divisionFiltrar.equalsIgnoreCase("Todas")){
            //filtrado equipo
            listaFiltrada.clear();
            listaFiltrada = filtrarEquipo(equipoFiltrar, listaPartidos);
        }
        else if(equipoFiltrar == null || "".equals(equipoFiltrar)){
            listaFiltrada.clear();
            listaFiltrada = filtrarDivision(divisionFiltrar);
        }
        else{
            //Filtrado division y equipo
            listaFiltrada.clear();
            listaFiltrada = filtrarDivision(divisionFiltrar);
            listaFiltrada = filtrarEquipo(equipoFiltrar, listaFiltrada);
        }
        return listaFiltrada;
    }

    private ObservableList<Partido> filtrarDivision(String divisionFiltrar){
        ObservableList<Partido> aux = FXCollections.observableArrayList();
        for (Partido partido : listaPartidos) {
            if(partido.getDivision().toString().equalsIgnoreCase(divisionFiltrar)){
                aux.add(partido);
            }
        }
        return aux;
    }

    private ObservableList<Partido> filtrarEquipo(String equipoFiltrar, ObservableList<Partido> lista){
        ObservableList<Partido> aux = FXCollections.observableArrayList();
        for (Partido partido : lista) {
            if(partido.getLocal().contains(equipoFiltrar) || partido.getVisitante().contains(equipoFiltrar)){
                aux.add(partido);
            }
        }
        return aux;
    }



}
