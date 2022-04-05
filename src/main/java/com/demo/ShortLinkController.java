package com.demo;

import com.demo.models.LinkPair;
import com.demo.models.ShortenLinkRequest;
import com.demo.models.ShortenLinkResponse;
import com.demo.services.ShortLinkCodeService;
import com.demo.services.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    @PostMapping("/shortenLink")
    public ResponseEntity<ShortenLinkResponse> codeLink(@RequestBody ShortenLinkRequest link) {
        ShortenLinkResponse shortenLinkResponse = new ShortenLinkResponse();
        for (String linkLink : link.getLinks()) {
            shortenLinkResponse.addPair(shortLinkService.generatePairLink(linkLink));
        }
        return new ResponseEntity<>(shortenLinkResponse, HttpStatus.OK);
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> getLongURL(@PathVariable String shortLink) {
        String link = shortLinkService.getLongLink(shortLink);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("location", link);
        return new ResponseEntity<>(responseHeader, HttpStatus.PERMANENT_REDIRECT);
    }

}

