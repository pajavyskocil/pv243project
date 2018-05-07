import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Attribute} from "../../../services/attribute/attribute.service";

@Component({
  selector: 'app-attributes-selector',
  templateUrl: './attributes-selector.component.html',
  styleUrls: ['./attributes-selector.component.css']
})
export class AttributesSelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public attributes : Attribute[];

  @Output()
  private attributeIds : EventEmitter<number[]> = new EventEmitter<number[]>();

  public selectedAttributeIds : number[];

  ngOnInit() {
  }

  public setAttributeIds(a : number[]) {
    this.attributeIds.emit(a);
  }

}
