import {Component, Input, OnInit} from '@angular/core';
import {ProductCategory} from "../../../services/product-category/product-category.service";

@Component({
  selector: 'app-product-category-detail',
  templateUrl: './product-category-detail.component.html',
  styleUrls: ['./product-category-detail.component.css']
})
export class ProductCategoryDetailComponent implements OnInit {

  @Input()
  productCategory: ProductCategory;
  constructor() { }

  ngOnInit() {
  }

}
