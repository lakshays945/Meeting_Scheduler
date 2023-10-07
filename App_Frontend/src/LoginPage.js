import React, { useEffect, useState } from 'react';
import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom'
import { useParams } from 'react-router-dom';
import './LoginPage.css'

const baseUrl = "http://localhost:8080/";



function AddMeetingForm({ username, state, toggleState }) {
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const [timerText, setTimerText] = useState('');
    const duration = 2000;
    const startTimeChange = (e) => {
        setStartTime(e.target.value);
    };
    const endTimeChange = (e) => {
        setEndTime(e.target.value);
    };
    const displayTimerText = (text)  => {
        setTimerText(text);
        const timer = setTimeout(() => {
            setTimerText('');
        }, duration);
        return () => clearTimeout(timer);
    };
    const submitMeeting = (e) => {
        e.preventDefault();
        if (startTime == '' || endTime == '') return;
        const st = 60 * (10 * startTime[0] + 1 * startTime[1]) + 10 * startTime[3] + 1 * startTime[4];
        const et = 60 * (10 * endTime[0] + 1 * endTime[1]) + 10 * endTime[3] + 1 * endTime[4];
        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            body: JSON.stringify({ userName: username, start: st, end: et })
        };
        fetch(baseUrl + "addMeeting", request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network Error");
                }
                return response.json();
            })
            .then(data => {
                if(data.valid){
                    toggleState();
                }
                else{
                    displayTimerText(data.response);
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                toggleState();
            });
    };
    if (state === false) return (<></>);
    return (
        <div className='div-container'>
            <form className='form-container'>
                Choose Start Time <input type='time' className='time-input' required onChange={startTimeChange} /> <br />
                Choose End Time <input type='time' className='time-input' required onChange={endTimeChange} /> <br />
                <div className='form-button'>
                    <button type='button' onClick={submitMeeting}> Submit </button>
                    &nbsp; &nbsp;
                    <button type='button' onClick={toggleState}> Back </button>
                </div>
            </form>
            {timerText}
        </div>
    );
}

function getTime(time) {
    var ans = '' + parseInt(time / 600) + parseInt((time % 600) / 60) + ':' + parseInt((time % 60) / 10) + parseInt((time % 10));
    if (time >= 720) ans += ':pm';
    else ans += ':am';
    return ans;
}

function Meeting({ meeting, setLoading }) {
    var start = getTime(meeting.startTime);
    var end = getTime(meeting.endTime);
    const [ID, setID] = useState(0);
    const deteleMeeting = (e) => {
        e.preventDefault();
        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            body: JSON.stringify({ meetingID: meeting.id })
        };
        fetch(baseUrl + 'deleteMeeting', request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network Error");
                }
                return response.json();
            })
            .then(data => {
                setLoading(true);
            })
            .catch(error => {
                setLoading(true);
            });
    };
    return (
        <>
            <li className='meeting'>
                Start Time = {start} &nbsp; &nbsp; End Time = {end}
            </li>
            <button type='button' onClick={deteleMeeting}> Delete </button> <br /> <br />
        </>
    );
}



function MeetingsList({ username, state, toggleState }) {
    const navigation = useNavigate();
    const [meetingsList, setMeetingList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    if (state === true) return (<></>);
    if (loading) {
        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            body: JSON.stringify({ userName: username })
        };
        fetch(baseUrl + "getMeetings", request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network Error");
                }
                return response.json();
            })
            .then(data => {
                setMeetingList(data.meetingList);
                setLoading(false);
            })
            .catch(error => {
                console.error("Fetch error:", error);
                setError(error);
                setLoading(false);
            })
    }
    const deleteUser = (e) => {
        var meetingsDeleted = false;
        var userDeleted = false;
        e.preventDefault();
        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            body: JSON.stringify({ userName: username })
        };
        fetch(baseUrl + "deleteAllMeetings", request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network Error");
                }
                return response.json();
            })
            .then(data => {
                userDeleted = true;
                if (meetingsDeleted) {
                    userDeleted = false;
                    navigation(-1);
                }
                console.log(data);
            })
            .catch(error => {
                userDeleted = true;
                if (meetingsDeleted) {
                    userDeleted = false;
                    navigation(-1);
                }
                console.error("Fetch Error :", error);
            });
        fetch(baseUrl + "deleteUser", request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network Error");
                }
                return response.json();
            })
            .then(data => {
                meetingsDeleted = true;
                if (userDeleted) { 
                    meetingsDeleted = false;
                    navigation(-1); 
                }
                console.log(data);
            })
            .catch(error => {
                meetingsDeleted = true;
                if (userDeleted) { 
                    meetingsDeleted = false;
                    navigation(-1); 
                }
                console.error("Fetch Error :", error);
            });
    }
    const addMeeting = (e) => {
        setLoading(true);
        toggleState();
    };
    if (meetingsList != 0) {
        return (
            <div>
                <h2>&nbsp;&nbsp;List of your Meetings</h2>
                {loading ? (
                    <p>Loading...</p>
                ) : error ? (
                    <p>Error: {error.message}</p>
                ) : (
                    <>
                        <ol>
                            {meetingsList.map((meeting, index) => (
                                <Meeting key={index} meeting={meeting} setLoading={setLoading} />
                            ))}
                        </ol>
                        <div>
                            <button type='button' onClick={addMeeting}> Add Meeting </button><br/>
                            <button type='button' onClick={deleteUser}>Delete User</button>
                        </div>
                    </>
                )}
            </div>
        );
    }
    else {
        return (
            <div>
                <button type='button' onClick={addMeeting}> Add Meeting </button> <br />
                <button type='button' onClick={deleteUser}>Delete User</button>
            </div>
        );
    }
}

const LoginPage = () => {
    var { username } = useParams();
    const [state, setState] = useState(false);
    const toggleState = (e) => {
        setState(!state);
    };
    return (
        <>
            <div className='welcome'>
                Welcome :) {username} <br />
            </div>
            <div>
                {MeetingsList({ username, state, toggleState })}
                {AddMeetingForm({ username, state, toggleState })}
            </div>
        </>
    );
}

export default LoginPage