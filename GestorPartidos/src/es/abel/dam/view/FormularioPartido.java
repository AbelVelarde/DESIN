package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FormularioPartido extends Stage {

    TextField textFieldLocal;
    TextField textFieldVisitante;
    ObservableList<Division> listaDivisiones;
    ComboBox<Division> comboDivision;
    TextField tfResultadoLocal;
    TextField tfResultadoVisitante;
    DatePicker fechaPartido;
    Button btnAceptar;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public FormularioPartido() {
        inicializarLayout();
        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addPartido();
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
        String fechaString = sdf.format(partidoEditar.getFecha());
        LocalDate fechaLocal = LocalDate.parse(fechaString);
        fechaPartido.setValue(fechaLocal);

        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                editarPartido(id);
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
        comboDivision = new ComboBox(listaDivisiones);
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

    private void addPartido(){
        String local = textFieldLocal.getText();
        String visitante = textFieldVisitante.getText();
        Division division = comboDivision.getSelectionModel().getSelectedItem();
        Resultado resultado = new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText()));
        Date fecha = new Date();
        try {
            fecha = sdf.parse(fechaPartido.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Partido partido = new Partido(local, visitante, division, resultado, fecha);
        Logica.getINSTANCE().añadirPartido(partido);

        close();
    }

    private void editarPartido(int id){
        String local = textFieldLocal.getText();
        String visitante = textFieldVisitante.getText();
        Division division = comboDivision.getValue();
        Resultado resultado = new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText()));
        Date fecha = new Date();
        try {
            fecha = sdf.parse(fechaPartido.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Partido partidoEditado = new Partido(local, visitante, division, resultado, fecha);



    }

}
