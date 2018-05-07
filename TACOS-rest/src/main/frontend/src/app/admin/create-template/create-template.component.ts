import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TemplateCreate, TemplateService} from "../../services/template/template.service";
import {AttributeCategory, AttributeCategoryService} from "../../services/attributeCategory/attribute-category.service";
import {Product, ProductService} from "../../services/product/product.service";

@Component({
  selector: 'app-create-template',
  templateUrl: './create-template.component.html',
  styleUrls: ['./create-template.component.css']
})
export class CreateTemplateComponent implements OnInit {

  private template : TemplateCreate;
  private productIds : number[];
  private attributeCategoryIds : number[];
  public formGroup:FormGroup;
  public attributeCategories : AttributeCategory[];
  public products : Product[];
  public loadedCategories : boolean = false;
  public loadedProducts : boolean = false;

  constructor(private templateService : TemplateService, private productService: ProductService, private attributeCategoryService: AttributeCategoryService, private router: Router, private fb: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      nameCtrl: ['', Validators.required],
    });
    this.attributeCategoryService.getAllAttributeCategories().subscribe( attributeCategories => {
      this.attributeCategories = attributeCategories;
      this.loadedCategories = true;
    });
    this.productService.getAllProducts().subscribe( products => {
      this.products = products;
      this.loadedProducts = true;
    });
  }

  createTemplate(name) {
    this.template = {
      name: name,
      productIds: this.productIds,
      attributeCategoryIds: this.attributeCategoryIds,
    };
    this.templateService.createTemplate(this.template).subscribe(() => {
      this.router.navigate([("/admin/templates")]);
    });
  }

  setProductIds(ids: number[]){
    this.productIds = ids;
  }

  setAttributeCategoryIds(ids: number[]){
    this.attributeCategoryIds = ids;
  }
}
