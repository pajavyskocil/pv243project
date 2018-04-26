import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {AttributeCategory} from "../attributeCategory/attribute-category.service";

@Injectable()
export class TemplateService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/productCategories";

  constructor(protected httpClient : HttpClient) { }

  public createTemplate(templateCreate: TemplateCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, templateCreate,{});
  }

  public deleteTemplate(template: Template) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${template.id}`,{});
  }

  public getAllTemplates(): Observable<Array<Template>> {
    return this.httpClient.get<Array<Template>>(this.URL, {});
  }

  public findTemplateById(id: LongRange) : Observable<Template> {
    return this.httpClient.get<Template>(`${this.URL}/${id}`,{});
  }

}


export interface Template {
  id: LongRange,
  name: String,
  minimalPrice: Number,
  attributesCategories: AttributeCategory[]
}

export interface TemplateCreate {
  name: String,
  minimalPrice: Number,
  attributesCategoryIds: LongRange[],
  productIds: LongRange[]

}
