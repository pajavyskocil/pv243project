import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AttributeCategory, AttributeCategoryService} from "../../services/attributeCategory/attribute-category.service";

@Component({
  selector: 'app-attribute-category-list',
  templateUrl: './attribute-category-list.component.html',
  styleUrls: ['./attribute-category-list.component.css']
})
export class AttributeCategoryListComponent implements OnInit {

  @Input()
  public attributeCategories: AttributeCategory[];

  @Output()
  delete : EventEmitter<any> = new EventEmitter();

  constructor(private attributeCategoryService: AttributeCategoryService) { }

  ngOnInit() {
  }

  public deleteAttributeCategory(i : number) {
    this.attributeCategoryService.deleteAttributeCategory(this.attributeCategories[i]).subscribe((removedCategory) => {
      this.attributeCategories.splice(i, 1);
    });
  }
}
