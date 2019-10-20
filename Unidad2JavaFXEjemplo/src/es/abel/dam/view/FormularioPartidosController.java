package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import es.abel.dam.utils.DateUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioPartidosController {

    private int id = -1;

    @FXML
    private TextField tfLocal;
    @FXML
    private TextField tfVisitante;
    @FXML
    private ComboBox<Division> cbDivision;
    @FXML
    private TextField tfResultadoLocal;
    @FXML
    private TextField tfResultadoVisitante;
    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private void añadirPartido(ActionEvent event){
        Partido partido = new Partido(tfLocal.getText(),
                tfVisitante.getText(),
                cbDivision.getValue(),
                new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText())),
                DateUtils.convertToDate(datePickerFecha.getValue()));

        if (id == -1) {
            Logica.getINSTANCE().addPartido(partido);
        }
        else{
            Logica.getINSTANCE().setPartido(partido, id);
        }
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void editarPartido(Partido partido){
        tfLocal.setText(partido.getLocal());
        tfVisitante.setText(partido.getVisitante());
        cbDivision.setValue(partido.getDivision());
        tfResultadoLocal.setText(""+partido.getResultado().getResultadoLocal());
        tfResultadoVisitante.setText(""+partido.getResultado().getResultadoVisitante());
        datePickerFecha.setValue(DateUtils.convertToLocalDate(partido.getFecha()));

        id = Logica.getINSTANCE().getListaPartidos().indexOf(partido);
    }

}

