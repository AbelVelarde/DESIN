package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Mail> listaMails;
    private Store store;
    private MailAccount mailAccount;

    public static Logica getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica(){
        listaMails = FXCollections.observableArrayList();
    }

    public void setListaMails(MailAccount mailAccount){
        try{
            this.mailAccount = mailAccount;
            Properties prop = new Properties();
            Session emailSesion = Session.getDefaultInstance(prop, null);
            store = emailSesion.getStore("imaps");
            store.connect("smtp.gmail.com", mailAccount.getAccount(), mailAccount.getPassword());

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Mail> getListaMails(){
        Folder folder = null;
        try {
            folder = store.getFolder("INBOX");
            folder.open(1);
            Message[] messages = folder.getMessages();
            for (Message message: messages) {
                listaMails.add(new Mail(message));
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }



        return listaMails;
    }

    public Folder getFolder() throws MessagingException {
        return store.getDefaultFolder();
    }

    public MailAccount getMailAccount(){
        return mailAccount;
    }


}
