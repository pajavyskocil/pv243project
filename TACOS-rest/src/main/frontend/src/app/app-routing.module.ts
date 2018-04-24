import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserDetailComponent} from "./user-detail/user-detail.component";
import {UserDetailIdComponent} from "./user-detail-id/user-detail-id.component";
import {UserDetailEmailComponent} from "./user-detail-email/user-detail-email.component";



const routes: Routes = [
  {
    path: 'users/:id',
    component: UserDetailIdComponent
  },
  {
    path: 'users/filter/email/:email',
    component: UserDetailEmailComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
