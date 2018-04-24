import { Component, OnInit } from '@angular/core';
import {User, UserService} from "../services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-detail-id',
  templateUrl: './user-detail-id.component.html',
  styleUrls: ['./user-detail-id.component.css']
})
export class UserDetailIdComponent implements OnInit {

  user : User;
  userId : LongRange;
  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.userId = res.id);
  }

  ngOnInit() {
    this.userService.findUserById(this.userId).subscribe((user) => {
      this.user = user;
    });
  }

}
