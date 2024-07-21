import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ApplicationRoutes } from './app-main-rules/routes.enum';
import { AppModule } from './app.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AppModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'trello-clone';

  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate([ApplicationRoutes.LOGIN]);
  }

  navigateToRegister() {
    this.router.navigate([ApplicationRoutes.REGISTER]);
  }

  navigateToAdminDashboard() {
    this.router.navigate([ApplicationRoutes.ADMIN]);
  }

  navigateToUserDashboard() {
    this.router.navigate([ApplicationRoutes.USER]);
  }


  showMessage() {
    alert('Button clicked!');
  }
}
