package CRUD.demo.RequestModels;

import CRUD.demo.Models.Meeting;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class MeetingByUserNameRequest {
    private String userName;

    public MeetingByUserNameRequest() {
    }

    public MeetingByUserNameRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
