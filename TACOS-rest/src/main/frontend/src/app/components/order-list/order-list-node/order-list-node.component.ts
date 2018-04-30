import {Component, Input, OnInit} from '@angular/core';
import {Order} from "../../../services/order/order.service";

@Component({
  selector: 'app-order-list-node',
  templateUrl: './order-list-node.component.html',
  styleUrls: ['./order-list-node.component.css']
})
export class OrderListNodeComponent implements OnInit {

  @Input()
  public order:Order;

  constructor() { }

  ngOnInit() {
  }

}
