import {Component, Input, OnInit} from '@angular/core';
import {Order} from "../../services/order/order.service";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  @Input()
  public orders : Order[];

  constructor() { }

  ngOnInit() {
  }

}
