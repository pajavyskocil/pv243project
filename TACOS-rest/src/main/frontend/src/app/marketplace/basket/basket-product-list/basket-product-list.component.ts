import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CreatedProduct} from "../../../services/createdProduct/created-product.service";
import {Order, OrderService} from "../../../services/order/order.service";

@Component({
  selector: 'app-basket-product-list',
  templateUrl: './basket-product-list.component.html',
  styleUrls: ['./basket-product-list.component.css']
})
export class BasketProductListComponent implements OnInit {

  @Input()
  public order : Order;

  public products : CreatedProduct[];

  @Output()
  reload : EventEmitter<any> = new EventEmitter();

  constructor(private orderService : OrderService) { }

  ngOnInit() {
    if (!(this.order == null || this.order == undefined)) {
      this.products = this.order.products;
    } else {
      this.products = null;
    }
  }


  removeCreatedProduct(i: number) {
    console.log(i);
    console.log(this.order);
    this.orderService.removeCreatedProduct(this.order , this.products[i]).subscribe((removedProduct) => {
      this.products.splice(i,1);
      this.reload.emit(null);
    });
  }
}
