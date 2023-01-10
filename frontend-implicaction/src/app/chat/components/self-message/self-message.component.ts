import { Component, Input, OnInit } from '@angular/core';
import { Message } from '../../models/message';

@Component({
  selector: 'app-self-message',
  templateUrl: './self-message.component.html',
  styleUrls: ['./self-message.component.scss']
})
export class SelfMessageComponent implements OnInit {

  @Input('message') message: Message;   

  constructor() { }

  ngOnInit(): void {
  }

}
