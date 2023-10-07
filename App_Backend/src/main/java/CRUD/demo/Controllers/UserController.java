package CRUD.demo.Controllers;

import CRUD.demo.Models.Meeting;
import CRUD.demo.Models.User;
import CRUD.demo.Repositories.UserRepository;
import CRUD.demo.RequestModels.AddUserRequest;
import CRUD.demo.RequestModels.DeleteUserRequest;
import CRUD.demo.RequestModels.LoginUserRequest;
import CRUD.demo.ResponseModels.AddUserResponse;
import CRUD.demo.ResponseModels.DeleteUserResponse;
import CRUD.demo.ResponseModels.LoginUserResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3001","http://localhost:3000"} )
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUserName(String userName){
        Optional<User> user = Optional.empty();
        try{
            user = userRepository.findById(userName);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @PostMapping ("/loginUser")
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest){
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        Optional<User> user = getUserByUserName(loginUserRequest.getUserName());
        if(user.isEmpty()){
            loginUserResponse.setResponse("No User with the given name");
            loginUserResponse.setValid(false);
            return ResponseEntity.ok(loginUserResponse);
        }
        if(!user.get().getPassWord().equals(loginUserRequest.getPassWord())){
            loginUserResponse.setResponse("Wrong Password");
            loginUserResponse.setValid(false);
            return ResponseEntity.ok(loginUserResponse);
        }
        loginUserResponse.setResponse("User found");
        loginUserResponse.setValid(true);
        return ResponseEntity.ok(loginUserResponse);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest addUserRequest){
        String userName = addUserRequest.getUserName();
        String passWord = addUserRequest.getPassWord();
        AddUserResponse addUserResponse = new AddUserResponse();
        Optional<User> user = getUserByUserName(userName);
        if(user != null && user.isPresent()){
            addUserResponse.setResponse("Username already exists");
            addUserResponse.setAdded(false);
            return ResponseEntity.ok(addUserResponse);
        }
        User newUser = new User(userName,passWord);
        userRepository.save(newUser);
        addUserResponse.setResponse("User added successfully");
        addUserResponse.setAdded(true);
        return ResponseEntity.ok(addUserResponse);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public ResponseEntity<DeleteUserResponse> deleteUser(@RequestBody DeleteUserRequest deleteUserRequest){
        DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
        Optional<User> user = getUserByUserName(deleteUserRequest.getUserName());
        if(user.isEmpty()){
            deleteUserResponse.setResponse("No user with given username");
            deleteUserResponse.setValid(false);
            return ResponseEntity.ok(deleteUserResponse);
        }
        userRepository.deleteById(deleteUserRequest.getUserName());
        deleteUserResponse.setResponse("User deleted successfully");
        deleteUserResponse.setValid(true);
        return ResponseEntity.ok(deleteUserResponse);
    }

}
