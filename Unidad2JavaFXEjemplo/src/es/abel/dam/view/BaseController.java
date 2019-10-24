package es.abel.dam.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {

    private Stage stage;

    protected Stage getStage(){
        return stage;
    }

    /**
     * Cargar una nueva ventana
     * @param fxml fxml a cargar
     * @param titulo titulo de la ventana
     * @param anchura anchura de la ventada
     * @param altura altura de la ventana
     * @return Controlador de la pantalla a crear. Hay que llamar posterior
     */
    protected BaseController cargarDialogo(String fxml, String titulo, int anchura, int altura){
        try {
            //Cargar el FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();
            BaseController controller = fxmlLoader.getController();
            controller.cargarStage(root, titulo, anchura, altura);
            return controller;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void abrirDialogo(boolean wait){
        if(stage != null){
            if (wait) {
                stage.showAndWait();
            } else {
                stage.show();
            }
        }
        else{
            throw new IllegalStateException("Hay que llamar a cargarDialogo primero.");
        }

    }

    private void cargarStage(Parent root, String titulo, int anchura, int altura){
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titulo);
        stage.setScene(new Scene(root, anchura, altura));
    }

}
