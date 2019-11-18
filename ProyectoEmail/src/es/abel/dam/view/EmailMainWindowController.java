package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import es.abel.dam.models.MailTreeItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;

public class EmailMainWindowController extends BaseController implements Initializable {

    @FXML
    private TableView<Mail> tablaMails;

    @FXML
    private TreeView<String> treeViewMail;

    @FXML
    private WebView wvMail;

    TreeItem root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeViewMail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> stringTreeItem, TreeItem<String> newValue) {
                if(((MailTreeItem)newValue.getParent()).getFolder() != null){
                    String carpeta = "";
                    while(newValue.getParent().getParent()!=null){
                        carpeta =  newValue.toString() + "/" + carpeta;
                        newValue = newValue.getParent();
                    }
                    StringBuilder str = new StringBuilder(carpeta);
                    str.delete(carpeta.length()-1, carpeta.length());
                    carpeta = str.toString();
                    System.out.println(carpeta);
                    tablaMails.setItems(Logica.getInstance().getListaMails(carpeta, ((MailTreeItem)newValue).getMailAccount()));
                }
            }
        });

        tablaMails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mail>() {
            @Override
            public void changed(ObservableValue<? extends Mail> observableValue, Mail mail, Mail newValue) {
                if(newValue!=null){
                    String mensaje = newValue.getContenido();
                    WebEngine webEngine = wvMail.getEngine();
                    webEngine.loadContent(mensaje);
                }
            }
        });
    }

    @FXML
    private void cargarLogin(){
        BaseController controller = cargarVentana("EmailLoginWindow.fxml", "Login");
        controller.abrirVentana(true);

        tablaMails.setItems(Logica.getInstance().getListaMails("INBOX", ((EmailLoginWindowController)controller).getMailAccount()));

        root = Logica.getInstance().getRootPrincipal();
        treeViewMail.setRoot(root);
        treeViewMail.setShowRoot(false);
        root.setExpanded(true);
    }

    @FXML
    private void cambiarTemas(){
        BaseController controller = cargarVentana("EmailTemasWindow.fxml", "Temas");
        controller.abrirVentana(true);
    }


}
