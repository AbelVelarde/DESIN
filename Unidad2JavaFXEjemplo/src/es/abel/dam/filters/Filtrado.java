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
            filtrarEquipo(equipoFiltrar, listaPartidos);
        }
        else if(equipoFiltrar == null || "".equals(equipoFiltrar)){
            listaFiltrada.clear();
            filtrarDivision(divisionFiltrar, listaPartidos);
        }
        else{
            //Filtrado division y equipo
            listaFiltrada.clear();
            listaFiltrada = listaPartidos;
            filtrarDivision(divisionFiltrar,listaFiltrada);
            filtrarEquipo(equipoFiltrar, listaFiltrada);
        }
        return listaFiltrada;
    }

    private void filtrarEquipo(String equipoFiltrar, ObservableList<Partido> lista){
        for (Partido partido : lista) {
            if(partido.getLocal().contains(equipoFiltrar) || partido.getVisitante().contains(equipoFiltrar)){
                listaFiltrada.add(partido);
            }
        }
    }

    private void filtrarDivision(String divisionFiltrar, ObservableList<Partido> lista){
        for (Partido partido : lista) {
            if(partido.getDivision().toString().equalsIgnoreCase(divisionFiltrar)){
                listaFiltrada.add(partido);
            }
        }
    }

}
