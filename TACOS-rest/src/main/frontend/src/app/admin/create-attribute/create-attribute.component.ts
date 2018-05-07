import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AttributeCreate, AttributeService} from "../../services/attribute/attribute.service";
import {AttributeCategory, AttributeCategoryService} from "../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-create-attribute',
  templateUrl: './create-attribute.component.html',
  styleUrls: ['./create-attribute.component.css']
})
export class CreateAttributeComponent implements OnInit {

  private attribute : AttributeCreate;
  private attributeCategoryId : number;
  public formGroup:FormGroup;
  public attributeCategories : AttributeCategory[];
  public loadedAttributeCategories : boolean = false;

  constructor(private attributeService: AttributeService, private attributeCategoryService: AttributeCategoryService, private router: Router, private fb: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      nameCtrl: ['', Validators.required],
      priceCtrl: ['', Validators.required]
    });
    this.attributeCategoryService.getAllAttributeCategories().subscribe( attributeCategories => {
      this.attributeCategories = attributeCategories;
      this.loadedAttributeCategories = true;
    });
  }

  createAttribute(name, price, description) {
    this.attribute = {
      name: name,
      price: price,
      description: description,
      status: "IN_STOCK",
      image: undefined,
      attributeCategoryId: this.attributeCategoryId,
    };
    this.attributeService.createAttribute(this.attribute).subscribe(() => {
      this.router.navigate([("/admin/attributes")]);
    });
  }

  setAttributeCategoryId(id: number){
    this.attributeCategoryId = id;
  }
}
