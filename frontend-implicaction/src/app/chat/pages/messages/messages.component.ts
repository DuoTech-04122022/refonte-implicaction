import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Group } from '../../models/group';
import { Message } from '../../models/message';
import { GroupsService } from '../../services/groups/groups.service';
import { MessagesService } from '../../services/messages/messages.service';
import { RxStompService } from '../../services/rx-stomp/rx-stomp.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {

  title = 'chat';
  user = 'sylvain';
  currentGroup!: string;
  group!: Group;
  currentMessage: string = '';
  receivedMessages: Message[] = [];
  constructor(
    private rxStompService: RxStompService,
    private route: ActivatedRoute,
    private messagesService: MessagesService,
    private groupsService: GroupsService
    ) {
  }

  async ngOnInit() {

    this.route.params.subscribe((res: any) => {
      this.currentGroup = res.id;
      this.receivedMessages = [];

      this.messagesService.findMessages(this.currentGroup).subscribe((res: any) => {
        console.log(this.receivedMessages);
        this.receivedMessages = [...this.receivedMessages, ...res];
      });

      this.groupsService.findGroup(this.currentGroup).subscribe((res: any) => {
        this.group = res;
        this.group.imageUrl = 'https://i.pravatar.cc/150?img=3'
      });

    });
    
    this.rxStompService.watch('/topic/chat/' + this.currentGroup).subscribe((m: any) => {

      const res = JSON.parse(m.body);

      const message: Message = res.body;
      console.log(message.sendedAt);
      this.receivedMessages.push(message);
    });
  }

  async sendMessage() {

    const message: Message = {
      content: this.currentMessage,
      sender: this.user,
      type: 'text',
      groupId: this.group.id,
    };
    this.messagesService.sendMessages(message);
    // this.rxStompService.publish({ destination: '/app/send/' + this.currentGroup , body: JSON.stringify(message) });
    this.currentMessage = '';
  }

}
