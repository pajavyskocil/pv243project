import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../../../services/product/product.service";

@Component({
  selector: 'app-products-selector',
  templateUrl: './products-selector.component.html',
  styleUrls: ['./products-selector.component.css']
})
export class ProductsSelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public products : Product[];

  @Output()
  private productIds : EventEmitter<number[]> = new EventEmitter<number[]>();

  public selectedProductIds : number[];

  ngOnInit() {
  }

  public setProductIds(p : number[]) {
    this.productIds.emit(p);
  }

}
