package com.demo;

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

    @PostMapping("/shortenLink")
    public ResponseEntity<?> codeLink(@RequestBody String link) {
        int id;
        try {
            id = dbManager.getIdByLink(link);
            String shortLink = shortLinkCode.codeURL(id);
            return new ResponseEntity<>("localhost:9003/" + shortLink, HttpStatus.OK);
        } catch (NullPointerException e) {
            id = dbManager.addNewLink(link);
            String shortLink = shortLinkCode.codeURL(id);
            return new ResponseEntity<>("localhost:9003/" + shortLink, HttpStatus.OK);
        }
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> getLongURL(@PathVariable String shortLink) {
        int id = shortLinkCode.decodeURL(shortLink);
        String link = dbManager.getLinkById(id);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("location", link);
        return new ResponseEntity<>(responseHeader, HttpStatus.PERMANENT_REDIRECT);
    }
}

