import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Template, TemplateService} from "../../services/template/template.service";

@Component({
  selector: 'app-template-list',
  templateUrl: './template-list.component.html',
  styleUrls: ['./template-list.component.css']
})
export class TemplateListComponent implements OnInit {

  @Input()
  public templates : Template[];

  @Output()
  delete : EventEmitter<any> = new EventEmitter();

  constructor(private templateService: TemplateService) { }

  ngOnInit() {
  }

  public deleteTemplate(i : number) {
    this.templateService.deleteTemplate(this.templates[i]).subscribe((removedTemplate) => {
      this.templates.splice(i, 1);
    });
  }
}
