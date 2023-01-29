import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChatRoutingModule } from './chat-routing.module';
import { GroupsComponent } from './pages/groups/groups.component';
import { MessagesComponent } from './pages/messages/messages.component';
import { GroupsService } from './services/groups/groups.service';
import { MessagesService } from './services/messages/messages.service';
import { RxStompService } from './services/rx-stomp/rx-stomp.service';


@NgModule({
  declarations: [
    GroupsComponent,
    MessagesComponent
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    RouterModule
  ],
  providers: [
    GroupsService,
    RxStompService,
    MessagesService
  ]
})
export class ChatModule { }
