import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Attribute, AttributeService} from "../../services/attribute/attribute.service";

@Component({
  selector: 'app-attribute-list',
  templateUrl: './attribute-list.component.html',
  styleUrls: ['./attribute-list.component.css']
})
export class AttributeListComponent implements OnInit {

  @Input()
  public attributes: Attribute[];

  @Output()
  delete : EventEmitter<any> = new EventEmitter();

  constructor(private attributeService: AttributeService) { }

  ngOnInit() {
  }

  public deleteAttribute(i : number) {
    this.attributeService.deleteAttribute(this.attributes[i]).subscribe((removedAttribute) => {
      this.attributes.splice(i, 1);
    });
  }
}
