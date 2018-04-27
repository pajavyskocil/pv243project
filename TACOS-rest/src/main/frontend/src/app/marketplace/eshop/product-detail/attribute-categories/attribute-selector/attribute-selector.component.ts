import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AttributeCategory} from "../../../../../services/attributeCategory/attribute-category.service";
import {Attribute} from "../../../../../services/attribute/attribute.service";

@Component({
  selector: 'app-attribute-selector',
  templateUrl: './attribute-selector.component.html',
  styleUrls: ['./attribute-selector.component.css']
})
export class AttributeSelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public attributeCategory : AttributeCategory;
  @Output()
  private attributeSelected : EventEmitter<AttributeSelect> = new EventEmitter<AttributeSelect>();

  public selectedAttribute : Attribute = null;

  ngOnInit() {
  }

  public selectAttribute(a : Attribute) {
    this.selectedAttribute = a;
    this.attributeSelected.emit({"attribute" : a, "category" : this.attributeCategory});
  }
}

export interface AttributeSelect {
  attribute : Attribute,
  category : AttributeCategory
}
