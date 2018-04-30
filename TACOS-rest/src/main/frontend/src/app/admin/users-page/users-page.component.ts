import { Component, OnInit } from '@angular/core';
import {User, UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-users-page',
  templateUrl: './users-page.component.html',
  styleUrls: ['./users-page.component.css']
})
export class UsersPageComponent implements OnInit {

  public users : User[];
  public loaded : boolean = false;

  constructor(private userService : UserService) { }

  ngOnInit(){
    //this.loadUsers();
    this.userService.loadUsers().subscribe((users) => {
      this.users = users;
      this.loaded = true;
    })
  }

  public loadUsers() {
    this.userService.loadUsers().subscribe((users) => {
      this.users = users;
      this.loaded = true;
    })
  }

}
