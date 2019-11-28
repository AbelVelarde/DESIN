package es.abel.dam.models;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.util.MimeMessageParser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mail{

    private Message message;

    public Message getMessage(){
        return message;
    }

    public String getAsunto(){
        try {
            return message.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRemitente(){
        try {
            return message.getFrom()[0].toString();
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getContenido(){
        MimeMessageParser mimeParser = new MimeMessageParser((MimeMessage) message);
        try {
            mimeParser.parse();
            return mimeParser.getHtmlContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        try {
            Date date = message.getReceivedDate();
            return sdf.format(date);
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mail(Message message){
        this.message = message;
    }
}
