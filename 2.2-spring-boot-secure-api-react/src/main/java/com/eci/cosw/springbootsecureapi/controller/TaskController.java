package com.eci.cosw.springbootsecureapi.controller;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "api" )
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    ObjectMapper mapperJson;

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        String tasks = null;
        try {
            tasks = Unirest.get("https://taskplanner11.azurewebsites.net/api/add-task?code=IRli1suo1CLj1VUGkfKp7UUZWiVuvlnrN1KM8Hcn78tNKPMUhk2Fqw==")
                    .asString().getBody();
        } catch (UnirestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task){
        try {
            Unirest.post("https://taskplanner11.azurewebsites.net/api/add-task?code=IRli1suo1CLj1VUGkfKp7UUZWiVuvlnrN1KM8Hcn78tNKPMUhk2Fqw==")
                        .header("Content-Type","application/json")
                        .body(mapperJson.writeValueAsString(task)).asString().getStatus();
        } catch (UnirestException | JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
