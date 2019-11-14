package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;

    private ObservableList<Mail> listaMails;
    private ArrayList<MailAccount> listaCuentas;
    private Store store;
    private MailAccount mailAccount;

    public static Logica getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica(){
        listaCuentas = new ArrayList<>();
        listaMails = FXCollections.observableArrayList();
    }

    public void setListaMails(MailAccount mailAccount){
        try{
            listaCuentas.add(mailAccount);
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

    public ObservableList<Mail> getListaMails(String carpeta){
        Folder folder = null;
        try {
            folder = store.getFolder(carpeta);
            folder.open(1);
            Message[] messages = folder.getMessages();
            listaMails.clear();
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
