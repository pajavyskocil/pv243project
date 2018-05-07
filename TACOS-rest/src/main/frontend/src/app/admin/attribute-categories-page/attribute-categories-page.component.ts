import { Component, OnInit } from '@angular/core';
import {AttributeCategory, AttributeCategoryService} from "../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-attribute-categories-page',
  templateUrl: './attribute-categories-page.component.html',
  styleUrls: ['./attribute-categories-page.component.css']
})
export class AttributeCategoriesPageComponent implements OnInit {

  public attributeCategories : AttributeCategory[];
  public loaded : boolean = false;

  constructor(private attributeCategoryService: AttributeCategoryService) { }

  ngOnInit() {
    this.attributeCategoryService.getAllAttributeCategories().subscribe( attributeCategories => {
      this.attributeCategories = attributeCategories;
      this.loaded = true;
    });
  }

}
