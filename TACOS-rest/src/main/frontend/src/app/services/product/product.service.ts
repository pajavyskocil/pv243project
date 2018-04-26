import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Template} from "../template/template.service";

@Injectable()
export class ProductService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/orders";

  constructor(protected httpClient:HttpClient) { }

  public createProduct(productCreate: ProductCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, productCreate,{});
  }

  public deleteProduct(product: Product) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${product.id}`,{});
  }

  public getAllProducts() : Observable<Array<Product>> {
    return this.httpClient.get<Array<Product>>(this.URL, {});
  }

  public findProductById(id: LongRange) : Observable<Product> {
    return this.httpClient.get<Product>(`${this.URL}/${id}`,{});
  }

  public addTemplate(product : Product, template : Template) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${product.id}/addTemplate/${template.id}`, {});
  }

  public removeTemplate(product : Product, template : Template) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${product.id}/removeTemplate/${template.id}`, {});
  }
}

export interface Product {
  id: LongRange,
  name: String,
  description: String,
  minimalPrice: Number,
  templates: Template[]
}

export interface ProductCreate {
  name: String,
  description: String,
  templateIds: LongRange[],
  productCategoryIds: LongRange[]
}
