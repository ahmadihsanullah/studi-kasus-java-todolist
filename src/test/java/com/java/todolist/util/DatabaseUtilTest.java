package com.java.todolist.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import util.DatabaseUtil;

public class DatabaseUtilTest {

    @Test
    void testConnection() throws SQLException{
        HikariDataSource hikariDataSource = DatabaseUtil.getHikariDataSource();

        Connection connection = hikariDataSource.getConnection();

        hikariDataSource.close();
    }
    
}
