import {Component, Input, OnInit} from '@angular/core';
import {CreatedProduct} from "../../services/createdProduct/created-product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  @Input()
  public products : CreatedProduct[];

  constructor() { }

  ngOnInit() {
  }

}
