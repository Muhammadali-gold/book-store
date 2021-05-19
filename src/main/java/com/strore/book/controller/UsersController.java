package com.strore.book.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strore.book.dao.Users;
import com.strore.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(path = "users")
public class UsersController {

    @Autowired
    UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) throws JsonProcessingException {
        Users users = userService.findById(id);
        return getStringResponseEntity(Objects.nonNull(users), mapper.writeValueAsString(users),"not found user");
    }

    static ResponseEntity<String> getStringResponseEntity(boolean success, String content,String errorMessage) {
        HttpHeaders headers = getHttpHeaders();
        if (success) {
            return new ResponseEntity<>(content, headers, HttpStatus.OK);

        }
        return new ResponseEntity<>(errorMessage, headers, HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "")
    public ResponseEntity<String> getUsers() throws JsonProcessingException {


        List<Users> users = userService.findAll();

        return getStringResponseEntity(Objects.nonNull(users) && users.isEmpty(), mapper.writeValueAsString(users),"not found user");

    }

    @PostMapping(path = "")
    public ResponseEntity<String> addUser(@RequestBody Users users){
        HttpHeaders headers = getHttpHeaders();

        try {
            userService.createUsers(users);
            return new ResponseEntity<>("ok", headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("has error", headers, HttpStatus.BAD_REQUEST);
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        return headers;
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        HttpHeaders headers = getHttpHeaders();

        Users users = userService.findById(id);

        if (Objects.nonNull(users)) {
            userService.deleteUser(id);
            return new ResponseEntity<>("deleted", headers, HttpStatus.OK);

        }
        return new ResponseEntity<>("\"not found", headers, HttpStatus.BAD_REQUEST);

    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<String> changeUser(@PathVariable Long id,@RequestBody Users users){
        HttpHeaders headers = getHttpHeaders();

        try {
            Users users1 = userService.findById(id);
            if (Objects.isNull(users1)){
                return new ResponseEntity<>("user not found", headers, HttpStatus.BAD_REQUEST);

            }
            users.setId(id);
            users.setCreatedAtV2(users1.getCreatedAt());
            userService.createUsers(users);
            return new ResponseEntity<>("success", headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("internal server error", headers, HttpStatus.BAD_REQUEST);
        }
    }


}
