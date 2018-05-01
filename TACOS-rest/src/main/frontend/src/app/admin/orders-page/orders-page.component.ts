import { Component, OnInit } from '@angular/core';
import {Order, OrderService} from "../../services/order/order.service";

@Component({
  selector: 'app-orders-page',
  templateUrl: './orders-page.component.html',
  styleUrls: ['./orders-page.component.css']
})
export class OrdersPageComponent implements OnInit {

  public orders : Order[];
  public loaded : boolean = false;

  constructor(private orderService : OrderService) { }

  ngOnInit() {
    this.orderService.getAllOrdersWithoutNewOrders().subscribe((orders) => {
      this.orders = orders;
      this.loaded = true;

    });
  }

}
