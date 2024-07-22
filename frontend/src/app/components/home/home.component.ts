import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AppModule } from '../../app.module';
import { ApplicationRoutes } from '../../app-main-rules/routes.enum';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  title = 'home';

  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate([ApplicationRoutes.LOGIN]);
  }

  navigateToRegister() {
    this.router.navigate([ApplicationRoutes.REGISTER]);
  }

  showMessage() {
    alert('Button clicked!');
  }
}
