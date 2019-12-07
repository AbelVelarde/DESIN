package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import javax.mail.Folder;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailMensajeWindowController extends BaseController implements Initializable {

    @FXML
    private ComboBox<MailAccount> cbAcounts;
    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private TextField tfDestinatario;
    @FXML
    private TextField tfAsunto;

    private Mail mail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbAcounts.setItems(Logica.getInstance().getAccountList());
    }

    @FXML
    private void cancelar(){
        getStage().close();
    }

    @FXML
    private void enviar(){
        String contenido = htmlEditor.getHtmlText();
        if(mail != null){
            contenido = contenido + "<br><br>" +
                    "<br> " + mail.getFecha() + ", " +
                    mail.getDestinatario()[0] + " escribio: " +
                    "<br> " + mail.getContenido();
        }
        MailAccount remitente = cbAcounts.getSelectionModel().getSelectedItem();
        String[] destinatarios = tfDestinatario.getText().split(", ");
        String asunto = tfAsunto.getText();

        Logica.getInstance().createNewMessage(contenido, remitente, destinatarios, asunto);

        getStage().close();
    }

    public void reenviar(Mail mail, MailAccount mailAccount) {
        String contenido = "---------- Mensaje reenviado ----------" +
                "<br>De: " + mail.getRemitente() +
                "<br>Fecha: " + mail.getFecha()  +
                "<br>Asunto: " + mail.getAsunto()  +
                "<br>Para: " + mail.getDestinatario()[0] +
                "<br>" +
                "<br>" + mail.getContenido();
        cbAcounts.getSelectionModel().select(mailAccount);
        cbAcounts.setDisable(true);
        htmlEditor.setHtmlText(contenido);
        tfAsunto.setText("FW: " + mail.getAsunto());
    }

    public void responder(Mail mail, MailAccount mailAccount) {
        this.mail = mail;
        cbAcounts.getSelectionModel().select(mailAccount);
        cbAcounts.setDisable(true);
        tfDestinatario.setText(mail.getDestinatario()[0]);
        tfAsunto.setText("RE: " + mail.getAsunto());
    }
}
