import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Todo } from '../models/todo.model';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/todos';

  // constructor(private http: HttpClient) { }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.apiUrl);
  }

  addTodo(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.apiUrl, todo);
  }

  updateTodo(todo: Todo): Observable<Todo> {
    return this.http.put<Todo>(`${this.apiUrl}/${todo.id}`, todo);
  }

  deleteTodo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // consommation des nouveaux endpoints ajoutés dans le backend
  getTodosByPriority(priority: string): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.apiUrl}/filter?priority=${priority}`);
  }

  searchTodos(query: string): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.apiUrl}/search?q=${query}`);
  }

  // filtrage par catégorie
  getTodosByCategory(category: string): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.apiUrl}/category?name=${category}`);
  }

}
