package es.abel.dam.models;

import javax.mail.Message;
import javax.mail.MessagingException;

public class Mail{

    private Message message;

    public String getAsunto(){
        try {
            return message.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRemitente(){
        String remitente = "";
        try {
            int n = message.getFrom().length;
            for (int i = 0; i < n ; i++) {
                if(i == 0){
                    remitente = message.getFrom()[i].toString();
                }
                else{
                    remitente = remitente + ", " + message.getFrom()[i].toString();
                }
            }
            return remitente;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mail(Message message){
        this.message = message;
    }
}
