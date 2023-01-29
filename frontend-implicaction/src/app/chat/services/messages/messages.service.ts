import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiEndpointsService } from 'src/app/core/services/api-endpoints.service';
import { Message } from '../../models/message';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http: HttpClient, private apiEndpointService: ApiEndpointsService) {
  }

  findMessages(groupId: string): Observable<Message[]> {
    return this.http.get<Message[]>(this.apiEndpointService.findMessages(groupId)).pipe(map((res: any) => res.content as Message[]));
  }
}
