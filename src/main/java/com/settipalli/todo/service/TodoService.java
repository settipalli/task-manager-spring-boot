package com.settipalli.todo.service;


import com.settipalli.todo.model.Todo;

import java.util.List;

public interface TodoService {
    public List<Todo> getAllTodo();
    public Todo getTodoById(long id);
    public Todo saveTodo(Todo todo);
    public void removeTodo(Todo todo);
}
