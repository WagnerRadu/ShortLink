package com.demo.models;

import java.util.ArrayList;
import java.util.List;

public class ShortenLinkRequest {
    private List<String> links;

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
