package CRUD.demo.RequestModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AddUserRequest {
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public AddUserRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
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
