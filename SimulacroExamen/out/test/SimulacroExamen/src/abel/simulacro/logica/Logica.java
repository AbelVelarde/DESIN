package abel.simulacro.logica;

import abel.simulacro.models.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<String> listaAulas;
    private ObservableList<Reserva> listaReservas;

    private int numAulas = 100;

    private Logica(){
        listaAulas = FXCollections.observableArrayList();
        listaReservas = FXCollections.observableArrayList();

        for(int i = 0; i < numAulas; i++){
            listaAulas.add("Aula " + i);
        }

    }

    public static Logica getInstance(){
        if(INSTANCE==null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public ObservableList<String> getListaAulas(){
        return listaAulas;
    }

    public ObservableList<Reserva> getListaReservas(){
        return listaReservas;
    }


    public void addReserva(Reserva reserva) {
        listaReservas.add(reserva);
        listaAulas.remove(reserva.getAula());
    }


    public void removeReserva(Reserva reserva) {
        listaReservas.remove(reserva);
        listaAulas.add(reserva.getAula());

        listaAulas.sort(null);
    }
}
