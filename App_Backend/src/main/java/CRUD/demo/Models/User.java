package CRUD.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @Column(name = "Username")
    String userName;

    @Column(name = "Password")
    String passWord;

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
    public User() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
