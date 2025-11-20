package com.example.todoapp.service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Create Todo
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // Get All Todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Get Todo by ID
    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    // Update Todo
    public Todo updateTodo(String id, Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(updatedTodo.getTitle());
                    todo.setDescription(updatedTodo.getDescription());
                    todo.setCompleted(updatedTodo.isCompleted());
                    return todoRepository.save(todo);
                })
                .orElse(null);
    }

    // Delete Todo
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
}
