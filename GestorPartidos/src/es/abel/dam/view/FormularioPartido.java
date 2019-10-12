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

    //TODO: Refactorizar codigo

    private TextField textFieldLocal;
    private TextField textFieldVisitante;
    private ObservableList<Division> listaDivisiones;
    private ComboBox<Division> comboDivision;
    private TextField tfResultadoLocal;
    private TextField tfResultadoVisitante;
    private DatePicker fechaPartido;
    private Button btnAceptar;

    public FormularioPartido() {
        inicializarLayout();
        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addPartido(-1);
            }
        });
    }

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

    private void inicializarLayout() {
        GridPane gridFormulario = new GridPane();
        gridFormulario.setHgap(10d);
        gridFormulario.setVgap(10d);

        textFieldLocal = new TextField();
        textFieldLocal.setPromptText("Introduzca nombre del equipo local");
        gridFormulario.add(new Label("Nombre local:"), 0, 0, 1, 1);
        gridFormulario.add(textFieldLocal, 1, 0, 1, 1);

        textFieldVisitante = new TextField();
        textFieldVisitante.setPromptText("Introduzca nombre del equipo visitante");
        gridFormulario.add(new Label("Nombre visitante:"), 0, 1, 1, 1);
        gridFormulario.add(textFieldVisitante, 1, 1, 1, 1);

        listaDivisiones = FXCollections.observableArrayList();
        listaDivisiones.add(Division.PRIMERA);
        listaDivisiones.add(Division.SEGUNDA);
        listaDivisiones.add(Division.TERCERA);
        comboDivision = new ComboBox<> (listaDivisiones);
        comboDivision.setPromptText("Elija division");
        gridFormulario.add(new Label("Division:"), 0, 2, 1, 1);
        gridFormulario.add(comboDivision, 1, 2, 1, 1);

        tfResultadoLocal = new TextField();
        tfResultadoVisitante = new TextField();
        tfResultadoLocal.setPromptText("Equipo local");
        tfResultadoVisitante.setPromptText("Equipo visitante");
        HBox hboxResultado = new HBox(10, tfResultadoLocal, new Label(" - "), tfResultadoVisitante);
        gridFormulario.add(new Label("Resultado del partido:"), 0, 3, 1, 1);
        gridFormulario.add(hboxResultado, 1, 3, 1, 1);

        fechaPartido = new DatePicker(LocalDate.now());
        gridFormulario.add(new Label("Fecha del partido:"), 0, 4, 1, 1);
        gridFormulario.add(fechaPartido, 1, 4, 1, 1);

        btnAceptar = new Button("Aceptar");
        gridFormulario.add(btnAceptar, 0, 5, 1, 1);

        Scene scene = new Scene(gridFormulario, 650, 400);
        setTitle("Alta Partido");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }

    private void addPartido(int id){
        int localRes = Integer.parseInt(tfResultadoLocal.getText());
        int visitRes = Integer.parseInt(tfResultadoVisitante.getText());

        if(localRes < 0 || visitRes < 0){
            Alerts.alertaResNegativo();
        }
        else{
            String local = textFieldLocal.getText();
            String visitante = textFieldVisitante.getText();
            Division division = comboDivision.getSelectionModel().getSelectedItem();
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
