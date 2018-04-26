import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AttributeService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/attributes";

  constructor(protected httpClient: HttpClient) {
  }

  public createAttribute(attributeCreate: AttributeCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, attributeCreate,{});
  }

  public deleteAttribute(attribute: Attribute) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${attribute.id}`,{});
  }

  public getAllAttributes() : Observable<Array<Attribute>> {
    return this.httpClient.get<Array<Attribute>>(this.URL, {});
  }

  public findAttributeById(id: LongRange) : Observable<Attribute> {
    return this.httpClient.get<Attribute>(`${this.URL}/${id}`,{});
  }
}

  export interface Attribute {
  id: LongRange,
  name: String,
  price: Number,
  description: String,
  status: String,
  image: String,
  attributeCategoryId: LongRange;
  attributeCategoryName: String;
}

  export interface AttributeCreate {
  name: String,
  price: Number,
  description: String,
  status: String,
  image: String,
  attributeCategoryId: LongRange;
}
