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

  public removeCreatedProduct(order : Order, createdProduct : CreatedProduct, ) : Observable<number> {
    return this.httpClient.put<number>(`${this.URL}/${order.id}/removeCreatedProduct/${createdProduct.id}`, {});
  }

  public getAllOrdersForState(orderState : OrderState) : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(`${this.URL}/filter/state/${orderState}`, {});
  }

  public getAllOrdersWithoutNewOrders() : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(`${this.URL}/filter/notNew`, {});
  }

  public submitOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/submitOrder/${order.id}`,{});
  }

  public cancelOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/cancelOrder/${order.id}`,{});
  }

  public finishOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/finishOrder/${order.id}`,{});
  }

  public processOrder(order: Order) : Observable<number>  {
    return this.httpClient.put<number>( `${this.URL}/processOrder/${order.id}`,{});
  }
}

export enum OrderState {
  BASKET,
  SUBMITTED,
  CANCELED,
  FINISHED,
  PROCESSED
}

export interface Order {
  id: number,
  state: OrderState,
  submitted: Object,
  submitterId: number,
  finished: Object,
  price: number,
  products: CreatedProduct[]
}
