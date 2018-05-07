import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {
  ProductCategory, ProductCategoryCreate,
  ProductCategoryService
} from "../../services/product-category/product-category.service";

@Component({
  selector: 'app-create-product-category',
  templateUrl: './create-product-category.component.html',
  styleUrls: ['./create-product-category.component.css']
})
export class CreateProductCategoryComponent implements OnInit {

  private productCategory : ProductCategoryCreate;
  private productCategoryId : number;
  public formGroup:FormGroup;
  public productCategories : ProductCategory[];
  public loadedProductCategories : boolean = false;
  private image : String = '';

  constructor(private productCategoryService: ProductCategoryService, private router: Router, private fb: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      nameCtrl: ['', Validators.required],
    });
    this.productCategoryService.getAllProductCategories().subscribe( productCategories => {
      this.productCategories = productCategories;
      this.loadedProductCategories = true;
    });
  }

  createProductCategory(name) {
    this.productCategory = {
      name: name,
      image: this.image,
      parentCategoryId: this.productCategoryId,
    };
    this.productCategoryService.createProductCategory(this.productCategory).subscribe(() => {
      this.router.navigate([("/admin/product-categories")]);
    });
  }

  setProductCategoryId(id: number){
    this.productCategoryId = id;
  }

  public fileSelected(event) {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.image = reader.result.split(',')[1];
      };
    }
  }
}
