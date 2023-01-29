import { Component, Input, OnInit } from '@angular/core';
import { TitleStrategy } from '@angular/router';
import { Group } from '../../models/group';

@Component({
  selector: 'group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements OnInit {

  constructor() { }

  @Input()
  group!: Group;

  ngOnInit(): void {
    if(!this.group?.imageUrl) {
      this.group.imageUrl = 'https://i.pravatar.cc/150?img=3'
    }
  }

}
