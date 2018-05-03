import { Component, OnInit } from '@angular/core';
import {User, UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

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
