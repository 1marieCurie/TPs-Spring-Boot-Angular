package ma.ensaf.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


// pour ne pas exposer directement l’Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private boolean completed;
    private String priority;
    private String category;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
}
