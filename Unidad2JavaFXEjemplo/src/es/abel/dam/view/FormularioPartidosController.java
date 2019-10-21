package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import es.abel.dam.utils.DateUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class FormularioPartidosController implements Initializable {

    private Partido partidoEditar = null;

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
        if (partidoEditar == null) {
            Partido partido = new Partido(tfLocal.getText(),
                    tfVisitante.getText(),
                    cbDivision.getValue(),
                    new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText())),
                    DateUtils.convertToDate(datePickerFecha.getValue()));
            Logica.getINSTANCE().addPartido(partido);
        }
        else{
            partidoEditar.setLocal(tfLocal.getText());
            partidoEditar.setVisitante(tfVisitante.getText());
            partidoEditar.setDivision(cbDivision.getValue());
            partidoEditar.setResultado(new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText())));
            partidoEditar.setFecha(DateUtils.convertToDate(datePickerFecha.getValue()));
            Logica.getINSTANCE().setPartido(partidoEditar);
        }
        //Obtener stage desde un evento
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void setPartidoEditar(Partido partido){
        partidoEditar = partido;

        tfLocal.setText(partido.getLocal());
        tfVisitante.setText(partido.getVisitante());
        cbDivision.setValue(partido.getDivision());
        tfResultadoLocal.setText(""+partido.getResultado().getResultadoLocal());
        tfResultadoVisitante.setText(""+partido.getResultado().getResultadoVisitante());
        datePickerFecha.setValue(DateUtils.convertToLocalDate(partido.getFecha()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePickerFecha.setValue(LocalDate.now());
    }

}

