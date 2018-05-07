import { Component, OnInit } from '@angular/core';
import {Attribute, AttributeService} from "../../services/attribute/attribute.service";

@Component({
  selector: 'app-attributes-page',
  templateUrl: './attributes-page.component.html',
  styleUrls: ['./attributes-page.component.css']
})
export class AttributesPageComponent implements OnInit {

  public attributes : Attribute[];
  public loaded : boolean = false;

  constructor(private attributeService: AttributeService) { }

  ngOnInit() {
    this.attributeService.getAllAttributes().subscribe( attributes => {
      this.attributes = attributes;
      this.loaded = true;
    });
  }
}
