import {Component, Input, OnInit} from '@angular/core';
import {Attribute} from "../../../services/attribute/attribute.service";

@Component({
  selector: 'app-attribute-detail',
  templateUrl: './attribute-detail.component.html',
  styleUrls: ['./attribute-detail.component.css']
})
export class AttributeDetailComponent implements OnInit {

  @Input()
  attribute: Attribute;
  constructor() { }

  ngOnInit() {
  }

}
