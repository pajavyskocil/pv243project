import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Attribute} from "../attribute/attribute.service";

@Injectable()
export class AttributeCategoryService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/attributeCategories";

  constructor(protected httpClient:HttpClient) { }

  public createAttributeCategory(attributeCategoryCreate: AttributeCategoryCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, attributeCategoryCreate,{});
  }

  public deleteAttributeCategory(attributeCategory: AttributeCategory) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${attributeCategory.id}`,{});
  }

  public getAllAttributeCategories() : Observable<Array<AttributeCategory>> {
    return this.httpClient.get<Array<AttributeCategory>>(this.URL, {});
  }

  public findAttributeCategoryById(id: LongRange) : Observable<AttributeCategory> {
    return this.httpClient.get<AttributeCategory>(`${this.URL}/${id}`,{});
  }
}

export interface AttributeCategory {
  id: LongRange,
  name: String,
  minimalPrice: Number,
  attributes: Attribute[],
}

export interface AttributeCategoryCreate {
  name: String,
  attributeIds: LongRange[],
  templateIds: LongRange[]
}
