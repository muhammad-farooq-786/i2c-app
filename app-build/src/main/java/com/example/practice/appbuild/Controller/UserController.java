package com.example.practice.appbuild.Controller;

import com.example.practice.appbuild.DAO.UserRepo;
import com.example.practice.appbuild.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/i2c")
public class UserController {

    @Autowired
    UserRepo userRepo;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
     List<User> users = userRepo.findAll();
     if(users.isEmpty()){
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
     return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User reqUser){
            User user = userRepo.save(reqUser);
            return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @PostMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id,@RequestBody User reqUser){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            User updated = user.get();
            updated.setName(reqUser.getName());
            updated.setEmail(reqUser.getEmail());
            updated.setCity(reqUser.getCity());
            User saved = userRepo.save(updated);
            return new ResponseEntity<>(saved,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){
        userRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
