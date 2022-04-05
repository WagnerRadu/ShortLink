package com.demo.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ShortenLinkResponse {
    private List<LinkPair> pairs = new ArrayList<>();

    public void addPair(LinkPair linkPair) {
        pairs.add(linkPair);
    }
}
