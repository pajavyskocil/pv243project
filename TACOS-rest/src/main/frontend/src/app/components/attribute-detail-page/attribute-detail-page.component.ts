import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Attribute, AttributeService} from "../../services/attribute/attribute.service";

@Component({
  selector: 'app-attribute-detail-page',
  templateUrl: './attribute-detail-page.component.html',
  styleUrls: ['./attribute-detail-page.component.css']
})
export class AttributeDetailPageComponent implements OnInit {

  attribute: Attribute;
  attributeId: number;
  public loaded: boolean = false;

  constructor(private attributeService: AttributeService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.attributeId = res.id);

  }

  ngOnInit() {
    this.loadAttribute();
  }

  loadAttribute() {
    this.attributeService.findAttributeById(this.attributeId).subscribe((attribute) => {
      this.attribute = attribute;
      this.loaded = true;
    });
  }
}
