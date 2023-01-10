import { Component, OnDestroy, OnInit } from '@angular/core';
import { Message } from '../../models/message';
import { Message as WebsocketMessage } from 'stompjs';
import { MessagesService } from '../../services/messages.service';
import { RxStompService } from 'src/app/shared/services/rx-stomp.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss'],
})
export class PageComponent implements OnInit, OnDestroy {
  messages?: Message[];
  private stompClient = null;
  currentMessage: string = '';
  greetings: string[] = [];
  private topicSubscription: Subscription;
  disabled = true;

  constructor(private messagesService: MessagesService, private rxStompService: RxStompService) {}

  ngOnInit(): void {
    // this.connect();
    // this.messagesService.find().subscribe((res) => {
    //   this.messages = res;
    // });
    this.topicSubscription = this.rxStompService.watch('/topic/chat').subscribe((message: WebsocketMessage) => {
      console.log(message);
    });
  }

  ngOnDestroy(): void {
    this.topicSubscription.unsubscribe();
  }

  // sendMessage(): void {
  //   const message: Message = {
  //     content: this.currentMessage,
  //     sender: 'me',
  //     sendedAt: new Date(Date.now()),
  //     type: 'text',
  //   };
  //   this.messagesService.add(message);
  //   this.currentMessage = '';
  // }

  onSendMessage() {
    const message = `Message generated at ${new Date()}`;
    this.rxStompService.publish({ destination: '/topic/chat', body: message });
  }
}
