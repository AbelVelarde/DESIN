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

    public void setAccount(MailAccount mailAccount){
        if(!listaCuentas.contains(mailAccount)){
            listaCuentas.add(mailAccount);
        }
        Folder f = loadMail(mailAccount);
        rootPrincipal.getChildren().add(getTreeItems(mailAccount, f));
    }

    private Folder loadMail(MailAccount mailAccount){
        try {
            Properties prop = new Properties();
            Session emailSesion = Session.getDefaultInstance(prop, null);
            store = emailSesion.getStore("imaps");
            store.connect("imap.gmail.com", mailAccount.getAccount(), mailAccount.getPassword());
            return store.getDefaultFolder();
        }catch(MessagingException e){
            e.printStackTrace();
            Alerts.alertaCredencialesEmail();
            return null;
        }
    }

    public ObservableList<Mail> getDefaultMails(){
        try {
            return getMailList(store.getDefaultFolder().getFolder("INBOX"));
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Mail> getMailList(Folder carpeta){
        try {
            if(!carpeta.isOpen()){
                carpeta.open(1);
            }
            Message[] messages = carpeta.getMessages();
            listaMails.clear();
            for (Message message: messages) {
                listaMails.add(new Mail(message));
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return listaMails;
    }

    public MailTreeItem getRootPrincipal(){
        return rootPrincipal;
    }

    private MailTreeItem getTreeItems(MailAccount mailAccount, Folder folder){
        try {
            MailTreeItem root = new MailTreeItem(mailAccount.getAccount(), mailAccount, folder);
            getFolder(root.getFolder().list(), root, mailAccount);
            root.setExpanded(true);
            return root;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param folders Array de carpetas
     * @param item
     * @param mailAccount
     * @throws MessagingException
     */
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
}
