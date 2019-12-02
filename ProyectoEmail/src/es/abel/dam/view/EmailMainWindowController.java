package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import es.abel.dam.models.MailTreeItem;
import es.abel.dam.servicios.GetMailsService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Service;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailMainWindowController extends BaseController implements Initializable {

    @FXML
    private TableView<Mail> tablaMails;
    @FXML
    private TreeView<String> treeViewMail;
    @FXML
    private WebView wvMail;
    @FXML
    private ProgressIndicator mainProgress;
    @FXML
    private Button btnMgResponder;
    @FXML
    private Button btnMgReenviar;
    @FXML
    private Button btnMgBorrar;

    private TreeItem root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
            TODO: rowfactory to unbold msgs.
            devolver nueva tableRow.
            update item.
            Si el mensaje no es nulo.
            Si el mensaje no esta leido: bold
            Else: nada.
         */


        btnMgResponder.setDisable(true);
        btnMgReenviar.setDisable(true);
        btnMgBorrar.setDisable(true);
        mainProgress.setVisible(false);

        treeViewMail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> oldValue, TreeItem<String> newValue) {
                MailTreeItem newmti = (MailTreeItem)newValue;
                MailTreeItem oldmti = (MailTreeItem)oldValue;

                if(oldmti != null && oldmti.getFolder().isOpen()){
                    try {
                        oldmti.getFolder().close();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                refrescarTabla(newmti);
            }
        });

        tablaMails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mail>() {
            @Override
            public void changed(ObservableValue<? extends Mail> observableValue, Mail mail, Mail newValue) {
                if(newValue!=null){
                    btnMgResponder.setDisable(false);
                    btnMgReenviar.setDisable(false);
                    btnMgBorrar.setDisable(false);
                    String mensaje = newValue.getContenido();
                    WebEngine webEngine = wvMail.getEngine();
                    webEngine.loadContent(mensaje);
                }
                else{
                    btnMgResponder.setDisable(true);
                    btnMgReenviar.setDisable(true);
                    btnMgBorrar.setDisable(true);
                }
            }
        });
    }

    @FXML
    private void cargarLogin(){
        BaseController controller = cargarVentana("EmailLoginWindow.fxml", "Login");
        controller.abrirVentana(true);

        //tablaMails.setItems(Logica.getInstance().getDefaultMails());

        root = Logica.getInstance().getRootPrincipal();
        treeViewMail.setRoot(root);
        treeViewMail.setShowRoot(false);
        root.setExpanded(true);
    }

    @FXML
    private void cargarCorreo(){
        BaseController controller = cargarVentana("EmailMensajeWindow.fxml", "Correo");
        controller.abrirVentana(true);
    }

    @FXML
    private void borrarCorreo(){
        MailTreeItem mti = (MailTreeItem)treeViewMail.getSelectionModel().getSelectedItem();
        Mail mail = tablaMails.getSelectionModel().getSelectedItem();
        Folder folder = mti.getFolder();
        MailAccount mailAccount = mti.getMailAccount();

        Logica.getInstance().deleteMail(mail, folder, mailAccount);

        refrescarTabla(mti);
    }

    @FXML
    private void cambiarTemas(){
        BaseController controller = cargarVentana("EmailTemasWindow.fxml", "Temas");
        controller.abrirVentana(true);
    }

    private void refrescarTabla(MailTreeItem mti){
        GetMailsService gms = new GetMailsService(mti.getFolder());
        gms.start();
        gms.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                mainProgress.setVisible(true);
            }
        });
        gms.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                tablaMails.setItems(gms.getValue());
                mainProgress.setVisible(false);
            }
        });
    }
}
