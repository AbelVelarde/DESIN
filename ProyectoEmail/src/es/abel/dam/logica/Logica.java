package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Mail> listaMails;
    private Store store;

    public static Logica getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica(){
        listaMails = FXCollections.observableArrayList();
    }

    public void setListaMails(String mail, String pwd){
        try{
            Properties prop = new Properties();
            Session emailSesion = Session.getDefaultInstance(prop, null);
            store = emailSesion.getStore("imaps");
            store.connect("smtp.gmail.com", mail, pwd);



//            Folder emailFolder = store.getFolder("INBOX");
//            emailFolder.open(Folder.READ_ONLY);
//            Message[] messages = emailFolder.getMessages();
//            listaMails = FXCollections.observableArrayList();
//            for (Message message: messages) {
//                listaMails.add(new Mail(message));
//            }


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Mail> getListaMails(){
        return listaMails;
    }

    public Store getStore() throws MessagingException {
        return store;
    }


}
