import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Message } from '../models/message';

@Injectable({
  providedIn: 'root',
})
export class MessagesService {
  websocket: WebSocket;
  messages: BehaviorSubject<Message[]>;
  constructor() {
    this.messages = new BehaviorSubject<Message[]>([]);
  }

  connect() {
    console.log('Initialize WebSocket Connection');
    this.websocket = new WebSocket('ws://localhost:8080/ws');
    
    this.websocket.onopen = (e) => {
      console.log(e);
    }

    this.websocket.onerror = (e) => {
      console.log(e.target);
    }

    this.websocket.onmessage = (e) => {
      console.log(e);
      const msg = JSON.parse(e.data);
      console.log(msg);
      this.messages.next([...this.messages.getValue(), msg]);
    }

    this.websocket.onclose = (e) => {
      console.log(e);
    }
  }

  find(): Observable<Message[]> {
    // const messages = [
    //   {
    //     sender: 'Test',
    //     content: 'COUCOU',
    //     type: 'text',
    //     sendedAt: new Date(Date.now())
    //   },
    //   {
    //     sender: 'Test',
    //     content: 'COUCOU',
    //     type: 'text',
    //     sendedAt: new Date(Date.now())
    //   },
    //   {
    //     sender: 'me',
    //     content: 'COUCOU',
    //     type: 'text',
    //     sendedAt: new Date(Date.now())
    //   },
    //   {
    //     sender: 'Test',
    //     content: 'COUCOU',
    //     type: 'text',
    //     sendedAt: new Date(Date.now())
    //   },
    //   {
    //     sender: 'Test',
    //     content: 'COUCOU',
    //     type: 'text',
    //     sendedAt: new Date(Date.now())
    //   },
    //   {
    //     sender: 'Test',
    //     content: 'COUCOU',
    //     type: 'text',
    //     sendedAt: new Date(Date.now())
    //   },
    // ]
    // this.messages.next(messages);

    
    return this.messages;
  }

  add(message: Message): Observable<Message[]> {
    this.messages.next([...this.messages.getValue(), message]);
    console.log(this.websocket);
    this.websocket.send(JSON.stringify(message));
    return this.messages;
  }

  closeWebSocketConnection() {
    this.websocket.close();
    console.log('Disconnected');
  }

}
