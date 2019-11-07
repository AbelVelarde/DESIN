package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Mail> listaMails;

    public static Logica getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica(){
        listaMails = FXCollections.observableArrayList();
    }

    public ObservableList<Mail> getListaMails(){
        try{
            Properties prop = new Properties();

            Session emailSesion = Session.getDefaultInstance(prop, null);

            Store store = emailSesion.getStore("imaps");

            store.connect("smtp.gmail.com", "sandierparapromociones@gmail.com", "abelvelarde97");

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();

            listaMails = FXCollections.observableArrayList();
            for (Message message: messages) {
                listaMails.add(new Mail(message));
            }

            //emailFolder.close(true);
            //store.close();

            return listaMails;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
