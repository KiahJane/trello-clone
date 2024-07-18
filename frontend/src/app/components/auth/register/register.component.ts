import { Component } from '@angular/core';
import { AuthService } from '../../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  register() {
    const user = { username: this.username, password: this.password };
    this.authService.register(user).subscribe(() => {
      this.router.navigate(['/login']);
    }, (error: any) => {
      // Handle error
      console.error('Registration failed', error);
    });
  }
}
