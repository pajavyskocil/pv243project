import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AttributeCategory} from "../../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-attribute-categories-selector',
  templateUrl: './attribute-categories-selector.component.html',
  styleUrls: ['./attribute-categories-selector.component.css']
})
export class AttributeCategoriesSelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public attributeCategories : AttributeCategory[];

  @Output()
  private attributeCategoryIds : EventEmitter<number[]> = new EventEmitter<number[]>();

  public selectedAttributeCategoryIds : number[];

  ngOnInit() {
  }

  public setAttributeCategoryIds(ac : number[]) {
    this.attributeCategoryIds.emit(ac);
  }
}
