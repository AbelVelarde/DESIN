package es.abel.dam.models;

import javax.mail.Message;
import javax.mail.MessagingException;

public class Mail{

    private String asunto;
    private String remitente = "";

    public void setAsunto(Message mensaje){
        try {
            asunto = mensaje.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getAsunto(){
        return asunto;
    }

    private void setRemitente(Message mensaje){
        try {
            int n = mensaje.getFrom().length;

            for (int i = 0; i < n ; i++) {
                if(i == 0){
                    remitente = mensaje.getFrom()[i].toString();
                }
                remitente = remitente + ", " + mensaje.getFrom()[i].toString();
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getRemitente(){
        return remitente;
    }

    public Mail(Message mensaje){
        setAsunto(mensaje);
        setRemitente(mensaje);
    }
}
