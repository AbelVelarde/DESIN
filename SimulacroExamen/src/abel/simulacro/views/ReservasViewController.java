package abel.simulacro.views;

import abel.simulacro.logica.Logica;
import abel.simulacro.models.Reserva;
import abel.simulacro.utils.DateUtils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ReservasViewController implements Initializable {

    @FXML
    private ComboBox<String> cbAulas;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnReservar;
    @FXML
    private TableView<Reserva> tvAulas;
    @FXML
    private Button btnLiberar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbAulas.setItems(Logica.getInstance().getListaAulas());
        tvAulas.setItems(Logica.getInstance().getListaReservas());
    }

    @FXML
    public void reservar(){
        String aula = cbAulas.getSelectionModel().getSelectedItem();
        Date fecha = DateUtils.convertToDate(datePicker.getValue());

        Reserva reserva = new Reserva(aula, fecha);

        Logica.getInstance().addReserva(reserva);
    }

    @FXML
    public void liberar(){
        Reserva reserva = tvAulas.getSelectionModel().getSelectedItem();

        Logica.getInstance().removeReserva(reserva);
    }
}
