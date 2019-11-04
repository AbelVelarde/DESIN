package pruebas;

import javax.mail.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
    }

    private Main(){

        try{
            Properties prop = new Properties();
//            prop.put("mail.pop3.host", "pop.gmail.com");
//            prop.put("mail.pop3.port", "995");
//            prop.put("mail.pop3.auth", "true");
//            prop.put("mail.pop3.starttls.enable", "true");

            Session emailSesion = Session.getDefaultInstance(prop, null);

            Store store = emailSesion.getStore("imaps");

            store.connect("smtp.gmail.com", "sandierparapromociones@gmail.com", "abelvelarde97");

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + emailFolder.getMessageCount());

            for(int i=0; i<messages.length; i++){
                System.out.println("Subject = " + messages[i].getSubject());
            }

            emailFolder.close(true);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
