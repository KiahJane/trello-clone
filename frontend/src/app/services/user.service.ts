import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { ConfigService } from './config.service';
import { BackendRoutes } from '../app-main-rules/routes.enum';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient, private configService: ConfigService) {}
  
  getMyInformation(): Observable<User> {
    return this.http.get<User>(`${this.configService.baseApiUrl}${BackendRoutes.USER_INFO}`, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_USERS}`);
  }

  getUserById(userId: string): Observable<User> {
    return this.http.get<User>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_USERS}/${userId}`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_USERS}`, user);
  }

  updateUser(userId: string, user: User): Observable<User> {
    return this.http.put<User>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_USERS}/${userId}`, user);
  }

  deleteUser(userId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_USERS}/${userId}`);
  }
}
