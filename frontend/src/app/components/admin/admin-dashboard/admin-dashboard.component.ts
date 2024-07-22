import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AppModule } from '../../../app.module';
import { ApplicationRoutes } from '../../../app-main-rules/routes.enum';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [AppModule, CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  title = 'admin dashboard';

  constructor(private router: Router) { }

  navigateToBoards() {
    this.router.navigate([ApplicationRoutes.ADMIN_BOARDS]);
  }

  navigateToUsers() {
    this.router.navigate([ApplicationRoutes.ADMIN_USERS]);
  }

  showMessage() {
    alert('Button clicked!');
  }
}
