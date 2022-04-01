package com.demo;

import org.springframework.stereotype.Component;

@Component //Sau @Service?
public class ShortLinkCode {

    public ShortLinkCode() {
        System.out.println("Component instatiated");
    }

    public String codeURL(int id) {
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder shortLink = new StringBuilder();
        while(id > 0) {
            shortLink.append(map[id % 62]);
            id = id/62;
        }
        return shortLink.toString();
    }

    public int decodeURL(String shortLink) {
        int id = 0;
        for (int i = shortLink.length() - 1; i >= 0; i--) {
            if ('a' <= shortLink.charAt(i) && shortLink.charAt(i) <= 'z')
                id = id * 62 + shortLink.charAt(i) - 'a';
            if ('A' <= shortLink.charAt(i) && shortLink.charAt(i) <= 'Z')
                id = id * 62 + shortLink.charAt(i) - 'A' + 26;
            if ('0' <= shortLink.charAt(i) && shortLink.charAt(i) <= '9')
                id = id * 62 + shortLink.charAt(i) - '0' + 52;
        }
        return id;
    }
}
