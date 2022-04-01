package com.demo;

import com.demo.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortLinkController {

    @Autowired
    ShortLinkCode shortLinkCode;

    @Autowired
    private DatabaseManager dbManager;

    public ShortLinkController(){
        System.out.println("Controller instantiated");
    }

    @PostMapping("/code")
    public ResponseEntity<?> codeLink(@RequestBody URL link) {
        String shortLink = shortLinkCode.codeURL(1535);
        ResponseEntity<?> response = new ResponseEntity<>(shortLink, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getLink/{id}")
    public void getLongURL(@PathVariable String id) {
        System.out.println(dbManager.getLinkById(Integer.parseInt(id)));
    }
}
