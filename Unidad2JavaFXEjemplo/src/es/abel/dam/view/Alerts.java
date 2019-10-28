package es.abel.dam.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerts {

    /**
     * Alerta para confirmar el borrado
     * @return true si se presiona el boton "aceptar"
     */
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

    /**
     * Alerta de error de borrado o edicion cuando no se selecciona una fila de la tabla
     */
    public static void alertaNoSelec() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, hay que seleccionar un partido primero.");
        alert.showAndWait();
    }

    /**
     * Alerta cuando el resultado (local o visitante) es no es valido (negativo o no entero)
     */
    public static void alertaResultado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, un resultado introducido es negativo o no es un numero entero.");
        alert.showAndWait();
    }

    /**
     * Alerta cuando el nombre del equipo (local o visitante) esta en blanco
     */
    public static void alertaNombre() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, el nombre no puede quedar en blanco.");
        alert.showAndWait();
    }

    /**
     * Alerta cuando no se selecciona una division
     */
    public static void alertaDivisionNula() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, escoja division.");
        alert.showAndWait();
    }

    /**
     * Alerta cuando el fichero a cargar no coincide
     */
    public static void alertaCargarFichero(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Error, no se puede cargar correctamente el fichero seleccionado");
        alert.showAndWait();
    }

}
