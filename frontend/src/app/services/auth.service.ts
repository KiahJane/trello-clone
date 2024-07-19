import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import RegisterModel from '../models/register.model';
import LoginModel from '../models/login.model';
import { Router } from '@angular/router';
import { extractRoleFromJson } from './helper.service';
import { ApplicationRoutes, BackendRoutes } from '../app-main-rules/routes.enum';
import { ApplicationRoles } from '../app-main-rules/application-roles';
import { MessageService } from 'primeng/api';
import PasswordChangeModel from '../models/password-change.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = ApplicationRoutes.BASE_API_URL;
  loginUrl: string = `${this.apiUrl}${BackendRoutes.AUTH_LOGIN}`;
  registerUrl: string = `${this.apiUrl}${BackendRoutes.AUTH_REGISTER}`;
  private changePasswordUrl = `${this.apiUrl}${BackendRoutes.CHANGE_PASSWORD}`;

  private ERROR_MESSAGE = 'Problem with login';

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private router: Router
  ) {}

  login(data: LoginModel): Observable<any> {
    return this.http.post<any>(this.loginUrl, data, {
      headers: { 'Content-Type': 'application/json' }
    })
      .pipe(map(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('currentUser', JSON.stringify(response.user));
        return response;
      }));
  }

  registerUser(data: RegisterModel): Observable<any> {
    return this.http.post<any>(this.registerUrl, data, {
      headers: { 'Content-Type': 'application/json' }
    })
      .pipe(map(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('currentUser', JSON.stringify(response.user));
        return response;
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
      this.router.navigateByUrl(ApplicationRoutes.ADMIN);
    } else if (role === ApplicationRoles.USER) {
      this.router.navigateByUrl(ApplicationRoutes.USER);
    } else if (role === ApplicationRoles.UNAPPROVED_USER) {
      this.router.navigateByUrl(ApplicationRoutes.UNAPPROVED_USER);
    }
  }

  rerouteToMainPage() {
    this.router.navigateByUrl(ApplicationRoutes.APP_MAIN);
  }

  changePassword(data: PasswordChangeModel): Observable<any> {
    return this.http.post<any>(this.changePasswordUrl, data, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
}
