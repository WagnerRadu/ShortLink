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
    public ResponseEntity<?> codeLink(@RequestBody String link) {
        int id;
        try {
            id = dbManager.getIdByLink(link);
            String shortLink = shortLinkCode.codeURL(id);
            ResponseEntity<?> response = new ResponseEntity<>(shortLink, HttpStatus.OK);
            System.out.println(id);
            System.out.println(shortLink);
            return response;
        } catch (NullPointerException e) {
            id = dbManager.addNewLink(link);
            String shortLink = shortLinkCode.codeURL(id);
            return new ResponseEntity<>("New entry will be created in DB with id " + id, HttpStatus.OK);
        }
    }

    @GetMapping("/getLink/{id}")
    public void getLongURL(@PathVariable String id) {
        System.out.println(dbManager.getLinkById(Integer.parseInt(id)));
    }
}
