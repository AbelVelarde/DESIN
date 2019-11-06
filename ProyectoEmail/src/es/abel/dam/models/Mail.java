package es.abel.dam.models;

import javax.mail.Message;
import javax.mail.MessagingException;

public class Mail{

    private Message message;

    private String asunto;

    public String getAsunto(){
        return asunto;
//        try {
//            return message.getSubject();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return null;
//        }
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
        try{
            asunto = message.getSubject();
        }catch (MessagingException e) {
            e.printStackTrace();
        }
        this.message = message;
    }
}
