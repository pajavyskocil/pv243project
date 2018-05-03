import { Component, OnInit } from '@angular/core';
import {Order, OrderService} from "../../services/order/order.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-order-detail-page',
  templateUrl: './marketplace-order-detail.component.html',
  styleUrls: ['./marketplace-order-detail.component.css']
})
export class MarketplaceOrderDetailComponent implements OnInit {

  order : Order;
  orderId : number;
  public loaded: boolean = false;

  constructor(private orderService : OrderService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.orderId = res.id);

  }

  ngOnInit() {
    this.loadOrder();
  }

  loadOrder() {
    this.orderService.findOrderById(this.orderId).subscribe((order) => {
      this.order = order;
      this.loaded = true;
    });
  }


  isSubmitted() {
    return this.order.state == "SUBMITTED";
  }

  isProcessed() {
    return this.order.state == "PROCESSED"
  }

  cancelOrder() {
    this.orderService.cancelOrder(this.order).subscribe(() => {
      this.loadOrder();
    });
  }


  processOrder() {
    this.orderService.processOrder(this.order).subscribe(() => {
      this.loadOrder();
    });
  }

  finishOrder() {
    this.orderService.finishOrder(this.order).subscribe(() => {
      this.loadOrder();
    });
  }

}
