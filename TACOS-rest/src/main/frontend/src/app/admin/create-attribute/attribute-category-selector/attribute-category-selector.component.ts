import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AttributeCategory} from "../../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-attribute-category-selector',
  templateUrl: './attribute-category-selector.component.html',
  styleUrls: ['./attribute-category-selector.component.css']
})
export class AttributeCategorySelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public attributeCategories : AttributeCategory[];

  @Output()
  private attributeCategoryId : EventEmitter<number> = new EventEmitter<number>();

  public selectedAttributeCategoryId : number;

  ngOnInit() {
  }

  public setAttributeCategoryId(ac : number) {
    this.attributeCategoryId.emit(ac);
  }
}
