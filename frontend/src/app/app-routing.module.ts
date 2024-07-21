import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AdminUsersComponent } from './components/admin/admin-users/admin-users.component';
import { AdminBoardsComponent } from './components/admin/admin-boards/admin-boards.component';
import { UserDashboardComponent } from './components/user/user-dashboard/user-dashboard.component';
import { AuthGuard } from './services/auth.guard';
import { ApplicationRoutes } from './app-main-rules/routes.enum';

export const routes: Routes = [
  { path: ApplicationRoutes.LOGIN, component: LoginComponent },
  { path: ApplicationRoutes.REGISTER, component: RegisterComponent },
  {
    path: ApplicationRoutes.ADMIN, component: AdminDashboardComponent, canActivate: [AuthGuard], data: { role: 'admin' },
    children: [
      { path: ApplicationRoutes.ADMIN_USERS, component: AdminUsersComponent },
      { path: ApplicationRoutes.ADMIN_BOARDS, component: AdminBoardsComponent },
    ]
  },
  { path: ApplicationRoutes.USER, component: UserDashboardComponent, canActivate: [AuthGuard], data: { role: 'user' } },
  { path: '', redirectTo: ApplicationRoutes.LOGIN, pathMatch: 'full' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
