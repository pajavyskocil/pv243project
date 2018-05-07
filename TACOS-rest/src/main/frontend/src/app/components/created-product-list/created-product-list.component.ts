import {Component, Input, OnInit} from '@angular/core';
import {CreatedProduct} from "../../services/createdProduct/created-product.service";

@Component({
  selector: 'app-created-product-list',
  templateUrl: './created-product-list.component.html',
  styleUrls: ['./created-product-list.component.css']
})
export class CreatedProductListComponent implements OnInit {

  @Input()
  public createdProducts : CreatedProduct[];

  constructor() { }

  ngOnInit() {
  }

}
