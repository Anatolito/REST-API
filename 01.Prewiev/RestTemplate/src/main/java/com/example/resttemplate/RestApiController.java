package com.example.resttemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * (Rest Controller – REST API)
 * GET – возвращает данные домена в форме JSON.
 * POST- возвращает данные домена, завернутые в ResponseEntity вместе с заголовками.
 */

@RestController
@RequestMapping(path = "/RestApi",
        produces = "application/json")
@CrossOrigin(origins = "*")

// Class
public class RestApiController {

    @GetMapping("/getData")
    public UserData get() {
        UserData userData = new UserData();
        userData.setId("1");
        userData.setUserName("darshanGPawar@geek");
        userData.setData("Data send by Rest-API");
        return userData;
    }

    // Annotation
    @PostMapping
    public ResponseEntity<UserData> post(@RequestBody UserData userData) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userData, headers, HttpStatus.CREATED);
    }
}