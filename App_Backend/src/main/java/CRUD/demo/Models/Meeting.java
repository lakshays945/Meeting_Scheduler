package CRUD.demo.Models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity(name = "MEETING")
@JsonSerialize
public class Meeting {

    public static boolean clash(Meeting m1, Meeting m2){
        if(m1.getEndTime() < m2.getStartTime() || m2.getEndTime() < m1.getStartTime()){
            return false;
        }
        return true;
    }
    @Column(name = "USERNAME")
    private String userName;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getID() {
        return ID;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    @Column(name = "START")
    private Long startTime;

    @Column(name = "END")
    private Long endTime;
    public Meeting(){}
    public Meeting(String userName, Long startTime, Long endTime){
        this.userName = userName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
