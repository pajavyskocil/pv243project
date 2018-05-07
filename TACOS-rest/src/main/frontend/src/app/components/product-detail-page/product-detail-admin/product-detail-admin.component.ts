import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../../services/product/product.service";

@Component({
  selector: 'app-product-detail-admin',
  templateUrl: './product-detail-admin.component.html',
  styleUrls: ['./product-detail-admin.component.css']
})
export class ProductDetailAdminComponent implements OnInit {

  @Input()
  product: Product;
  constructor() { }

  ngOnInit() {
  }

}
