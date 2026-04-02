package ma.ensaf.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensaf.todo.entity.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByPriority(Todo.Priority priority);
    List<Todo> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
