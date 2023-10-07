package CRUD.demo.Controllers;


import CRUD.demo.Models.Meeting;
import CRUD.demo.Models.User;
import CRUD.demo.Repositories.MeetingRepository;
import CRUD.demo.RequestModels.DeleteMeetingRequest;
import CRUD.demo.RequestModels.MeetingByUserNameRequest;
import CRUD.demo.RequestModels.MeetingRequest;
import CRUD.demo.ResponseModels.AddMeetingResponse;
import CRUD.demo.ResponseModels.DeleteMeetingResponse;
import CRUD.demo.ResponseModels.MeetingByUserNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3001","http://localhost:3000"} )
public class MeetingController {
    @Autowired
    MeetingRepository meetingRepository;

    @PostMapping("/getMeetings")
    ResponseEntity<MeetingByUserNameResponse> getAllMeetingsByUserName(@RequestBody MeetingByUserNameRequest meetingByUserNameRequest){
        MeetingByUserNameResponse meetingByUserNameResponse = new MeetingByUserNameResponse();
        List<Meeting> meetingList = meetingRepository.getMeetingsByUserName(meetingByUserNameRequest.getUserName());
        meetingByUserNameResponse.setMeetingList(meetingList);
        return ResponseEntity.ok(meetingByUserNameResponse);
    }

    Optional<Meeting> getMeetingFromID(@PathVariable Long meetingID){
        return meetingRepository.findById(meetingID);
    }

    @PostMapping("/addMeeting")
    @ResponseBody
    ResponseEntity<AddMeetingResponse> addMeeting(@RequestBody MeetingRequest meetingRequest){
        AddMeetingResponse addMeetingResponse = new AddMeetingResponse();
        List<Meeting> meetings = meetingRepository.findAll();
        Meeting meeting = new Meeting(meetingRequest.getUserName(),meetingRequest.getStart(),meetingRequest.getEnd());
        if(meetings.isEmpty()){
            meetingRepository.save(meeting);
            addMeetingResponse.setResponse("Meeting Added");
            addMeetingResponse.setValid(true);
            return ResponseEntity.ok(addMeetingResponse);
        }
        meetings.remove(null);
        for(Meeting m : meetings){
            if(Meeting.clash(meeting,m)){
                addMeetingResponse.setResponse("This slot is not free choose another slot");
                addMeetingResponse.setValid(false);
                return ResponseEntity.ok(addMeetingResponse);
            }
        }
        meetingRepository.save(meeting);
        addMeetingResponse.setResponse("Meeting Added");
        addMeetingResponse.setValid(true);
        return ResponseEntity.ok(addMeetingResponse);
    }

    @PostMapping("/deleteMeeting")
    @ResponseBody
    ResponseEntity<DeleteMeetingResponse> deleteMeeting(@RequestBody DeleteMeetingRequest deleteMeetingRequest){
        DeleteMeetingResponse deleteMeetingResponse = new DeleteMeetingResponse();
        Optional<Meeting> meeting = meetingRepository.findById(deleteMeetingRequest.getMeetingID());
        if(meeting.isEmpty()){
            deleteMeetingResponse.setResponse("No meeting with the given ID " + deleteMeetingRequest.getMeetingID());
            deleteMeetingResponse.setValid(false);
            return ResponseEntity.ok(deleteMeetingResponse);
        }
        meetingRepository.deleteById(deleteMeetingRequest.getMeetingID());
        deleteMeetingResponse.setResponse("Meeting is deleted successfully");
        deleteMeetingResponse.setValid(true);
        return ResponseEntity.ok(deleteMeetingResponse);
    }
    @PostMapping("/deleteAllMeetings")
    @ResponseBody
    ResponseEntity<MeetingByUserNameResponse> deleteAllMeetingsByUserName(@RequestBody MeetingByUserNameRequest meetingByUserNameRequest){
        MeetingByUserNameResponse meetingByUserNameResponse = new MeetingByUserNameResponse();
        meetingRepository.deleteMeetingsByUserName(meetingByUserNameRequest.getUserName());
        return ResponseEntity.ok(meetingByUserNameResponse);
    }
}
