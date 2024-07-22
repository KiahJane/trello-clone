import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AppModule } from '../../../app.module';
import { ApplicationRoutes } from '../../../app-main-rules/routes.enum';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [AppModule, CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent {
  title = 'user board';

  constructor(private router: Router) {}

  navigateToBoard(boardId: string) {
    this.router.navigate([ApplicationRoutes.USER_BOARD.replace('{boardId}', boardId)]);
  }

  showMessage() {
    alert('Button clicked!');
  }
}
