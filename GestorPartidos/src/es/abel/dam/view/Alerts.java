package es.abel.dam.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerts {

    public static boolean alertaBorradoConfim() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("");
        alert.setContentText("¿Desea borrar este partido? No podrá recuperarlo más adelante.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static void alertaBorradoNoSelec() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, hay que seleccionar un partido para poder borrar.");
        alert.showAndWait();
    }

    public static void alertaResNegativo(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, un equipo no puede tener un resultado negativo.");
        alert.showAndWait();
    }

}
