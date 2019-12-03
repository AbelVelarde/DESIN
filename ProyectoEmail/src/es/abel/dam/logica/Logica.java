package es.abel.dam.logica;

import es.abel.dam.models.Mail;
import es.abel.dam.models.MailAccount;
import es.abel.dam.models.MailTreeItem;
import es.abel.dam.view.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;

    private Properties props;
    private Session session = null;

    private ObservableList<Mail> listaMails;
    private ObservableList<MailAccount> listaCuentas;

    private MailTreeItem rootPrincipal;

    public static Logica getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public Logica() {
        props = new Properties();
//        props.put("incomingHost", "imap.gmail.com");
//        props.put("mail.store.protocol", "imaps");
//        props.put("mail.transport.protocol", "smtps");
//        props.put("mail.smtps.host", "smtp.gmail.com");
//        props.put("mail.smtps.auth", true);
//        props.put("outgoingHost", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.user","username");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
//        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.EnableSSL.enable","true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        listaCuentas = FXCollections.observableArrayList();
        listaMails = FXCollections.observableArrayList();
        rootPrincipal = new MailTreeItem("", null, null);
    }

    public ObservableList<MailAccount> getAccountList(){
        return listaCuentas;
    }

    public void setAccount(MailAccount mailAccount) {
        if (!listaCuentas.contains(mailAccount)) {
            listaCuentas.add(mailAccount);
            Folder f = loadMail(mailAccount);
            rootPrincipal.getChildren().add(getTreeItems(mailAccount, f));
        }
    }

    private void createSession(MailAccount mailAccount){
        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailAccount.getAccount(), mailAccount.getPassword());
            }
        });
    }

    private Folder loadMail(MailAccount mailAccount) {
        try {
            createSession(mailAccount);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", mailAccount.getAccount(), mailAccount.getPassword());
            mailAccount.setStore(store);
            return store.getDefaultFolder();
        } catch (MessagingException e) {
            e.printStackTrace();
            Alerts.alertaCredencialesEmail();
            return null;
        }
    }

    public ObservableList<Mail> getDefaultMails(MailAccount mailAccount) {
        try {
            return getMailList(mailAccount.getStore().getDefaultFolder().getFolder("INBOX"));
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createNewMessage(String contenido, MailAccount mailAccount, String[] destinatarios, String asunto){
        createSession(mailAccount);
        MimeMessage mimeMessage = new MimeMessage(session);

        try{
            mimeMessage.setContent(contenido, "text/html");
            //mimeMessage.setText(contenido);
            mimeMessage.setFrom(mailAccount.getAccount());

            InternetAddress[] to = new InternetAddress[destinatarios.length];
            for(int i=0; i<destinatarios.length; i++){
                to[i] = new InternetAddress(destinatarios[i]);
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, to);
            mimeMessage.setSubject(asunto);

            Transport.send(mimeMessage);

        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Mail> getMailList(Folder carpeta) {
        try {
            if (carpeta != null && carpeta.getType() == 3) {
                if (!carpeta.isOpen()) {
                    carpeta.open(Folder.READ_WRITE);
                }
                Message[] messages = carpeta.getMessages();
                listaMails.clear();
                for (Message message : messages) {
                    listaMails.add(new Mail(message));
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return listaMails;
    }

    public MailTreeItem getRootPrincipal() {
        return rootPrincipal;
    }

    private MailTreeItem getTreeItems(MailAccount mailAccount, Folder folder) {
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
     * @param folders     Array de carpetas
     * @param item
     * @param mailAccount
     * @throws MessagingException
     */
    private void getFolder(Folder[] folders, MailTreeItem item, MailAccount mailAccount) throws MessagingException {
        for (Folder folder : folders) {
            MailTreeItem mti = new MailTreeItem(folder.getName(), mailAccount, folder);
            item.getChildren().add(mti);
            if (folder.getType() == Folder.HOLDS_FOLDERS) {
                mti.setExpanded(true);
                getFolder(folder.list(), mti, mailAccount);
            }
        }
    }

    public void deleteMail(Mail mail, Folder folder, MailAccount mailAccount) {
        Message m = mail.getMessage();
        try {
            if (!folder.getName().equals("Papelera")) {
                Folder papelera = mailAccount.getStore().getDefaultFolder().getFolder("[Gmail]/Papelera");
                folder.copyMessages(new Message[]{m}, papelera);
                folder.close();
            } else {
                m.setFlag(Flags.Flag.DELETED, true);
                folder.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
