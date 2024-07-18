import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard.component';
import { UserDashboardComponent } from './components/user/user-dashboard.component';

const routes: Routes = [
    // Auth routes
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
  
    // Admin routes
    { path: 'admin', component: AdminDashboardComponent },
  
    // User routes
    { path: 'user', component: UserDashboardComponent },
  
    // Default route
    { path: '', redirectTo: '/login', pathMatch: 'full' },
  
    // Wildcard route (for any undefined paths)
    { path: '**', redirectTo: '/login' }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
