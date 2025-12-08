import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private stompClient!: Stomp.Client;
  public todoUpdates: Subject<any> = new Subject<any>();

  connect() {
    const socket = new SockJS('http://localhost:8080/ws-todos');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, frame => {
      this.stompClient.subscribe('/topic/todos', message => {
        this.todoUpdates.next(JSON.parse(message.body));
      });
    });
  }
}
