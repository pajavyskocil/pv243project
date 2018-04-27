import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Template} from "../../../../services/template/template.service";

@Component({
  selector: 'app-template-selector',
  templateUrl: './template-selector.component.html',
  styleUrls: ['./template-selector.component.css']
})
export class TemplateSelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public templates : Template[];

  @Output()
  private templateSelected : EventEmitter<Template> = new EventEmitter<Template>();

  public selectedTemplate : Template = undefined;

  ngOnInit() {
  }

  public selectTemplate(t : Template) {
    this.templateSelected.emit(t);
  }
}
