import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Template} from "../../../services/template/template.service";

@Component({
  selector: 'app-templates-selector',
  templateUrl: './templates-selector.component.html',
  styleUrls: ['./templates-selector.component.css']
})
export class TemplatesSelectorComponent implements OnInit {

  constructor() { }

  @Input()
  public templates : Template[];

  @Output()
  private templateIds : EventEmitter<number[]> = new EventEmitter<number[]>();

  public selectedTemplateIds : number[];

  ngOnInit() {
  }

  public setTemplateIds(t : number[]) {
    this.templateIds.emit(t);
  }

}
