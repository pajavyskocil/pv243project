import { Component, OnInit } from '@angular/core';
import {Product, ProductService} from "../../services/product/product.service";

@Component({
  selector: 'app-products-page',
  templateUrl: './products-page.component.html',
  styleUrls: ['./products-page.component.css']
})
export class ProductsPageComponent implements OnInit {

  public products : Product[];
  public loaded : boolean = false;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getAllProducts().subscribe( products => {
      this.products = products;
      this.loaded = true;
    });
  }

}
