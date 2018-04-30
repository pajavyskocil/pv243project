import { Component, OnInit } from '@angular/core';
import {User, UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-detail-id',
  templateUrl: './user-detail-id.component.html',
  styleUrls: ['./user-detail-id.component.css']
})
export class UserDetailIdComponent implements OnInit {

  user : User;
  userId : number;
  public loaded : boolean = false;

  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.userId = res.id);
  }

  ngOnInit() {
    this.loadUser()
  }

  loadUser() {
    this.userService.findUserById(this.userId).subscribe((user) => {
      this.loaded =false;
      this.user = user;
      this.loaded = true;
    });
  }

  isSuperadmin() {
    return this.user.role == "SUPERADMIN";
  }

  isSubmitter() {
    return this.user.role == "SUBMITTER";
  }

  isPractitioner() {
    return this.user.role == "PRACTITIONER";
  }

  makeSuperAdmin() {
    this.userService.setSuperAdmin(this.user).subscribe(() => {
      this.loadUser();
    });
  }

  makePractitioner() {
    this.userService.setPractitioner(this.user).subscribe(() => {
      this.loadUser();
    });
  }

  makeSubmitter() {
    this.userService.setSubmitter(this.user).subscribe(() => {
      this.loadUser()
    });
  }

}
