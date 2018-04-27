import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductCategory, ProductCategoryService } from '../../../services/product-category/product-category.service';

@Component({
  selector: 'app-subcategory',
  templateUrl: './subcategory.component.html',
  styleUrls: ['./subcategory.component.css']
})
export class SubcategoryComponent implements OnInit {

  private category : ProductCategory;
  public loaded : boolean = false;

  constructor(private productCategoryService : ProductCategoryService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(res => {
      let categoryId = res.id;
      this.loaded = false;

      this.productCategoryService.findProductCategoryById(categoryId).subscribe(
        productCategory => {
          this.category = productCategory;
          this.loaded = true;
        }
      )
    });

  }
}
