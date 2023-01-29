import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { ChatRoutingModule } from './chat-routing.module';
import { GroupComponent } from './components/group/group.component';
import { GroupsComponent } from './pages/groups/groups.component';
import { MessagesComponent } from './pages/messages/messages.component';
import { GroupsService } from './services/groups/groups.service';
import { MessagesService } from './services/messages/messages.service';
import { RxStompService } from './services/rx-stomp/rx-stomp.service';


@NgModule({
  declarations: [
    GroupsComponent,
    MessagesComponent,
    GroupComponent,
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    RouterModule,
    FormsModule
  ],
  providers: [
    GroupsService,
    RxStompService,
    MessagesService
  ]
})
export class ChatModule { }
