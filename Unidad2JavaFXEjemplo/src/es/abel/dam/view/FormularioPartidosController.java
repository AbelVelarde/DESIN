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

public class FormularioPartidosController extends BaseController implements Initializable {

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

        String local = tfLocal.getText();
        String visitante = tfVisitante.getText();
        Division div = cbDivision.getValue();
        String localRes =tfResultadoLocal.getText();
        String visitRes = tfResultadoVisitante.getText();
        Date fecha = DateUtils.convertToDate(datePickerFecha.getValue());

        if(!localRes.matches("[0-9]+") || !visitRes.matches("[0-9]+")){
            Alerts.alertaResultado();
        }
        //Comprueba que el nombre de los equipos no este en blanco
        else if(!(local.trim().length() > 0) || !(visitante.trim().length() > 0)){
            Alerts.alertaNombre();
        }
        //Comprueba que se halla elegido una division
        else if(div == null){
            Alerts.alertaDivisionNula();
        }
        else {
            Resultado res = new Resultado(Integer.parseInt(localRes), Integer.parseInt(visitRes));
            if (partidoEditar == null) {
                Partido partido = new Partido(local, visitante, div, res, fecha);
                Logica.getINSTANCE().addPartido(partido);
            } else {
                partidoEditar.setLocal(local);
                partidoEditar.setVisitante(visitante);
                partidoEditar.setDivision(div);
                partidoEditar.setResultado(res);
                partidoEditar.setFecha(fecha);
                Logica.getINSTANCE().setPartido(partidoEditar);
            }
            //Obtener stage desde un evento
            //((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            getStage().close();
        }
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

