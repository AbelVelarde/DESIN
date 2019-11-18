package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import es.abel.dam.models.MailTreeItem;
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

    private MailTreeItem rootPrincipal;

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
            rootPrincipal = new MailTreeItem("", mailAccount, null);

            Properties prop = new Properties();
            Session emailSesion = Session.getDefaultInstance(prop, null);
            store = emailSesion.getStore("imaps");
            store.connect("smtp.gmail.com", mailAccount.getAccount(), mailAccount.getPassword());
            Folder f = store.getDefaultFolder();

            rootPrincipal.getChildren().add(cargarTreeView(mailAccount, f));

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

    private MailTreeItem cargarTreeView(MailAccount mailAccount, Folder folder){
        try {
            MailTreeItem root = new MailTreeItem(mailAccount.getAccount(), mailAccount, folder);
            getFolder((root).getFolder().list(), root, mailAccount);
            return root;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getFolder(Folder[] folders, MailTreeItem item, MailAccount mailAccount) throws MessagingException {
        for (Folder folder : folders) {
            MailTreeItem mti = new MailTreeItem(folder.getName(), mailAccount, folder);
            item.getChildren().add(mti);
            if(folder.getType() == Folder.HOLDS_FOLDERS){
                mti.setExpanded(true);
                getFolder(folder.list(), mti, mailAccount);
            }
        }
    }

    public MailTreeItem getRootPrincipal(){
        return rootPrincipal;
    }

}
