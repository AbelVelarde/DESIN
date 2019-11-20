package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailTreeItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.mail.Folder;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailMainWindowController extends BaseController implements Initializable {

    @FXML
    private TableView<Mail> tablaMails;

    @FXML
    private TreeView<String> treeViewMail;

    @FXML
    private WebView wvMail;

    private TreeItem root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeViewMail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> oldValue, TreeItem<String> newValue) {
//                Folder oldFolder =  ((MailTreeItem)oldValue).getFolder();
//                if(oldFolder.isOpen()){
//                    oldFolder.close();
//                }
                MailTreeItem mti = (MailTreeItem)newValue;
                tablaMails.setItems(Logica.getInstance().getMailList(mti.getFolder(), mti.getMailAccount()));
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

        tablaMails.setItems(Logica.getInstance().getDefaultMails(((EmailLoginWindowController)controller).getMailAccount()));

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
