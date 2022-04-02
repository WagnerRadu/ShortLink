package com.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class DatabaseManager {
    DataSource dataSource;
    JdbcTemplate template;

    @Autowired
    public DatabaseManager(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(this.dataSource);
    }

    public String getLinkById(int id) {
        String query = "select url from links where id = ?";
        return template.queryForObject(query, String.class, id);
    }

    public int getIdByLink(String link) throws NullPointerException{
        String query = "select id from links where url = ?";
        int id;
        try {
            return template.queryForObject(query, Integer.class, link);
        } catch (DataAccessException e) {
            throw new NullPointerException();
        }
    }

    public int addNewLink(String link) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into links (url) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, link);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}