import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductCategory, ProductCategoryService} from "../../services/product-category/product-category.service";

@Component({
  selector: 'app-product-category-list',
  templateUrl: './product-category-list.component.html',
  styleUrls: ['./product-category-list.component.css']
})
export class ProductCategoryListComponent implements OnInit {

  @Input()
  public productCategories: ProductCategory[];

  @Output()
  delete : EventEmitter<any> = new EventEmitter();

  constructor(private productCategoryService: ProductCategoryService) { }

  ngOnInit() {
  }

  public deleteProductCategory(i : number) {
    this.productCategoryService.deleteProductCategory(this.productCategories[i]).subscribe((removedCategory) => {
      this.productCategories.splice(i, 1);
    });
  }
}
