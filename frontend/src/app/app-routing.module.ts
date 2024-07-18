import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AdminViewComponent } from './views/admin-view/admin-view.component';
import { UserViewComponent } from './views/user-view/user-view.component';
import { ApplicationRoutes } from './app-main-rules/application-routes';

const routes: Routes = [
  { path: ApplicationRoutes.APP_MAIN_ROUTE, component: LoginComponent },
  { path: ApplicationRoutes.UNAPPROVED_USER_HEADLINE_PAGE, component: RegisterComponent },
  { path: ApplicationRoutes.ADMIN_HEADLINE_PAGE, component: AdminViewComponent  },
  { path: ApplicationRoutes.USER_HEADLINE_PAGE, component: UserViewComponent },
  { path: '', redirectTo: ApplicationRoutes.APP_MAIN_ROUTE, pathMatch: 'full' },
  { path: '**', redirectTo: ApplicationRoutes.APP_MAIN_ROUTE }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
