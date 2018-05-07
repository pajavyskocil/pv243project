import { Component, OnInit } from '@angular/core';
import {ProductCategory, ProductCategoryService} from "../../services/product-category/product-category.service";

@Component({
  selector: 'app-product-categories-page',
  templateUrl: './product-categories-page.component.html',
  styleUrls: ['./product-categories-page.component.css']
})
export class ProductCategoriesPageComponent implements OnInit {

  public productCategories : ProductCategory[];
  public loaded : boolean = false;

  constructor(private productCategoryService: ProductCategoryService) { }

  ngOnInit() {
    this.productCategoryService.getAllProductCategories().subscribe( productCategories => {
      this.productCategories = productCategories;
      this.loaded = true;
    });
  }

}
