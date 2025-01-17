import {Injectable} from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {BackendRoutes} from "../app-main-rules/routes.enum";

interface JwtUser {
  accessToken: string;
}

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler) {

    let isSignUp = request.url.endsWith(BackendRoutes.AUTH_REGISTER);
    let isLogIn = request.url.endsWith(BackendRoutes.AUTH_LOGIN);

    if (!(isSignUp || isLogIn)) {
      let currentUser = localStorage.getItem('currentUser');
      if (currentUser !== null) {
        const user: JwtUser = JSON.parse(currentUser);
        if (user.accessToken) {
          request = request.clone({
            setHeaders: {
              Authorization: `Bearer ${user.accessToken}`
            }
          });
        }
      }
    }

    return next.handle(request);
  }

}
