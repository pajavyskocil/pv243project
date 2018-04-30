import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User, UserService} from "../../../services/user/user.service";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  @Input()
  public users : User[];


  @Output()
  delete : EventEmitter<any> = new EventEmitter();

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  public deleteUser(i : number) {
    this.userService.deleteUser(this.users[i]).subscribe((removedUser) => {
      this.users.splice(i, 1);
    });
  }


}
