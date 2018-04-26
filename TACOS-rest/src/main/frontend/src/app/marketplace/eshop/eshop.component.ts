import { Component, OnInit } from '@angular/core';
import { ProductCategoryService, ProductCategory } from '../../services/product-category/product-category.service';

@Component({
  selector: 'app-eshop',
  templateUrl: './eshop.component.html',
  styleUrls: ['./eshop.component.css']
})
export class EshopComponent implements OnInit {

  private categories : ProductCategory[];

  constructor(private productCategorySerivce: ProductCategoryService) { }

  ngOnInit() {
    this.productCategorySerivce.getAllProductCategories().subscribe( categories => this.categories = categories)
  }

}
