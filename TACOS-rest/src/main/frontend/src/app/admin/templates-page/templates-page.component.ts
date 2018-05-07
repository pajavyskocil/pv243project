import { Component, OnInit } from '@angular/core';
import {Template, TemplateService} from "../../services/template/template.service";

@Component({
  selector: 'app-templates-page',
  templateUrl: './templates-page.component.html',
  styleUrls: ['./templates-page.component.css']
})
export class TemplatesPageComponent implements OnInit {

  public templates : Template[];
  public loaded : boolean = false;

  constructor(private templateService: TemplateService) { }

  ngOnInit() {
    this.templateService.getAllTemplates().subscribe( templates => {
      this.templates = templates;
      this.loaded = true;
    });
  }

}
