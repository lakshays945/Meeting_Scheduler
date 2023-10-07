package CRUD.demo.ResponseModels;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AddUserResponse {
    private String response;
    private Boolean added;

    public AddUserResponse(String response, Boolean added) {
        this.response = response;
        this.added = added;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }

    public AddUserResponse(){}

    @Override
    public String toString() {
        return "AddUserResponse{" +
                "response='" + response + '\'' +
                '}';
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
