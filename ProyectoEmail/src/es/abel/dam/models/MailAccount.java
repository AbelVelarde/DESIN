package es.abel.dam.models;

public class MailAccount {

    private String account;
    private String password;

    public MailAccount(String account, String password){
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String mail) {
        this.account = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
