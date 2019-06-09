package com.settipalli.todo.service;

import com.settipalli.todo.model.Todo;
import com.settipalli.todo.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(long id) {
        return todoRepository.getOne(id);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void removeTodo(Todo todo) {
        todoRepository.delete(todo);
    }
}
