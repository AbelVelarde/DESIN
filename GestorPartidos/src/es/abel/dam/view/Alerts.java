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

    public static void alertaResultado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, un resultado introducido es negativo o no es un numero entero.");
        alert.showAndWait();
    }

    public static void alertaNombre() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, el nombre no puede quedar en blanco.");
        alert.showAndWait();
    }

    public static void alertaDivisionNula() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, escoja division.");
        alert.showAndWait();
    }

}
