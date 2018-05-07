import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Template, TemplateService} from "../../services/template/template.service";

@Component({
  selector: 'app-template-detail-page',
  templateUrl: './template-detail-page.component.html',
  styleUrls: ['./template-detail-page.component.css']
})
export class TemplateDetailPageComponent implements OnInit {
  template: Template;
  templateId: number;
  public loaded: boolean = false;

  constructor(private templateService: TemplateService, private route: ActivatedRoute) {
    this.route.params.subscribe(res => this.templateId = res.id);

  }

  ngOnInit() {
    this.loadTemplate();
  }

  loadTemplate() {
    this.templateService.findTemplateById(this.templateId).subscribe((template) => {
      this.template = template;
      this.loaded = true;
    });
  }
}
