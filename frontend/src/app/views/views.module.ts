import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminViewComponent } from './admin-view/admin-view.component';
import { UserViewComponent } from './user-view/user-view.component';

@NgModule({
  declarations: [
    AdminViewComponent,
    UserViewComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    AdminViewComponent,
    UserViewComponent
  ]
})
export class ViewsModule { }
