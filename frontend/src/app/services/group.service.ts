import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Group } from '../models/group.model';
import { ConfigService } from './config.service';
import { BackendRoutes } from '../app-main-rules/routes.enum';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  createGroupUser(boardId: string, group: Group): Observable<Group> {
    return this.http.post<Group>(`${this.configService.baseApiUrl}${BackendRoutes.NEW_GROUP.replace('{boardId}', boardId)}`, group);
  }

  updateGroupUser(groupId: string, group: Group): Observable<Group> {
    return this.http.put<Group>(`${this.configService.baseApiUrl}${BackendRoutes.GROUP.replace('{groupId}', groupId)}`, group);
  }

  deleteGroupUser(groupId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}${BackendRoutes.GROUP.replace('{groupId}', groupId)}`);
  }
}
