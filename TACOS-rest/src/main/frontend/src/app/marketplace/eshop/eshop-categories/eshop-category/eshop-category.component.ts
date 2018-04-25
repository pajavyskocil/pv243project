import { Component, OnInit, Input } from '@angular/core';
import { ProductCategory } from '../../../../services/product-category/product-category.service';

@Component({
  selector: 'app-eshop-category',
  templateUrl: './eshop-category.component.html',
  styleUrls: ['./eshop-category.component.scss']
})
export class EshopCategoryComponent implements OnInit {

  constructor() { }

  @Input()
  private category : ProductCategory;

  ngOnInit() {
  }

}
