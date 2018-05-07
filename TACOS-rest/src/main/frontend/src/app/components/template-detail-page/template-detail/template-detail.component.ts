import {Component, Input, OnInit} from '@angular/core';
import {Template} from "../../../services/template/template.service";

@Component({
  selector: 'app-template-detail',
  templateUrl: './template-detail.component.html',
  styleUrls: ['./template-detail.component.css']
})
export class TemplateDetailComponent implements OnInit {

  @Input()
  template:Template;

  constructor() { }

  ngOnInit() {
  }

}
