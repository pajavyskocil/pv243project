import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {CreatedProduct} from "../createdProduct/created-product.service";

@Injectable()
export class OrderService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/orders";

  constructor(protected httpClient:HttpClient) { }

  public deleteOrder(order: Order) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${order.id}`,{});
  }

  public getAllOrders() : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(this.URL, {});
  }

  public removeCreatedProduct(order : Order, createdProduct : CreatedProduct, ) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${order.id}/removeCreatedProduct/${createdProduct.id}`, {});
  }

  public getAllOrdersForState(orderState : OrderState) : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(`${this.URL}/filter/state/${orderState}`, {});
  }

  public getAllOrdersWithoutNewOrders() : Observable<Array<Order>> {
    return this.httpClient.get<Array<Order>>(`${this.URL}/filter/notNew`, {});
  }

  public submitOrder(order: Order) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/submitOrder/${order.id}`,{});
  }

  public cancelOrder(order: Order) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/cancelOrder/${order.id}`,{});
  }

  public finishOrder(order: Order) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/finishOrder/${order.id}`,{});
  }

  public processOrder(order: Order) : Observable<LongRange>  {
    return this.httpClient.put<LongRange>( `${this.URL}/processOrder/${order.id}`,{});
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
  id: LongRange,
  state: OrderState,
  submitted: Object,
  submitterId: LongRange,
  finished: Object,
  price: Number,
  products: CreatedProduct[]
}
