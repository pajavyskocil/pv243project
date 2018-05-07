import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ProductService} from "../../services/product/product.service";
import {
  AttributeCategoryCreate,
  AttributeCategoryService
} from "../../services/attributeCategory/attribute-category.service";
import {Template, TemplateService} from "../../services/template/template.service";
import {Attribute, AttributeService} from "../../services/attribute/attribute.service";

@Component({
  selector: 'app-create-attribute-category',
  templateUrl: './create-attribute-category.component.html',
  styleUrls: ['./create-attribute-category.component.css']
})
export class CreateAttributeCategoryComponent implements OnInit {

  private attributeCategory : AttributeCategoryCreate;
  private templateIds : number[];
  private attributeIds : number[];
  public formGroup:FormGroup;
  public attributes : Attribute[];
  public templates : Template[];
  public loadedTemplates : boolean = false;
  public loadedAttributes : boolean = false;

  constructor(private templateService : TemplateService, private attributeService: AttributeService, private attributeCategoryService: AttributeCategoryService, private router: Router, private fb: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      nameCtrl: ['', Validators.required],
    });
    this.attributeService.getAllAttributes().subscribe( attributes => {
      this.attributes = attributes;
      this.loadedAttributes = true;
    });
    this.templateService.getAllTemplates().subscribe( templates => {
      this.templates = templates;
      this.loadedTemplates = true;
    });
  }

  createAttributeCategory(name) {
    this.attributeCategory = {
      name: name,
      templateIds: this.templateIds,
      attributeIds: this.attributeIds,
    };
    this.attributeCategoryService.createAttributeCategory(this.attributeCategory).subscribe(() => {
      this.router.navigate([("/admin/attribute-categories")]);
    });
  }

  setAttributeIds(ids: number[]){
    this.attributeIds = ids;
  }

  setTemplateIds(ids: number[]){
    this.templateIds = ids;
  }
}
