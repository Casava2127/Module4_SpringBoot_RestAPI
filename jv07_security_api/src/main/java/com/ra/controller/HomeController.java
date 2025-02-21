package com.ra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HomeController {
    @GetMapping("/home")
    public ResponseEntity<?> home(){
        return new ResponseEntity<>("Xin chao den voi api", HttpStatus.OK);
    }
    @GetMapping("/cart")
    public ResponseEntity<?> cart(){
        return new ResponseEntity<>("Xin chao den trang gio ahng", HttpStatus.OK);
    }

}
