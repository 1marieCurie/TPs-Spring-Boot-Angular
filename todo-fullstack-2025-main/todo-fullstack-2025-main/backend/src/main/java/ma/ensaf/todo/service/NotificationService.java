package ma.ensaf.todo.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate template;

    public NotificationService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendTodoUpdate(Object todo) {
        template.convertAndSend("/topic/todos", todo);
    }
}
