package ma.ensaf.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    private boolean completed = false;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW; // valeur par défaut

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum Category {
        WORK, STUDY, HOME, OTHER
    }

}
