import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { AuthService } from 'src/app/shared/services/auth.service';
import { Group } from '../../models/group';
import { GroupsService } from '../../services/groups/groups.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {

  groups?: Group[];
  user: User;
  constructor(private groupsService: GroupsService, private authService: AuthService) {
    this.user = this.authService.getCurrentUser();
  }

  ngOnInit(): void {
    this.groupsService.findGroups(parseInt(this.user.id)).subscribe((res) => {
      this.groups = res;
    });


  }

  createGroup(): void {
    // this.groupsService.createGroup();
  }
}
