import { Component, OnInit, Input } from '@angular/core';
import { ProductCategory } from '../../../../services/product-category/product-category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-eshop-category',
  templateUrl: './eshop-category.component.html',
  styleUrls: ['./eshop-category.component.scss']
})
export class EshopCategoryComponent implements OnInit {

  constructor(private router: Router) { }

  @Input()
  private category : ProductCategory;

  ngOnInit() {
  }

  navigateToCategory() {
    this.router.navigate(['/marketplace/eshop-category', this.category.id]);
  }

}
