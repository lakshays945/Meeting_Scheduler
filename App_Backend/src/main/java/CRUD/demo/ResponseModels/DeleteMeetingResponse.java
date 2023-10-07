package CRUD.demo.ResponseModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DeleteMeetingResponse {
    private String response;
    private Boolean valid;
    public DeleteMeetingResponse(){}

    public DeleteMeetingResponse(String response, Boolean valid) {
        this.response = response;
        this.valid = valid;
    }

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
