import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import RegisterModel from '../models/register.model';
import LogInModel from '../models/login.model';
import { Router } from '@angular/router';
import { extractRoleFromJson } from './helper.service';
import { ApplicationRoutes, BackendRoutes } from '../app-main-rules/routes.enum';
import { ApplicationRoles } from '../app-main-rules/application-roles';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:3000';
  registerUrl: string = `${this.apiUrl}/api/auth/${BackendRoutes.Register}`;
  loginUrl: string = `${this.apiUrl}/api/auth/${BackendRoutes.Login}`;

  private ERROR_MESSAGE = 'Problem with login';

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private router: Router
  ) {}

  login(data: LogInModel): Observable<any> {
    return this.http.post<any>(this.loginUrl, data, {
      headers: { 'Content-Type': 'application/json' }
    })
      .pipe(map(user => {
        localStorage.setItem('token', user.token);
        localStorage.setItem('currentUser', JSON.stringify(user));
        return user;
      }));
  }

  register(data: RegisterModel): Observable<any> {
    return this.http.post<any>(this.registerUrl, data, {
      headers: { 'Content-Type': 'application/json' }
    })
      .pipe(map(user => {
        localStorage.setItem('token', user.accessToken);
        localStorage.setItem('currentUser', JSON.stringify(user));
        return user;
      }));
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  getRole(): string {
    const token = this.getToken();
    if (token) {
      const payload = atob(token.split('.')[1]);
      const parsedPayload = JSON.parse(payload);
      return parsedPayload.role;
    }
    return '';
  }

  rerouteCurrentUser() {
    let role = extractRoleFromJson(localStorage.getItem('currentUser'));

    if (role === undefined) {
      this.router.navigateByUrl(ApplicationRoutes.APP_MAIN);
      this.messageService.add({ severity: 'error', summary: 'Error', detail: this.ERROR_MESSAGE });
    } else if (role === ApplicationRoles.ADMIN) {
      this.router.navigateByUrl(ApplicationRoutes.Admin);
    } else if (role === ApplicationRoles.USER) {
      this.router.navigateByUrl(ApplicationRoutes.User);
    } else if (role === ApplicationRoles.UNAPPROVED_USER) {
      this.router.navigateByUrl(ApplicationRoutes.UnapprovedUser);
    }
  }

  rerouteToMainPage() {
    this.router.navigateByUrl(ApplicationRoutes.APP_MAIN);
  }
}
