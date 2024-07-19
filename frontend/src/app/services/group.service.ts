import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Group } from '../models/';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  createGroup(boardId: string, group: Group): Observable<Group> {
    return this.http.post<Group>(`${this.configService.baseApiUrl}/boards/${boardId}/groups`, group);
  }

  editGroup(groupId: string, group: Group): Observable<Group> {
    return this.http.put<Group>(`${this.configService.baseApiUrl}/groups/${groupId}`, group);
  }

  deleteGroup(groupId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}/groups/${groupId}`);
  }
}
