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

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: ApplicationRoutes.ADMIN, component: AdminDashboardComponent, canActivate: [AuthGuard], data: { role: 'admin' },
    children: [
      { path: 'users', component: AdminUsersComponent },
      { path: 'boards', component: AdminBoardsComponent },
    ]
  },
  { path: ApplicationRoutes.USER, component: UserDashboardComponent, canActivate: [AuthGuard], data: { role: 'user' } },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
