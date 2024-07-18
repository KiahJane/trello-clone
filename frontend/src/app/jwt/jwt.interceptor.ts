import {Injectable} from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {ApplicationRoutes, BackendRoutes} from "../app-main-rules/application-routes";

interface JwtUser {
  accessToken: string;
}

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler) {

    let isSignUp = request.url.endsWith(BackendRoutes.SIGN_IN_END);
    let isLogIn = request.url.endsWith(BackendRoutes.LOG_IN_END);

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
