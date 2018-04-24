import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpParams } from '@angular/common/http';
import {s} from "@angular/core/src/render3";

@Injectable()
export class UserService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/users";

  constructor(protected httpClient:HttpClient) { }

  public createUser(userCreate: UserCreate) : Observable<UserCreate>  {
    return this.httpClient.post<UserCreate>(this.URL, userCreate,{});
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
  password: String,
  orders: Order[]
}

export interface UserCreate {
  name: String,
  surname: String,
  email: String,
  role: String,
  password: String
}

export interface Order {
  id: LongRange,
  state: String,
  submitted: String,
  submitter: User,
  finished: String,
  price: String,
  products: CreatedProduct[]
}

export interface CreatedProduct {
  id: LongRange,
  price: Number,
  count: LongRange,
  description: String,
  order: Order,
  product: Product,
  attributes: Attribute[]
}

export interface Product {
  id: LongRange,
  name: String,
  description: String,
  minimalPrice: Number,
  templates: Template[],
  productCategories: ProductCategory[],
  createdProducts: CreatedProduct[]
}

export interface Attribute {
  id: LongRange,
  name: String,
  price: Number,
  description: String,
  status: String,
  attributesCategories: AttributeCategory[],
  image: String,
  createdProducts: CreatedProduct[]
}

export interface Template {
  id: LongRange,
  name: String,
  minimalPrice: Number,
  attributesCategories: AttributeCategory[],
  products: CreatedProduct[]
}

export interface ProductCategory {
  id: LongRange,
  name: String,
  minimalPrice: Number,
  image: String,
  parentCategory: ProductCategory,
  subCategories: ProductCategory[]
}

export interface AttributeCategory {
  id: LongRange,
  name: String,
  minimalPrice: Number,
  attributes: Attribute[],
  templates: Template[]
}


