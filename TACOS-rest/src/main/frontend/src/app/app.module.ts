import { BrowserModule } from '@angular/platform-browser';
import { NgModule , NO_ERRORS_SCHEMA} from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule} from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";


import { AppComponent } from './app.component';


import { FormsModule } from '@angular/forms';
import {UserService} from "./services/user/user.service";
import { UserDetailComponent } from './user-detail/user-detail.component';
import { UserDetailIdComponent } from './user-detail-id/user-detail-id.component';
import { UserDetailEmailComponent } from './user-detail-email/user-detail-email.component';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailComponent,
    UserDetailIdComponent,
    UserDetailEmailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [UserService],
  bootstrap: [AppComponent],
  schemas: [ NO_ERRORS_SCHEMA ]
})
export class AppModule { }
