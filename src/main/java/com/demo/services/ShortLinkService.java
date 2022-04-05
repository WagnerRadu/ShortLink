package com.demo.services;

import com.demo.DatabaseManager;
import com.demo.models.LinkPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkService {
    @Autowired
    private DatabaseManager dbManager;

    @Autowired
    private ShortLinkCodeService shortLinkCode;

    public LinkPair generatePairLink(String link) {
        int id;
        try {
            id = dbManager.getIdByLink(link);
            String shortLink = shortLinkCode.codeURL(id);
            return new LinkPair(link, "localhost:9003/" + shortLink);
        } catch (NullPointerException e) {
            id = dbManager.addNewLink(link);
            String shortLink = shortLinkCode.codeURL(id);
            return new LinkPair(link, "localhost:9003/" + shortLink);
        }
    }

    public String getLongLink(String shortLink) {
        int id = shortLinkCode.decodeURL(shortLink);
        return dbManager.getLinkById(id);

    }
}
