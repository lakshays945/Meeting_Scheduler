package CRUD.demo.RequestModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DeleteUserRequest {
    private String userName;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
