package CRUD.demo.ResponseModels;

import CRUD.demo.Models.Meeting;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class LoginUserResponse {
    private String response;
    private Boolean valid;


    public LoginUserResponse(String response, Boolean valid) {
        this.response = response;
        this.valid = valid;
    }

    public LoginUserResponse(){}

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

}
