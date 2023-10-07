package CRUD.demo.RequestModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class LoginUserRequest {
    private String userName;
    private String passWord;

    public LoginUserRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public LoginUserRequest(){}

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
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
