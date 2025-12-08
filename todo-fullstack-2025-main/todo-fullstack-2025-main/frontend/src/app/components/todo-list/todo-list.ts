import { Component, OnInit } from '@angular/core';
import { Todo } from '../../models/todo.model';
import { TodoService } from '../../services/todo.service';
import { WebsocketService } from '../../services/websocket.service';


@Component({
  selector: 'app-todo-list',
  imports: [],
  templateUrl: './todo-list.html',
  styleUrls: './todo-list.scss',
})

export class TodoList implements OnInit {
  todos: Todo[] = [];
  newTitle = '';

  constructor(private todoService: TodoService,
              private websocketService: WebsocketService) {}

  ngOnInit(): void {
    this.loadTodos();

    this.websocketService.connect();
    this.websocketService.todoUpdates.subscribe(update => {
      console.log("Notification reçue:", update);
      this.loadTodos();
    });
  }

  loadTodos() {
    this.todoService.getTodos().subscribe(data => this.todos = data);
  }

  addTodo() {
    if (this.newTitle.trim()) {
      this.todoService.addTodo({title: this.newTitle, completed: false})
        .subscribe(todo => {
          this.todos.push(todo);
          this.newTitle = '';
        });
    }
  }

  toggle(todo: Todo) {
    todo.completed = !todo.completed;
    this.todoService.updateTodo(todo).subscribe();
  }

  delete(todo: Todo) {
    this.todoService.deleteTodo(todo.id!).subscribe(() => {
      this.todos = this.todos.filter(t => t.id !== todo.id);
    });
  }

  filterPriority(p: string) {
    if (p === 'ALL') {
      this.loadTodos();
    } else {
      this.todoService.getTodosByPriority(p).subscribe(res => this.todos = res);
    }
  }

  search(query: string) {
    this.todoService.searchTodos(query).subscribe(res => this.todos = res);
  }
}
