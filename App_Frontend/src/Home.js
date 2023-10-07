
import React, { useState } from 'react';
import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom'

const baseUrl = "http://localhost:8080/";

function Home() {
    const navigation = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [timerText, setTimerText] = useState('');
    const duration = 3000;
    require('react-dom');
    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const displayTimerText = (text)  => {
        setTimerText(text);
        const timer = setTimeout(() => {
            setTimerText('');
        }, duration);
        return () => clearTimeout(timer);
    };

    const handleLogin = (e) => {
        e.preventDefault();
        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            body: JSON.stringify({ userName: username, passWord: password })
        };
        fetch(baseUrl + "loginUser", request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network error");
                }
                return response.json();
            })
            .then(data => {
                if (data.valid) {
                    navigation('/LoginPage/' + username);
                }
                else{
                    displayTimerText(data.response);
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
            });
    };

    const handleRegister = (e) => {
        e.preventDefault();
        const request = {
            method: "POST",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            body: JSON.stringify({ userName: username, passWord: password })
        };
        fetch(baseUrl + "addUser", request)
            .then(response => {
                if (!response.ok) {
                    throw new ErrorEvent("Network error");
                }
                return response.json();
            })
            .then(data => {
                displayTimerText(data.response);
            })
            .catch(error => {
                console.error("Fetch error:", error);
            });
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            <form>
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={handleUsernameChange}
                    className="inputField"
                />
                <br />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={handlePasswordChange}
                />
                <br/>
                <button type="submit" onClick={handleLogin}>Login</button>
                <button type="submit" onClick={handleRegister}>Register</button>
                <br/>
                {timerText}
            </form>
        </div>
    );
}

export default Home;
