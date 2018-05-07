import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AttributeCategory, AttributeCategoryService} from "../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-attribute-category-detail-page',
  templateUrl: './attribute-category-detail-page.component.html',
  styleUrls: ['./attribute-category-detail-page.component.css']
})
export class AttributeCategoryDetailPageComponent implements OnInit {

  attributeCategory: AttributeCategory;
  attributeCategoryId: number;
  public loaded: boolean = false;

  constructor(private attributeCategoryService: AttributeCategoryService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.attributeCategoryId = res.id);

  }

  ngOnInit() {
    this.loadAttributeCategory();
  }

  loadAttributeCategory() {
    this.attributeCategoryService.findAttributeCategoryById(this.attributeCategoryId).subscribe((attributeCategory) => {
      this.attributeCategory = attributeCategory;
      this.loaded = true;
    });
  }
}

