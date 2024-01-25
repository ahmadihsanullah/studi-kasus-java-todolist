package service;

import entity.Todolist;
import repository.TodolistRepository;

public class TodolistServiceImpl implements TodolistService{

    private TodolistRepository todoListRepository;

    public TodolistServiceImpl(TodolistRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }
    @Override
    public void showTodoList() {
        Todolist[] model = todoListRepository.getAll();

//        System.out.println("TODOLIST");
        for(var todo : model){
            System.out.println(todo.getId() + " : " + todo.getTodo());
        }
    }

    @Override
    public void addTodoList(String todo) {
        Todolist todolist = new Todolist(todo);
        todoListRepository.add(todolist);
        System.out.println("TODOLIST ADDED: " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
        boolean success = todoListRepository.remove(number);
        if (success){
            System.out.println("BERHASIL MENGHAPUS TODO" + number);
        }else {
            System.out.println("GAGAL MENGHAPUS TODO : " + number);
        }
    }
}
