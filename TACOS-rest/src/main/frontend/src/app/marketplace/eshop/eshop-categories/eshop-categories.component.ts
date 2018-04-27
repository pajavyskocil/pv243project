import { Component, OnInit, Input } from '@angular/core';
import { ProductCategory } from '../../../services/product-category/product-category.service';

@Component({
  selector: 'app-eshop-categories',
  templateUrl: './eshop-categories.component.html',
  styleUrls: ['./eshop-categories.component.css']
})
export class EshopCategoriesComponent implements OnInit {

  constructor() { }

  @Input()
  public categories : ProductCategory[];

  ngOnInit() {
  }

}
