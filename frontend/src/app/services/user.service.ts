import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  user: string = "/api/user/information";
  board: string = "/api/user/board";
  boards: string = "/api/user/boards";

  constructor(private http: HttpClient, private configService: ConfigService) {}
  
  getMyInformation(): Observable<User> {
    return this.http.get<User>(this.user, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.configService.baseApiUrl}/users`);
  }

  getUserById(userId: string): Observable<User> {
    return this.http.get<User>(`${this.configService.baseApiUrl}/users/${userId}`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.configService.baseApiUrl}/users`, user);
  }

  updateUser(userId: string, user: User): Observable<User> {
    return this.http.put<User>(`${this.configService.baseApiUrl}/users/${userId}`, user);
  }

  deleteUser(userId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}/users/${userId}`);
  }
}
