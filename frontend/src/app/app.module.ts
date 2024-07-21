import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router'; 

import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';

import { AuthGuard } from './services/auth.guard';
import { AuthService } from './services/auth.service';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AdminUsersComponent } from './components/admin/admin-users/admin-users.component';
import { AdminBoardsComponent } from './components/admin/admin-boards/admin-boards.component';
import { UserDashboardComponent } from './components/user/user-dashboard/user-dashboard.component';
import { BoardSummaryComponent } from './components/user/board-summary/board-summary.component';

import { MessageService } from 'primeng/api';

@NgModule({
  declarations: [
    //AppComponent,
    LoginComponent,
    RegisterComponent,
    AdminDashboardComponent,
    AdminUsersComponent,
    AdminBoardsComponent,
    UserDashboardComponent,
    BoardSummaryComponent
  ],
  imports: [
    AppComponent,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    MessagesModule,
    MessageModule,
    ToastModule
  ],
  providers: [
    MessageService,
    AuthGuard,
    AuthService
  ], 
  bootstrap: [ AppComponent ],
})
export class AppModule { }
