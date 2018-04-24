import { Component, OnInit } from '@angular/core';
import {User, UserService} from "../services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-detail-email',
  templateUrl: './user-detail-email.component.html',
  styleUrls: ['./user-detail-email.component.css']
})
export class UserDetailEmailComponent implements OnInit {

  user : User;
  email : String;
  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.email = res.email);
  }

  ngOnInit() {
    this.userService.findUserByEmail(this.email).subscribe((user) => {
      this.user = user;
    });
  }

}
