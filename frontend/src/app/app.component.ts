import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApplicationRoutes } from './app-main-rules/routes.enum';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'trello-clone';

  constructor(private router: Router) {}

  navigateToAdminDashboard() {
    this.router.navigate([ApplicationRoutes.ADMIN]);
  }

  showMessage() {
    alert('Button clicked!');
  }
}
