import {Component, Input, OnInit} from '@angular/core';
import { Message } from '../models/message';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit {

  @Input('message') message: Message;

  constructor() { }

  ngOnInit(): void {
  }

}
