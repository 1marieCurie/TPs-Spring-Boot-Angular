package ma.ensaf.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.ensaf.todo.entity.Todo;
import ma.ensaf.todo.repository.TodoRepository;

@Service
@RequiredArgsConstructor

public class TodoService {

    private final TodoRepository repository;
    private final NotificationService notificationService;

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo create(Todo todo) {
        Todo saved = repository.save(todo);
        notificationService.sendTodoUpdate(saved); // notification en temps réel
        return saved;
    }

    public Todo update(Long id, Todo todo) {
        todo.setId(id);
        Todo updated = repository.save(todo);
        notificationService.sendTodoUpdate(updated);
        return updated;
    }

    public void delete(Long id) {
        repository.deleteById(id);
        notificationService.sendTodoUpdate("Todo supprimé: " + id);
    }
    public Todo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found: " + id));
    }

    // méthodes ajoutées pour enrichir le projet
    public List<Todo> findByPriority(Todo.Priority priority) {
        List<Todo> todos = repository.findByPriority(priority);
        notificationService.sendTodoUpdate("Recherche par priorité: " + priority + ", " + todos.size() + " résultats");
        return todos;
    }

    public List<Todo> search(String keyword) {
        List<Todo> todos = repository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        notificationService.sendTodoUpdate("Recherche par mot-clé: '" + keyword + "', " + todos.size() + " résultats");
        return todos;
    }

    // la méthode planifiée pour le scheduler
    @Scheduled(fixedRate = 60000) // toutes les 60 secondes
    public void checkTodosDue() {
        List<Todo> dueTodos = repository.findAll().stream()
                .filter(todo -> todo.getDueDate() != null && todo.getDueDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        dueTodos.forEach(todo -> notificationService.sendTodoUpdate(
                "Todo arrivé à échéance: " + todo.getTitle()
        ));
    }

}
