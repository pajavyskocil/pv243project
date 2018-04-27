import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AttributeCategory} from "../../../../services/attributeCategory/attribute-category.service";
import {Attribute} from "../../../../services/attribute/attribute.service";
import {AttributeSelect} from "./attribute-selector/attribute-selector.component";

@Component({
  selector: 'app-attribute-categories',
  templateUrl: './attribute-categories.component.html',
  styleUrls: ['./attribute-categories.component.css']
})
export class AttributeCategoriesComponent implements OnInit {

  constructor() { }

  @Input()
  public attributeCategories : AttributeCategory[];

  @Output()
  public selectedAttribute : EventEmitter<AttributeSelect> = new EventEmitter<AttributeSelect>();

  ngOnInit() {
  }

  public switchAttribute(as : AttributeSelect) {
    this.selectedAttribute.emit(as);
  }
}
