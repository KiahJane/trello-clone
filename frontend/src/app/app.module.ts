import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';

import { AuthGuard } from './services/auth.guard';
import { AuthService } from './services/auth.service';

import { MessageService } from 'primeng/api';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MessagesModule,
    MessageModule,
    ToastModule,
  ],
  providers: [
    provideHttpClient(),
    MessageService,
    AuthGuard,
    AuthService
  ]
})
export class AppModule { }
