package com.java.todolist.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import entity.Todolist;
import repository.TodolistRepository;
import util.DatabaseUtil;

public class TodolistRepositoryImpl {
    private TodolistRepository todolistRepository;
    private HikariDataSource dataSource;

    @BeforeEach
    void setUp(){
        dataSource = DatabaseUtil.getHikariDataSource();
        todolistRepository = new repository.TodolistRepositoryImpl(dataSource);
    }

    @Test 
    void testAdd(){
        Todolist todolist = new Todolist();
        todolist.setTodo("Joko");

        todolistRepository.add(todolist);
    }

    @Test
    void testDelete(){
        System.out.println(todolistRepository.remove(1));
        System.out.println(todolistRepository.remove(2));
        System.out.println(todolistRepository.remove(3));
        System.out.println(todolistRepository.remove(4));
    }

    @Test
    void testFindAll(){
        todolistRepository.add(new Todolist("hanif"));
        todolistRepository.add(new Todolist("ihsan"));
        todolistRepository.add(new Todolist("aji"));

        Todolist[] todolists = todolistRepository.getAll();
        for(var todo: todolists){
            System.out.println(todo.getId() + " : " + todo.getTodo());
        }
    }

    @AfterEach
    void tearDown(){
        dataSource.close();
    }
}
