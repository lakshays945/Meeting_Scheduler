package CRUD.demo.RequestModels;

public class DeleteMeetingRequest {
    private Long meetingID;
    public DeleteMeetingRequest(){}
    public DeleteMeetingRequest(Long meetingID){
        this.meetingID = meetingID;
    }
    public Long getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Long meetingID) {
        this.meetingID = meetingID;
    }
}
