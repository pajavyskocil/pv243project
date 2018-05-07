import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductCategory} from "../../../services/product-category/product-category.service";

@Component({
  selector: 'app-product-category-selector',
  templateUrl: './product-category-selector.component.html',
  styleUrls: ['./product-category-selector.component.css']
})
export class ProductCategorySelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public productCategories : ProductCategory[];

  @Output()
  private productCategoryId : EventEmitter<number> = new EventEmitter<number>();

  public selectedProductCategoryId : number;

  ngOnInit() {
  }

  public setProductCategoryId(pc : number) {
    this.productCategoryId.emit(pc);
  }
}
