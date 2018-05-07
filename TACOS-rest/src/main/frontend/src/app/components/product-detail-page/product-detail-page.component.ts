import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Product, ProductService} from "../../services/product/product.service";

@Component({
  selector: 'app-product-detail-page',
  templateUrl: './product-detail-page.component.html',
  styleUrls: ['./product-detail-page.component.css']
})
export class ProductDetailPageComponent implements OnInit {

  product: Product;
  productId: number;
  public loaded: boolean = false;

  constructor(private productService: ProductService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.productId = res.id);

  }

  ngOnInit() {
    this.loadProduct();
  }

  loadProduct() {
    this.productService.findProductById(this.productId).subscribe((product) => {
      this.product = product;
      this.loaded = true;
    });
  }
}
