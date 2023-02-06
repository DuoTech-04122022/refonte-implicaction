import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiEndpointsService } from 'src/app/core/services/api-endpoints.service';
import { Group } from '../../models/group';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  constructor(private http: HttpClient, private apiEndpointService: ApiEndpointsService) {
  }

  findGroups(user: number): Observable<Group[]> {
    return this.http.get<Group[]>(this.apiEndpointService.findGroups(user, {rows: 10})).pipe(map((res: any) => res.content as Group[]));
  }

  findGroup(id: string): Observable<Group>{
    return this.http.get<Group>(this.apiEndpointService.findGoup(id));
  }

  add(group: Group): Observable<any> {
    return this.http.post(this.apiEndpointService.addGroup(), group);
  }

  addUser(groupId: string, user: string): Observable<any>{
    return this.http.post(this.apiEndpointService.addUser(groupId), user);
  }
  
  removeUser(groupId: string, user: string): Observable<any> {
    return this.http.post(this.apiEndpointService.removeUser(groupId), user);
  }

  createGroup(payload: any): Observable<any> {
    return this.http.post(this.apiEndpointService.addGroup(), payload);
  }
}
