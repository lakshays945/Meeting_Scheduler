package CRUD.demo.ResponseModels;


import CRUD.demo.Models.Meeting;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class MeetingByUserNameResponse {
    private List<Meeting> meetingList;
    public MeetingByUserNameResponse() {
    }
    public MeetingByUserNameResponse(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
}
