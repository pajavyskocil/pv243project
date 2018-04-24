import {Component, Input, OnInit} from '@angular/core';
import {User, UserService} from "../services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  @Input()
  user : User;
  constructor(private userService: UserService) {}

  ngOnInit() {}
}
