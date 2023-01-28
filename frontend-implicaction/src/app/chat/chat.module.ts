import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChatRoutingModule } from './chat-routing.module';
import { GroupsComponent } from './pages/groups/groups.component';
import { MessagesComponent } from './pages/messages/messages.component';


@NgModule({
  declarations: [
    GroupsComponent,
    MessagesComponent
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    RouterModule
  ]
})
export class ChatModule { }
