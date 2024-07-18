import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
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
    this.authService.register(user).subscribe(response => {
      // Handle registration response
      this.router.navigate(['/login']);
    }, error => {
      // Handle error
      console.error('Registration failed', error);
    });
  }
}
