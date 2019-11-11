package es.abel.dam.models;

import javafx.scene.control.TreeItem;

import javax.mail.Folder;

public class MailTreeItem extends TreeItem<String> {

    private String nombre;
    private Mail mail;
    private Folder folder;

    public Folder getFolder() {
        return folder;
    }
    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public MailTreeItem(String name, Mail mail, Folder folder){
        nombre = name;
        this.mail = mail;
        this.folder = folder;
    }

}
