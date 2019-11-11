package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailTreeItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;

public class EmailMainWindowController extends BaseController implements Initializable {

    @FXML
    private TableView<Mail> tablaMails;

    @FXML
    private TreeView<MailTreeItem> treeViewMail;

    @FXML
    private VBox panelPrueba;

    private Mail mail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void cargarLogin(){
        BaseController baseController = cargarVentana("EmailLoginWindow.fxml", "Login");
        baseController.abrirVentana(true);

        treeViewMail.setRoot(cargarTreeView());

    }

    @FXML
    private TreeItem cargarTreeView(){
        try {
            MailTreeItem root = new MailTreeItem(mail.toString(), mail, Logica.getInstance().getFolder());
            getFolder(root.getFolder().list(), root);
            return root;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getFolder(Folder[] folders, MailTreeItem treeItem) throws MessagingException {
        for (Folder folder : folders) {
            MailTreeItem mti = new MailTreeItem(folder.getName(), mail, folder);
            treeItem.getChildren().add(mti);
            if(folder.getType() == Folder.HOLDS_FOLDERS){
                getFolder(folder.list(), mti);
            }
        }
    }
}
