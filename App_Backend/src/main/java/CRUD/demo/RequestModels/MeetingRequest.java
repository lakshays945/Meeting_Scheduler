package CRUD.demo.RequestModels;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MeetingRequest {
    private String userName;
    private Long start;
    private Long end;

    public Long getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "MeetingRequest{" +
                "userName='" + userName + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public MeetingRequest(String userName, Long start, Long end) {
        this.userName = userName;
        this.start = start;
        this.end = end;
    }

    public Long getEnd() {
        return end;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getUserName() {
        return userName;
    }
}
