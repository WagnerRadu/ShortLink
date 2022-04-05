package com.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LinkPair {
    private String longLink;
    private String shortLink;
}
