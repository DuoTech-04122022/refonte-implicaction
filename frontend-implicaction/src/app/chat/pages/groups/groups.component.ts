import { Component, OnInit } from '@angular/core';
import { Group } from '../../models/group';
import { GroupsService } from '../../services/groups/groups.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {

  groups?: Group[];
  constructor(private groupsService: GroupsService) { }

  ngOnInit(): void {
    this.groupsService.findGroups(55).subscribe((res) => {
      this.groups = res;
    });
  }

}
