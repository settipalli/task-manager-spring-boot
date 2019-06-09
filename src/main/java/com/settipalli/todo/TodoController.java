package com.settipalli.todo;

import com.settipalli.todo.model.Response;
import com.settipalli.todo.model.Todo;
import com.settipalli.todo.service.TodoService;
import com.settipalli.todo.exception.TodoException;
import com.settipalli.todo.util.PayloadValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private static Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getAllTodo() {
        logger.info("Returning all Todos");
        return new ResponseEntity<List<Todo>>(todoService.getAllTodo(), HttpStatus.OK);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id) throws TodoException {
        logger.info("Todo id to return " + id);
        Todo todo = todoService.getTodoById(id);
        if (todo == null || todo.getId() <= 0) {
            throw new TodoException("Todo does not exit.");
        }
        return new ResponseEntity<Todo>(todoService.getTodoById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeTodoById(@PathVariable("id") long id) throws TodoException {
        logger.info("Todo id is remove + " + id);
        Todo todo = todoService.getTodoById(id);
        if (todo == null || todo.getId() <= 0) {
            throw new TodoException("Todo does not exit.");
        }
        todoService.removeTodo(todo);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Todo has been deleted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Todo> saveTodo(Todo payload) throws TodoException {
        logger.info("Payload to save " + payload);
        if (!PayloadValidator.validateCreatePaylod(payload)) {
            throw new TodoException("Malformed payload - id not defined.");
        }
        return new ResponseEntity<Todo>(todoService.saveTodo(payload), HttpStatus.OK);
    }

    @RequestMapping(value = "/todo", method = RequestMethod.PATCH)
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo payload) throws TodoException {
        logger.info("Payload to update " + payload);
        Todo todo = todoService.getTodoById(payload.getId());
        if (todo == null || todo.getId() <= 0) {
            throw new TodoException("Invalid todo");
        }
        return new ResponseEntity<Todo>(todoService.saveTodo(payload), HttpStatus.OK);
    }
}
