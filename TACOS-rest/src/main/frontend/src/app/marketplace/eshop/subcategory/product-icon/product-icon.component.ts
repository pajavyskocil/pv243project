import { Component, OnInit, Input } from '@angular/core';
import {Product} from "../../../../services/product/product.service";

@Component({
  selector: 'app-product-icon',
  templateUrl: './product-icon.component.html',
  styleUrls: ['./product-icon.component.scss']
})
export class ProductIconComponent implements OnInit {

  constructor() { }

  @Input()
  public product : Product;

  ngOnInit() {
  }

}
