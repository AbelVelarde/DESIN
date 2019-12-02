package es.abel.dam.models;

import javax.mail.Store;
import java.util.Objects;

public class MailAccount{

    private String account;
    private String password;
    private Store store;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAccount that = (MailAccount) o;
        return Objects.equals(account, that.account) &&
                Objects.equals(password, that.password);
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    @Override
    public String toString(){
        return account;
    }
}
