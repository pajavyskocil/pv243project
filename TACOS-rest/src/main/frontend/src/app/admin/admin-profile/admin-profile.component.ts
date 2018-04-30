import { Component, OnInit } from '@angular/core';
import {User, UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  public user : User;
  public loaded : boolean = false;

  constructor(private userService : UserService ) { }

  ngOnInit() {
    this.userService.findUserById(30).subscribe((user) => {
      this.user = user;
      this.loaded = true;
    }
    )
  }

}
