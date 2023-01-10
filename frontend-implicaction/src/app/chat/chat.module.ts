import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import { PageComponent } from './containers/page/page.component';
import { MessageComponent } from './components/message/message.component';
import { SelfMessageComponent } from './components/self-message/self-message.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    PageComponent,
    MessageComponent,
    SelfMessageComponent,
  ],
  imports: [
    FormsModule,
    CommonModule,
    ChatRoutingModule
  ]
})
export class ChatModule { }
