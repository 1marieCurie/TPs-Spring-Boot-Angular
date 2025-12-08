package ma.ensaf.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensaf.todo.entity.Todo;
import ma.ensaf.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @GetMapping
    public List<Todo> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@Valid @RequestBody Todo todo) {
        return service.create(todo);
    }

    @PutMapping("/{id}")
    public Todo upddate(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        return service.update(id, todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //Nouveaux endpoints ajoutés
    @GetMapping("/filter")
    public List<Todo> filterByPriority(@RequestParam Todo.Priority priority) {
        return service.findByPriority(priority);
    }

    @GetMapping("/search")
    public List<Todo> searchTodos(@RequestParam String q) {
        return service.search(q);
    }

}
