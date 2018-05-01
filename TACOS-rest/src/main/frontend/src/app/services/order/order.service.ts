import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {CreatedProduct} from "../createdProduct/created-product.service";

@Injectable()
export class OrderService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/orders";

  constructor(protected httpClient:HttpClient) { }

  public deleteOrder(order: Order) : Observable<number>  {
    return this.httpClient.delete<number>( `${this.URL}/${order.id}`,{});
  }

  public getAllOrders() : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(this.URL, {});
  }

  public findOrderById(id: Number) : Observable<Order> {
    return this.httpClient.get<Order>(`${this.URL}/${id}`,{});
  }

  public removeCreatedProduct(order : Order, createdProduct : CreatedProduct, ){
    console.log(`${this.URL}/${order.id}/removeCreatedProduct?createdProductId=${createdProduct.id}`);
    return this.httpClient.put(`${this.URL}/${order.id}/removeCreatedProduct?createdProductId=${createdProduct.id}`, {});
  }

  public getAllOrdersForState(orderState : String) : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(`${this.URL}/filter/state/${orderState}`, {});
  }

  public getAllOrdersWithoutNewOrders() : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(`${this.URL}/filter/notNew`, {});
  }

  public submitOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/submitOrder?id=${order.id}`,{});
  }

  public cancelOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/cancelOrder?id=${order.id}`,{});
  }

  public finishOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/finishOrder?id=${order.id}`,{});
  }

  public processOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/processOrder?id=${order.id}`,{});
  }
}

export interface localDate {
  dayOfMonth: number,
  month: number,
  year: number
}


export interface Order {
  id: number,
  state: String,
  submitted: localDate,
  submitterId: number,
  finished: localDate,
  price: number,
  products: CreatedProduct[]
}
