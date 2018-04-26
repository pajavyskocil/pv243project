import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Order} from "../order/order.service";

@Injectable()
export class UserService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/users";

  constructor(protected httpClient:HttpClient) { }

  public createUser(userCreate: UserCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, userCreate,{});
  }

  public deleteUser(user: User) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${user.id}`,{});
  }

  public loadUsers(): Observable<Array<User>> {
    return this.httpClient.get<Array<User>>(this.URL, {});
  }

  public findUserById(id: LongRange) : Observable<User> {
    return this.httpClient.get<User>(`${this.URL}/${id}`,{});
  }

  public findUserByEmail(email: String) : Observable<User> {
    return this.httpClient.get<User>(`${this.URL}/filter/email/${email}`,{});
  }

  public getAllUsersForRole(userRole: UserRole): Observable<Array<User>> {
    return this.httpClient.get<Array<User>>(`${this.URL}/filter/role/${userRole}`, {});
  }

  public addOrderToUser(user : User, order : Order) : Observable<LongRange>{
    return this.httpClient.put<LongRange>(`${this.URL}/${user.id}/addOrder/${order.id}`, {});
  }

  public removeOrderFromUser(user : User, order : Order) : Observable<LongRange>{
    return this.httpClient.put<LongRange>(`${this.URL}/${user.id}/removeOrder/${order.id}`, {});
  }

  public setSubmitter(user: User) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/setSubmitter/${user.id}`,{});
  }

  public setPractitioner(user: User) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/setPractitioner/${user.id}`,{});
  }

  public setSuperAdmin(user: User) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/setSuperAdmin/${user.id}`,{});
  }

}

export enum UserRole {
  SUBMITTER,
  PRACTITIONER,
  SUPERADMIN
}
export interface User {
  id: LongRange,
  name: String,
  surname: String,
  email: String,
  role: UserRole,TA
  orders: Order[]
}

export interface UserCreate {
  name: String,
  surname: String,
  email: String,
  role: String,
  password: String
}

export interface UserAuthenticate {
  email: String,
  password: String
}
