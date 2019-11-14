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

    private MailAccount mailAccount;

    TreeItem root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeViewMail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> stringTreeItem, TreeItem<String> newValue) {
                String carpeta = "";
                while(newValue.getParent()!=null){
                    carpeta =  newValue.toString() + "/" + carpeta;
                    newValue = newValue.getParent();
                }
                StringBuilder str = new StringBuilder(carpeta);
                str.delete(carpeta.length()-1, carpeta.length());
                carpeta = str.toString();
                tablaMails.setItems(Logica.getInstance().getListaMails(carpeta));
            }
        });

        tablaMails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mail>() {
            @Override
            public void changed(ObservableValue<? extends Mail> observableValue, Mail mail, Mail newValue) {
                String mensaje = newValue.getContenido();
                WebEngine webEngine = wvMail.getEngine();
                webEngine.loadContent(mensaje);
            }
        });
    }

    @FXML
    private void cargarLogin(){
        BaseController baseController = cargarVentana("EmailLoginWindow.fxml", "Login");
        baseController.abrirVentana(true);

        tablaMails.setItems(Logica.getInstance().getListaMails("INBOX"));

        mailAccount = Logica.getInstance().getMailAccount();
        cargarTreeView();
        treeViewMail.setRoot(root);
        root.setExpanded(true);
    }

    private void cargarTreeView(){
        try {
           root = new MailTreeItem(mailAccount.getAccount(), mailAccount, Logica.getInstance().getFolder());
           getFolder(((MailTreeItem)root).getFolder().list(), (MailTreeItem) root);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void getFolder(Folder[] folders, MailTreeItem item) throws MessagingException {
        for (Folder folder : folders) {
            MailTreeItem mti = new MailTreeItem(folder.getName(), mailAccount, folder);
            item.getChildren().add(mti);
            if(folder.getType() == Folder.HOLDS_FOLDERS){
                mti.setExpanded(true);
                getFolder(folder.list(), mti);
            }
        }
    }
}
