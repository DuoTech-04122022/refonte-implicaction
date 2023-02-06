import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GroupsComponent } from './pages/groups/groups.component';
import { MessagesComponent } from './pages/messages/messages.component';

const routes: Routes = [
  {
    path: '',
    component: GroupsComponent,
    children: [
      {
        path: ':id',
        component: MessagesComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule { }
