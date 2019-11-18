package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import es.abel.dam.models.MailTreeItem;
import es.abel.dam.view.Alerts;
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
        rootPrincipal = new MailTreeItem("", null, null);
    }

    public void setCuenta(MailAccount mailAccount){
        if(!listaCuentas.contains(mailAccount)){
            listaCuentas.add(mailAccount);
        }
        Folder f = cargarMail(mailAccount);
        rootPrincipal.getChildren().add(cargarTreeView(mailAccount, f));
    }

    private Folder cargarMail(MailAccount mailAccount){
        try {
            Properties prop = new Properties();
            Session emailSesion = Session.getDefaultInstance(prop, null);
            store = emailSesion.getStore("imaps");
            store.connect("smtp.gmail.com", mailAccount.getAccount(), mailAccount.getPassword());
            return store.getDefaultFolder();
        }catch(MessagingException e){
            e.printStackTrace();
            Alerts.alertaCredencialesEmail();
            return null;
        }
    }

    public ObservableList<Mail> getListaMails(String carpeta, MailAccount mailAccount){
        //TODO: cambiar funcionalidad para que reciba una folder
        try {
            Folder folder = cargarMail(mailAccount).getFolder(carpeta);
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
            root.setExpanded(true);
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
