import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AttributeService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/attributes";

  constructor(protected httpClient: HttpClient) {
  }

  public createAttribute(attributeCreate: AttributeCreate) : Observable<number>  {
    return this.httpClient.post<number>(this.URL, attributeCreate,{});
  }

  public deleteAttribute(attribute: Attribute) : Observable<number>  {
    return this.httpClient.delete<number>( `${this.URL}/${attribute.id}`,{});
  }

  public getAllAttributes() : Observable<Array<Attribute>> {
    return this.httpClient.get<Array<Attribute>>(this.URL, {});
  }

  public findAttributeById(id: number) : Observable<Attribute> {
    return this.httpClient.get<Attribute>(`${this.URL}/${id}`,{});
  }
}

  export interface Attribute {
  id: number,
  name: String,
  price: Number,
  description: String,
  status: String,
  image: String,
  attributeCategoryId: number;
  attributeCategoryName: String;
}

  export interface AttributeCreate {
  name: String,
  price: Number,
  description: String,
  status: String,
  image: String,
  attributeCategoryId: number;
}
