package com.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

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
}
