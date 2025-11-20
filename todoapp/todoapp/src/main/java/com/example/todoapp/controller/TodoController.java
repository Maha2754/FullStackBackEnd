package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // allow frontend
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Create Todo
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    // Get All Todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    // Get Todo by ID
    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    // Test
    @GetMapping("/test")
    public String testApi() {
        return "Todo Controller Working!";
    }

    // Update Todo
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
        return todoService.updateTodo(id, updatedTodo);
    }

    // Delete Todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }
}
