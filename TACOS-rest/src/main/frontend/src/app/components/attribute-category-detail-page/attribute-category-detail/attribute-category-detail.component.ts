import {Component, Input, OnInit} from '@angular/core';
import {AttributeCategory} from "../../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-attribute-category-detail',
  templateUrl: './attribute-category-detail.component.html',
  styleUrls: ['./attribute-category-detail.component.css']
})
export class AttributeCategoryDetailComponent implements OnInit {

  @Input()
  attributeCategory: AttributeCategory;

  constructor() { }

  ngOnInit() {
  }

}
