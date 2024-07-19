import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  user: any = {};

  constructor(private userService: UserService, private route: ActivatedRoute) {}

  ngOnInit() {
    const userId = this.route.snapshot.paramMap.get('id');
    this.userService.getUser(userId).subscribe((data: any) => {
      this.user = data;
    });
  }

  saveUser() {
    this.userService.updateUser(this.user).subscribe(() => {
      alert('User saved successfully');
    });
  }
}
