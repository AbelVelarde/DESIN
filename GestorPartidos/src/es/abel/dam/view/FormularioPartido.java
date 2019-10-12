package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import es.abel.dam.utils.DateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

public class FormularioPartido extends Stage {

    private TextField textFieldLocal;
    private TextField textFieldVisitante;
    private ObservableList<Division> listaDivisiones;
    private ComboBox<Division> comboDivision;
    private TextField tfResultadoLocal;
    private TextField tfResultadoVisitante;
    private DatePicker fechaPartido;
    private Button btnAceptar;

    /**
     * Constructor del formulario para añadir un nuevo partido.
     */
    public FormularioPartido() {
        inicializarLayout();
        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addPartido(-1);
            }
        });
    }

    /**
     * Constructor del formulario para editar un partido.
     * @param partidoEditar Partido a editar.
     * @param id Posicion del partido en la lista.
     */
    public FormularioPartido(Partido partidoEditar, int id) {
        inicializarLayout();
        textFieldLocal.setText(partidoEditar.getLocal());
        textFieldVisitante.setText(partidoEditar.getVisitante());
        comboDivision.setValue(partidoEditar.getDivision());
        tfResultadoLocal.setText("" + partidoEditar.getResultado().getResultadoLocal());
        tfResultadoVisitante.setText("" + partidoEditar.getResultado().getResultadoVisitante());
        fechaPartido.setValue(DateUtils.convertToLocalDate(partidoEditar.getFecha()));

        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addPartido(id);
            }
        });
    }

    /**
     * Crea el layout del formulario tanto como editar como para añadir partidos.
     */
    private void inicializarLayout() {
        //Contenedor base
        GridPane gridFormulario = new GridPane();
        gridFormulario.setHgap(10d);
        gridFormulario.setVgap(10d);

        //Equipo local
        textFieldLocal = new TextField();
        textFieldLocal.setPromptText("Introduzca nombre del equipo local");
        gridFormulario.add(new Label("Nombre local:"), 0, 0, 1, 1);
        gridFormulario.add(textFieldLocal, 1, 0, 1, 1);

        //Equipo visiante
        textFieldVisitante = new TextField();
        textFieldVisitante.setPromptText("Introduzca nombre del equipo visitante");
        gridFormulario.add(new Label("Nombre visitante:"), 0, 1, 1, 1);
        gridFormulario.add(textFieldVisitante, 1, 1, 1, 1);

        //Division del partido
        listaDivisiones = FXCollections.observableArrayList();
        listaDivisiones.add(Division.PRIMERA);
        listaDivisiones.add(Division.SEGUNDA);
        listaDivisiones.add(Division.TERCERA);
        comboDivision = new ComboBox<> (listaDivisiones);
        comboDivision.setPromptText("Elija division");
        gridFormulario.add(new Label("Division:"), 0, 2, 1, 1);
        gridFormulario.add(comboDivision, 1, 2, 1, 1);

        //Resultado del partido
        tfResultadoLocal = new TextField();
        tfResultadoVisitante = new TextField();
        tfResultadoLocal.setPromptText("Equipo local");
        tfResultadoVisitante.setPromptText("Equipo visitante");
        HBox hboxResultado = new HBox(10, tfResultadoLocal, new Label(" - "), tfResultadoVisitante);
        gridFormulario.add(new Label("Resultado del partido:"), 0, 3, 1, 1);
        gridFormulario.add(hboxResultado, 1, 3, 1, 1);

        //Fecha del partido
        fechaPartido = new DatePicker(LocalDate.now());
        gridFormulario.add(new Label("Fecha del partido:"), 0, 4, 1, 1);
        gridFormulario.add(fechaPartido, 1, 4, 1, 1);

        //Boton aceptar. Añade el partido o edita uno existente
        btnAceptar = new Button("Aceptar");
        gridFormulario.add(btnAceptar, 0, 5, 1, 1);

        //Escena
        Scene scene = new Scene(gridFormulario, 650, 400);
        setTitle("Alta Partido");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }

    /**
     * Crea un nuevo partido y sobreescribe uno existente en la lista o lo añade a la misma.
     * @param id posicion en ListaPartidos del partido a editar.
     *           Si el valor es -1 el partido se añade a la lista.
     */
    private void addPartido(int id){
        String localResStr = tfResultadoLocal.getText();
        String visitResStr = tfResultadoVisitante.getText();
        String local = textFieldLocal.getText();
        String visitante = textFieldVisitante.getText();
        Division division = comboDivision.getSelectionModel().getSelectedItem();
        //Comprueba que los resutados introducidos son válidos (solo enteros)
        if(!localResStr.matches("[0-9]+") || !visitResStr.matches("[0-9]+")){
            Alerts.alertaResultado();
        }
        //Comprueba que el nombre de los equipos no este en blanco
        else if(!(local.trim().length() > 0) || !(visitante.trim().length() > 0)){
            Alerts.alertaNombre();
        }
        //Comprueba que se halla elegido una division
        else if(division == null){
            Alerts.alertaDivisionNula();
        }
        else{
            int localRes = Integer.parseInt(localResStr);
            int visitRes = Integer.parseInt(visitResStr);
            Resultado resultado = new Resultado(localRes, visitRes);
            Date fecha = DateUtils.convertToDate(fechaPartido.getValue());

            Partido partido = new Partido(local, visitante, division, resultado, fecha);

            if(id == -1){
                Logica.getINSTANCE().añadirPartido(partido);
            }
            else{
                Logica.getINSTANCE().editarPartido(partido, id);
            }
            close();
        }
    }

}
